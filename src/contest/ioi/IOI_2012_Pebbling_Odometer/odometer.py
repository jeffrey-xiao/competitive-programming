#!/usr/bin/python
# -*- coding: utf-8 -*-
#
# Odometer simulator
# Copyright © 2012 Giovanni Mascellani <mascellani@poisson.phc.unipi.it>
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.


import argparse
import tempfile
import os
import subprocess
import shutil
import select
import json
import sys

OP_LEFT = 0
OP_RIGHT = 1
OP_MOVE = 2
OP_GET = 3
OP_PUT = 4
OP_HALT = 5
OP_JUMP = 6
OP_BORDERJ = 7
OP_PEBBLEJ = 8

op_map = {'left': OP_LEFT,
          'right': OP_RIGHT,
          'move': OP_MOVE,
          'get': OP_GET,
          'put': OP_PUT,
          'halt': OP_HALT,
          'jump ': OP_JUMP,
          'border ': OP_BORDERJ,
          'pebble ': OP_PEBBLEJ}

op_inv_map = {}
for i, j in op_map.iteritems():
    op_inv_map[j] = i

movex = {0: 0, 1: -1, 2: 0, 3: 1}
movey = {0: -1, 1: 0, 2: 1, 3: 0}
dir_name = {0: 'north',
            1: 'west',
            2: 'south',
            3: 'east'}

DEFAULT_GRID_SIDE = 256
ALPHABET = set('qwertyuiopasdfghjklzxcvbnm'
               'QWERTYUIOPASDFGHJKLZXCVBNM1234567890_')
MAX_LABEL_LEN = 128


def check_label(label):
    if not set(label).issubset(ALPHABET):
        raise ParserException("Label %s has forbidden characters" % (label))
    if len(label) > MAX_LABEL_LEN:
        raise ParserException("Label %s is too long" % (label))


class ParserException(Exception):
    pass


class Simulation:

    def __init__(self, grid_side=DEFAULT_GRID_SIDE,
                 debug=False, max_steps=None,
                 grid=None):
        self.grid_side = grid_side
        if grid is None:
            self.grid = [[0] * grid_side for i in xrange(grid_side)]
        else:
            self.grid = grid
        self.debug = debug
        self.max_steps = max_steps

        self.posx = 0
        self.posy = 0
        self.dir = 0
        self.killed = False

        self.instruction_num = 0
        self.step_num = 0
        self.ip = 0

        self.code = []
        self.labels = {}
        self.labels_inv = {}

        self.compilation_dir = None

    def load_program(self, fin):
        for line in fin:

            # Preprocess line, removes comments and spaces
            line += '#'
            line, comment = line.split('#', 1)
            line = line.strip().replace('\t', ' ')
            if line == '':
                continue

            # Detect a label
            if line.endswith(':'):
                label = line[:-1].strip()
                check_label(label)
                if label in self.labels:
                    raise ParserException("Label %s defined twice" % (label))
                else:
                    self.labels[label] = len(self.code)
                    if len(self.code) not in self.labels_inv:
                        self.labels_inv[len(self.code)] = []
                    self.labels_inv[len(self.code)].append(label)

            # Interpret line
            else:
                for op_code in op_map:
                    if line.startswith(op_code):
                        if op_map[op_code] in \
                                [OP_JUMP, OP_BORDERJ, OP_PEBBLEJ]:
                            label = line[len(op_code):].strip()
                            check_label(label)
                        else:
                            if line != op_code:
                                raise ParserException("Could not parse "
                                                      "line %s" % (line))
                            label = None
                        self.code.append((op_map[op_code], label))
                        self.instruction_num += 1
                        break
                else:
                    raise ParserException("Could not parse line %s" % (line))

        # Convert labels to numbers
        for addr in xrange(len(self.code)):
            op_code, label = self.code[addr]
            if op_code in [OP_JUMP, OP_BORDERJ, OP_PEBBLEJ]:
                if label not in self.labels:
                    raise ParserException("Label %s not defined" % (label))
                else:
                    self.code[addr] = (op_code, self.labels[label])
            else:
                self.code[addr] = (op_code, 0)

    def dump_bytecode(self, fout):
        data = [self.code, self.labels, self.labels_inv]
        json.dump(data, fout)

    def load_bytecode(self, fin):
        data = json.load(fin)
        self.code, self.labels, self.labels_inv = data

    def compile_runner(self):
        self.compilation_dir = tempfile.mkdtemp()
        self.srcfile = os.path.join(self.compilation_dir, 'runner.c')
        self.runfile = os.path.join(self.compilation_dir, 'runner')

        with open(os.path.join(os.path.dirname(__file__), 'runner.c')) as fin:
            with open(self.srcfile, 'w') as fout:
                for line in fin:
                    fout.write(line)
                    if 'PROGRAM DATA' in line:
                        for op_code, data in self.code:
                            fout.write("{ %d, %d },\n" % (op_code, data))
                    if 'GRID SIDE' in line:
                        fout.write('#define SIDE %d\n' % (self.grid_side))

        res = os.system('gcc -O2 -static -Wall -o %s %s' %
                        (self.runfile, self.srcfile))
        if res != 0:
            self.clean_runner()
            raise Exception("Couldn't compile runner")

    def clean_runner(self):
        if self.compilation_dir is not None:
            try:
                shutil.rmtree(self.compilation_dir)
            except:
                pass
        self.compilation_dir = None
        self.srcfile = None
        self.runfile = None

    def execute_runner(self):
        # Start the runner
        popen = subprocess.Popen(self.runfile,
                                 stdin=subprocess.PIPE,
                                 stdout=subprocess.PIPE)

        # Write the input data (max_steps and transposed grid)
        max_steps = self.max_steps
        if max_steps is None:
            max_steps = -1
        popen.stdin.write("%d %d %d %d %d %d\n" % (self.posx,
                                                   self.posy,
                                                   self.dir,
                                                   self.step_num,
                                                   self.ip,
                                                   max_steps))
        for i in xrange(self.grid_side):
            for j in xrange(self.grid_side):
                popen.stdin.write("%d " % (self.grid[i][j]))
            popen.stdin.write("\n")
        popen.stdin.close()

        try:
            select.select([popen.stdout], [], [])
        except KeyboardInterrupt:
            #popen.send_signal(subprocess.signal.SIGUSR1)
            print "Keyboard interrput, waiting for child to terminate!"

        # Read back the output data (position, direction, step number,
        # IP, whether program was killed, and transposed grid)
        self.posx, self.posy, self.dir, self.step_num, self.ip, self.killed = \
            map(int, popen.stdout.readline().strip().split(' '))
        self.killed = True if self.killed != 0 else False
        for i in xrange(self.grid_side):
            self.grid[i] = map(int, popen.stdout.readline().strip().split(' '))
            assert len(self.grid[i]) == self.grid_side

        # Wait for the child process
        popen.wait()

    def eval_move(self):
        """Returns newx, newy, you_did_actually_move"""
        new_posx = self.posx + movex[self.dir]
        new_posy = self.posy + movey[self.dir]
        if 0 <= new_posx and new_posx < self.grid_side \
                and 0 <= new_posy and new_posy < self.grid_side:
            return new_posx, new_posy, True
        else:
            return self.posx, self.posy, False

    def execute_program(self, compilation):
        if compilation:
            if self.compilation_dir is None:
                self.compile_runner()
            self.execute_runner()
        else:
            self.execute_interpreter()

    def execute_interpreter(self):
        while self.execute_step():
            pass

    def print_info(self):
        print "  Position is (%d, %d); direction is %s" % \
            (self.posy, self.posx, dir_name[self.dir])
        if self.ip in self.labels_inv:
            print "  Instruction pointer is %d, labelled with %s" % \
                (self.ip, ", ".join(self.labels_inv[self.ip]))
        else:
            print "  Instruction pointer is %d, without labels" % \
                (self.ip)
        print "  Dump of the first 10 rows and columns of the grid"
        self.print_grid(min(10, self.grid_side))

    def print_grid(self, side):
        for y in xrange(side):
            for x in xrange(side):
                present = ' '
                if x == self.posx and y == self.posy:
                    present = '*'
                print '%s%02d ' % (present, self.grid[x][y]),
            print

    def execute_step(self):

        if self.debug:
            self.print_info()

        # Detect termination (IP past the end of the code)
        if self.ip >= len(self.code):
            return False

        # Get current instruction
        op_code, jump_to = self.code[self.ip]
        jumped = False
        halting = False

        # Execute the instruction
        if op_code == OP_LEFT:
            self.dir = (self.dir + 1) % 4

        elif op_code == OP_RIGHT:
            self.dir = (self.dir + 3) % 4

        elif op_code == OP_MOVE:
            self.posx, self.posy, moved = self.eval_move()

        elif op_code == OP_GET:
            if self.grid[self.posx][self.posy] > 0:
                self.grid[self.posx][self.posy] -= 1

        elif op_code == OP_PUT:
            if self.grid[self.posx][self.posy] < 15:
                self.grid[self.posx][self.posy] += 1

        elif op_code == OP_HALT:
            halting = True
            jumped = True

        elif op_code == OP_JUMP:
            self.ip = jump_to
            jumped = True

        elif op_code == OP_BORDERJ:
            newx, newy, moved = self.eval_move()
            if not moved:
                self.ip = jump_to
                jumped = True

        elif op_code == OP_PEBBLEJ:
            if self.grid[self.posx][self.posy] > 0:
                self.ip = jump_to
                jumped = True

        # Increment step_num, possibly update IP and check execution
        # length
        self.step_num += 1
        if not jumped:
            self.ip += 1
        if self.max_steps is not None and self.step_num == self.max_steps \
                and not halting:
            halting = True
            self.killed = True

        return not halting


def load_grid(fin, grid):
    for line in fin:
        line += '#'
        line, comment = line.split('#', 1)
        line = line.strip()
        if line == '':
            continue

        try:
            posy, posx, num = map(int, line.split(' '))
            if posx < 0 or posx >= len(grid):
                raise ParserException("Column index %d out of bound "
                                      "in line %s" % (posx, line))
            if posy < 0 or posy >= len(grid[posx]):
                raise ParserException("Row index %d out of bound in line %s" %
                                      (posy, line))
            if num < 0 or num >= 16:
                raise ParserException("Pebble number %d out of bound "
                                      "in line %s" % (num, line))
        except ValueError:
            raise ParserException("Could not parse grid line %s" % (line))
        grid[posx][posy] = num


def dump_grid(fout, grid):
    for posx in xrange(len(grid)):
        for posy in xrange(len(grid[posx])):
            if grid[posx][posy] != 0:
                fout.write("%d %d %d\n" % (posy, posx, grid[posx][posy]))


def main():
    parser = argparse.ArgumentParser(description="Odometer simulator")
    parser.add_argument('-s', '--side', dest='grid_side',
                        action='store', default=DEFAULT_GRID_SIDE,
                        type=int,
                        help="grid's side length (default: 256)")
    parser.add_argument('-g', '--grid', dest='grid_file',
                        action='store', default=None,
                        type=str,
                        help="grid description file (default: empty grid)")
    parser.add_argument('program',
                        action='store', type=str,
                        help="program to run")
    parser.add_argument('-d', '--debug',
                        action='store_true', default=False,
                        help="print debug information")
    parser.add_argument('-c', '--compile', dest='compile',
                        action='store_true', default=False,
                        help="compile the program instead of interpreting it")
    parser.add_argument('-m', '--max-steps', dest='max_steps',
                        action='store', default=None,
                        metavar='STEPS', type=int,
                        help="execute at most STEPS steps")
    parser.add_argument('-o', '--output', dest='output_file',
                        action='store', default=None,
                        metavar='OUTFILE', type=str,
                        help="after execution, dump grid to file OUTFILE")
    args = parser.parse_args()

    s = Simulation(args.grid_side,
                   debug=args.debug,
                   max_steps=args.max_steps)

    if args.grid_file is not None:
        with open(args.grid_file) as fin:
            load_grid(fin, s.grid)

    with open(args.program) as fin:
        try:
            s.load_program(fin)
        except ParserException, e:
            print "Could not parse source code:\n%s" % (e.message)
            sys.exit(1)

    s.execute_program(compilation=args.compile)

    if args.output_file is not None:
        with open(args.output_file, 'w') as fout:
            dump_grid(fout, s.grid)

    print
    print "FINAL STATUS"
    s.print_info()
    print "Programs has %d instructions" % (s.instruction_num)
    if s.killed:
        print "Execution took %d steps and was killed" % (s.step_num)
    else:
        print "Execution took %d steps" % (s.step_num)

if __name__ == '__main__':
    main()
