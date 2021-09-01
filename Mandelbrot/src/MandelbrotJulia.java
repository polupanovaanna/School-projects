
//Polupanova Anna 2020a
//Mandelbrot&Julia panel
//02.03.18
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

public class MandelbrotJulia {

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

	static class MandelbrotPanel extends JPanel {
		static double n = 2;
		static double x0 = 0;
		static double y0 = 0;

		public void scan(double xx, double yy, double nn) {
			x0 = xx;
			y0 = yy;
			n = nn;

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
		 */

		protected void paintComponent(Graphics arg0) {
			super.paintComponent(arg0);
			Random r = new Random();

			Graphics2D g2d = (Graphics2D) arg0;
			double w = getWidth();
			double h = getHeight();

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
					x = (k - centerX) * valueX + x0;
					y = (-j + centerY) * valueY + y0;
					double p = x;
					double q = y;
					double[] z0 = new double[2];
					z0[0] = p;
					z0[1] = q;
					double z1[] = new double[2];
					z1[0] = p;
					z1[1] = q;
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
							z1 = sum(mult(z1, z1), z0);
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

	static class JuliaPanel extends JPanel {
		static double n = 2;
		static double x0 = 0;
		static double y0 = 0;
		static double constant1 = 0.28;
		static double constant2 = 0.0133;

		public static void scan(double xx, double yy, double nn, double cc1, double cc2) {
			n = nn;
			x0 = xx;
			y0 = yy;
			constant1 = cc1;
			constant2 = cc2;
		}

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
					x = (k - centerX) * valueX + x0;
					y = (-j + centerY) * valueY + y0;
					double p = x;
					double q = y;
					double[] z0 = new double[2];
					z0[0] = p;
					z0[1] = q;
					double z1[] = new double[2];
					z1[0] = p;
					z1[1] = q;
					double[] constant = new double[2];
					constant[0] = constant1;
					constant[1] = constant2;
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
		int height = 500;
		int width = 500;
		JFrame window = new JFrame("graphic");
		window.setSize(width, height);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel up = new JPanel(); // верхняя панель
		JPanel left = new JPanel(); // боковая панель
		JPanel central = new JPanel(); // панель с мн-вами мандельброта и жулиа
		JPanel down = new JPanel(); // нижняя панель с масштабом
		window.add(up, BorderLayout.NORTH);
		window.add(left, BorderLayout.WEST);
		window.add(down, BorderLayout.SOUTH);
		window.add(central, BorderLayout.CENTER);
		// Верхняя
		up.setLayout(new FlowLayout());
		JPanel up1 = new JPanel(); // Мандельброт или Жулиа
		JPanel up2 = new JPanel(); // коэффициенты для жулиа
		JPanel up3 = new JPanel(); // перерисовка
		up.add(up1);
		up.add(up2);
		up.add(up3);

		up1.setLayout(new GridLayout(2, 1));
		JRadioButton mandelbrotbutton = new JRadioButton("Мандельброт");
		JRadioButton juliabutton = new JRadioButton("Жулиа");
		up1.add(mandelbrotbutton);
		up1.add(juliabutton);
		ButtonGroup bg = new ButtonGroup();
		bg.add(mandelbrotbutton);
		bg.add(juliabutton);

		up2.setLayout(new GridLayout(2, 2));
		TextField alpha = new TextField("0.28");
		TextField beta = new TextField("0.0133");
		up2.add(new JLabel("a"));
		up2.add(alpha);
		up2.add(new JLabel("b"));
		up2.add(beta);

		JButton repaint = new JButton("перерисовать");
		up3.add(repaint);
		up.setVisible(false);

		// боковая панель
		left.setLayout(new GridLayout(2, 1));
		JCheckBox uppanel = new JCheckBox("выбор множества");
		JCheckBox downpanel = new JCheckBox("масштабирование");
		left.add(uppanel);
		left.add(downpanel);

		// нижняя панель
		down.setLayout(new FlowLayout());
		down.add(new JLabel("x"));
		TextField x1 = new TextField("0.0");
		down.add(x1);
		down.add(new JLabel("y"));
		TextField y1 = new TextField("0.0");
		down.add(y1);
		down.add(new JLabel("Масштаб"));
		TextField scale = new TextField("2.0");
		down.add(scale);
		down.setVisible(false);

		// центральная панель
		central.setLayout(new GridLayout());
		JuliaPanel julia = new JuliaPanel();
		MandelbrotPanel mandelbrot = new MandelbrotPanel();

		// работа с верхней панелью
		mandelbrotbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				central.remove(julia);
				double x = Double.parseDouble(x1.getText());
				double y = Double.parseDouble(y1.getText());
				double n = Double.parseDouble(scale.getText());
				mandelbrot.scan(x, y, n);
				central.add(mandelbrot);
				up2.setVisible(false);
				window.repaint();
			}
		});
		juliabutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				central.remove(mandelbrot);
				double x = Double.parseDouble(x1.getText());
				double y = Double.parseDouble(y1.getText());
				double n = Double.parseDouble(scale.getText());
				double constant1 = Double.parseDouble(alpha.getText());
				double constant2 = Double.parseDouble(beta.getText());
				julia.scan(x, y, n, constant1, constant2);
				central.add(julia);
				up2.setVisible(true);
				julia.repaint();
				window.repaint();

			}
		});

		repaint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				double x = Double.parseDouble(x1.getText());
				double y = Double.parseDouble(y1.getText());
				double n = Double.parseDouble(scale.getText());
				double constant1 = Double.parseDouble(alpha.getText());
				double constant2 = Double.parseDouble(beta.getText());
				julia.scan(x, y, n, constant1, constant2);
				mandelbrot.scan(x, y, n);
				julia.repaint();
				mandelbrot.repaint();

			}
		});

		// работа с боковой панелью

		uppanel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (uppanel.isSelected()) {
					up.setVisible(true);
				} else {
					up.setVisible(false);
				}

			}
		});

		downpanel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (downpanel.isSelected()) {
					down.setVisible(true);
				} else {
					down.setVisible(false);
				}

			}
		});

		window.setVisible(true);

	}

}
