package contest.misc;

import java.util.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Test {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static private final String INPUT_FILTER = "C:/Users/Jeffrey/Desktop/filter1.jpg";
	static private final String OUTPUT_TEXT = "C:/Users/Jeffrey/Desktop/filter2.txt";
	static private final String OUTPUT_FILTER = "C:/Users/Jeffrey/Desktop/filter2.jpg";
	static int WIDTH;
	static int HEIGHT;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader(OUTPUT_TEXT));
		//out = new PrintWriter(new FileWriter("out.txt"));
		
		BufferedImage image = ImageIO.read(new File(INPUT_FILTER));
		WIDTH = image.getWidth();
		HEIGHT = image.getHeight();
		BufferedImage res = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		int min = 255;
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				min = Math.min(min, getRed(image.getRGB(i, j)));
			}
		}

		
		PrintWriter filter = new PrintWriter(new File(OUTPUT_TEXT));
		filter.printf("%d\n%d\n", WIDTH, HEIGHT);
//		min *= 1.25;
		System.out.println(min);
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				int newHue = (int) (255 * (((double)(255 - getRed(image.getRGB(i, j))) / (255 - min))));
			
				filter.print(Math.max(0, (255 - newHue)) + (j == HEIGHT - 1 ? "" : " "));
				res.setRGB(i, j, rgbToInt(Math.max(0, 255 - newHue), Math.max(0, 255 - newHue), Math.max(0, 255 - newHue)));
				assert(getRed(image.getRGB(i, j)) == getGreen(image.getRGB(i, j)) && getGreen(image.getRGB(i, j)) == getBlue(image.getRGB(i, j)));
			}
			filter.println();
		}

		
		
		filter.close();
		ImageIO.write(res, "jpg", new File(OUTPUT_FILTER));
		ImageIO.write(scale(res, 0.5), "jpg", new File("C:/Users/Jeffrey/Desktop/scale.jpg"));
		out.close();
	}

	static BufferedImage scale (BufferedImage img, double ratio) {
		int WIDTH = img.getWidth();
		int HEIGHT = img.getHeight();
		int NWIDTH = (int)(WIDTH * ratio);
		int NHEIGHT = (int)(HEIGHT * ratio);
		BufferedImage ret = new BufferedImage(NWIDTH, NHEIGHT, BufferedImage.TYPE_INT_RGB);
		
		double[][] weight = new double[NWIDTH][NHEIGHT];
		double[][] sum = new double[NWIDTH][NHEIGHT];

		for (int i = 0; i < NWIDTH; i++)
			Arrays.fill(sum[i], 255);
		
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				double nx = i * ratio;
				double ny = j * ratio;
				
				int x1 = (int)(nx);
				int x2 = (int)(nx + 1);
				
				int y1 = (int)(ny);
				int y2 = (int)(ny + 1);
				
				if (x1 < NWIDTH && y1 < NHEIGHT) {
					double dist = getDist((1 - Math.abs(nx - x1)), (1 - Math.abs(ny - y1)));
					weight[x1][y1] += dist;
					sum[x1][y1] += dist * getRed(img.getRGB(i, j));
				} 
				
				if (x1 < NWIDTH && y2 < NHEIGHT) {
					double dist = getDist((1 - Math.abs(nx - x1)), (1 - Math.abs(ny - y2)));
					weight[x1][y2] += dist;
					sum[x1][y2] += dist * getRed(img.getRGB(i, j));
				} 
				
				if (x2 < NWIDTH && y1 < NHEIGHT) {
					double dist = getDist((1 - Math.abs(nx - x2)), (1 - Math.abs(ny - y1)));
					weight[x2][y1] += dist;
					sum[x2][y1] += dist * getRed(img.getRGB(i, j));
				} 
				
				if (x2 < NWIDTH && y2 < NHEIGHT) {
					double dist = getDist((1 - Math.abs(nx - x2)), (1 - Math.abs(ny - y2)));
					weight[x2][y2] += dist;
					sum[x2][y2] += dist * getRed(img.getRGB(i, j));
				}
			}
		}
		
		for (int i = 0; i < NWIDTH; i++) {
			for (int j = 0; j < NHEIGHT; j++) {
				sum[i][j] /= weight[i][j];
				if (sum[i][j] > 225)
					sum[i][j] = 255;
//				System.out.println(sum[i][j] + " " + weight[i][j]);
				ret.setRGB(i, j, rgbToInt((int)sum[i][j], (int)sum[i][j], (int)sum[i][j]));
			}
		}
		
		return ret;
	}
	
	static double getDist (double x, double y) {
		double dist = Math.sqrt(x * x + y * y);
		if (dist < 0.2)
			return 0;
		return dist;
	}
	
	static int rgbToInt(int r, int g, int b){
		int a = 255;
		return (((a << 8) + r << 8) + g << 8) + b;
	}
	
	static int getRed (int color) {
		return (color >>> 16) & 0xFF;
	}
	
	static int getGreen (int color) {
		return (color >>>  8) & 0xFF;
	}
	
	static int getBlue (int color) {
		return (color >>>  0) & 0xFF;
	}
	
	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong () throws IOException {
		return Long.parseLong(next());
	}

	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}

