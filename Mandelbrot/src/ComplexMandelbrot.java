
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ComplexMandelbrot {
	public static double[] sum(double[] z1, double[] z2) {
		double[] arr = new double[2];
		arr[0] = z1[0] + z2[0];
		arr[1] = z1[1] + z2[1];
		return arr;
	}

	public static double[] mult(double[] z1, double[] z2) {
		double[] arr = new double[2];
		arr[0] = z1[0] * z2[0] - z1[1] * z2[1];
		arr[1] = z1[0] * z2[1] + z1[1] * z2[0];
		return arr;
	}

	public static double abs(double[] z) {
		double abs = Math.sqrt(z[0] * z[0] + z[1] * z[1]);
		return abs;
	}

	static class MyPanel extends JPanel {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
		 */
		@Override
		protected void paintComponent(Graphics arg0) {
			super.paintComponent(arg0);
			Random r = new Random();

			Graphics2D g2d = (Graphics2D) arg0;
			double w = getWidth();
			double h = getHeight();
			int n = 2;
			double centerX = w / 2;
			double centerY = h / 2;
			double valueX = 2 * n / w;
			double valueY = 2 * n / h;
			// цикл бегает по всем x,y, координата n-го x = (x - centerX)*valueX
			// координата n-го y = (-y + centerY)*valueY
			double x = 0;
			double y = 0;
			double x1;
			double y1;

			// x - k
			// y - j
			// программа идет сверху вниз -> слева направо из левого верхнего угла
			for (int k = 0; k < w; k += 1) {
				for (int j = 0; j < h; j += 1) {
					boolean flag = false;
					int counter = 0;
					x = (k - centerX) * valueX;
					y = (-j + centerY) * valueY;
					double p = x;
					double q = y;
					double[] z0 = new double[2];
					z0[0] = p;
					z0[1] = q;
					double z1[] = new double[2];
					z1[0] = p;
					z1[1] = q;
					double[] constant = new double[2];
					constant[0] = 0.28;
					constant[1] = 0.0113;
					for (int i = 0; i < 250; i++) {
						if (abs(z1) > 2) {
							flag = true;
							if (counter < 10) {
								g2d.setColor(new Color(0, counter * 20, 0));
							} else {
								g2d.setColor(new Color(counter, Math.abs(counter - 250), Math.abs(counter - 100)));
							}
							g2d.fillRect(k, j, 1, 1);
							break;
						} else {
							z1 = sum(mult(z1, z1), constant);
							counter = counter + 1;
						}
					}
					if (flag == false) {
						g2d.setColor(Color.BLACK);
						g2d.fillRect(k, j, 1, 1);
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random r = new Random();

		int height = 500;
		int width = 500;
		JFrame window = new JFrame("graphic");
		window.setSize(width, height);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.add(new MyPanel());
		window.setVisible(true);

	}

}
