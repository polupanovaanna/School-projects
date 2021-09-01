import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class Launcher {
	static Random r = new Random();
	static int k = 0;// счетчик который нужен в таймере бонуса 1
	static int p = 0; // счетчик который нужен в таймере бонуса 2
	static int Ballsize = 40; // диаметр м€ча
	static double Ballx = 0;
	static double Bally = 0;
	static double Ballvx = 0;
	static double Ballvy = 0;
	static int Mousex;
	static int Mousey;
	static double RBM = 7; // рассто€ние между курсором и шариком
	static double axBallCursor; // ускорение м€ча по оси X из-за прит€жени€ к курсору
	static double ayBallCursor; // ускорение м€ча по оси Y из-за прит€жени€ к курсору
	static int gravitationconstatntBig = 50; // константа прит€жени€ между м€чом и большой ракетой
	static int gravitationconstantSmall = 20; // константа прит€жени€ между м€чом и малой ракетой
	static int gravitationconstantMouse = 10; // константа прит€жени€ между курсором и м€чиком
	static int constantv = 0; // константа, на которую увеличиваетс€ скорость ракет каждые 10 секунд
	static int windowwidth = 1000; // ширина окна
	static int windowheight = 600; // высота окна
	static int lives = 3; // количество жизней
	static int time = 100; // движение ракет (мс)
	static int score = 0;//очки
	static boolean crushed = false;// показывает врезалс€ ли м€ч в бонус

	static int n = 9; // максимальное количество ракет в игре одновременно
	static Rocket[] rockets = new Rocket[n];

	static Rocket rocket1 = new Rocket();
	static Rocket rocket2 = new Rocket();
	static BRocket bigrocket = new BRocket();

	static Bonus bonus = new Bonus();

	public static void move(Rocket rocket) {
		rocket.Y = rocket.Y + rocket.vY + constantv;
		rocket.X = rocket.X + rocket.vX;
	}

	public static void move(Bonus bonus) {
		bonus.Y = bonus.Y + bonus.vY + constantv;
	}

	public static void crush(Rocket rocket) {
		if ((rocket.existance == 1) && (Ballx > rocket.X - Ballsize) && (Ballx < rocket.X + rocket.width)
				&& (Bally < rocket.Y + rocket.height) && (Bally > rocket.Y - Ballsize)) {
			lives = lives - 1;
			rocket.existance = 0;
			Ballvx = 0;
			Ballvy = 0;

		}
	}

	public static void Bcrush(BRocket rocket) {
		if ((rocket.existance == 1) && (Ballx > rocket.X - Ballsize) && (Ballx < rocket.X + rocket.width)
				&& (Bally < rocket.Y + rocket.height) && (Bally > rocket.Y - Ballsize)) {
			lives = lives - 1;
			rocket.existance = 0;
			Ballvx = 0;
			Ballvy = 0;

		}
	}

	public static void crush(Bonus bonus) {
		if ((bonus.existance == 1) && (Ballx > bonus.X - Ballsize) && (Ballx < bonus.X + bonus.width)
				&& (Bally < bonus.Y + bonus.height) && (Bally > bonus.Y - Ballsize)) {

			if (bonus.type == 2) {
				lives = lives + 1;
				score = score + 10;

			}
			if (bonus.type == 1) {
				constantv = 0;
				score = score + 10;

			}
			if (bonus.type == 2) {
				gravitationconstantSmall = r.nextInt(30);
				score = score + 10;

			}

			bonus.existance = 0;
			Ballvx = 0;
			Ballvy = 0;

		}
	}

	public static double getaccelerationx(Rocket rocket) {
		double axBallRocket = 0;
		if (rocket.existance == 1) {
			double r = Math
					.sqrt((Ballx - rocket.X) * (Ballx - rocket.X) + (Bally - rocket.Y) * (Bally - rocket.Y) + 0.1);
			axBallRocket = gravitationconstantSmall * (Ballx - rocket.X) / r / r;
		}
		return axBallRocket;
	}

	public static double getaccelerationy(Rocket rocket) {
		double ayBallRocket = 0;
		if (rocket.existance == 1) {
			double r = Math
					.sqrt((Ballx - rocket.X) * (Ballx - rocket.X) + (Bally - rocket.Y) * (Bally - rocket.Y) + 0.1);
			ayBallRocket = gravitationconstantSmall * (Bally - rocket.Y) / r / r;
		}
		return ayBallRocket;
	}

	static class GamePanel extends JPanel {

		protected void paintComponent(Graphics arg0) {
			super.paintComponent(arg0);
			Graphics2D g2d = (Graphics2D) arg0;
			g2d.setColor(Color.black);
			g2d.fillRect(0, 0, windowwidth, windowheight);
			g2d.setColor(new Color(254, 40, 162));
			int k = 10;
			for (int i = 0; i < lives; i++) {
				g2d.fillOval(windowwidth - 40, k, 15, 15);
				k = k + 20;
			}
			g2d.setColor(Color.CYAN);
			g2d.fillOval((int) Ballx, (int) Bally, Ballsize, Ballsize);
			g2d.setColor(Color.RED);
			for (int i = 0; i < rockets.length; i++) {
				if (rockets[i].existance == 1) {
					g2d.fillRect(rockets[i].X, rockets[i].Y, rockets[i].width, rockets[i].height);
				}
			}
			if (bigrocket.existance == 1) {
				g2d.fillRect(bigrocket.X, bigrocket.Y, bigrocket.width, bigrocket.height);
			}
			if (rocket1.existance == 1) {
				g2d.fillRect(rocket1.X, rocket1.Y, rocket1.width, rocket1.height);
			}
			if (rocket2.existance == 1) {
				g2d.fillRect(rocket2.X, rocket2.Y, rocket2.width, rocket2.height);
			}
			g2d.setColor(Color.WHITE);
			g2d.drawString(Integer.toString(score), 10, windowheight - 50 );

			if (bonus.existance == 1) {
				if (bonus.type == 1) {
					g2d.setColor(Color.GREEN);
				}
				if (bonus.type == 2) {
					g2d.setColor(Color.PINK);
				}

				if (bonus.type == 3) {
					g2d.setColor(Color.ORANGE);
				}
				g2d.fillRect(bonus.X, bonus.Y, bonus.width, bonus.height);
			}
			
		}

	}

	static class FinalPanel extends JPanel {

		@Override
		protected void paintComponent(Graphics arg0) {

			super.paintComponent(arg0);
			Graphics2D g2d = (Graphics2D) arg0;
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, windowwidth, windowheight);
			g2d.setColor(Color.WHITE);
			g2d.drawString("¬џ ѕ–ќ»√–јЋ»", windowwidth / 2 - 100, windowheight / 2 - 100);
			g2d.drawString("¬јЎ –≈«”Ћ№“ј“:", windowwidth / 2 - 100, windowheight / 2 - 85);
			g2d.drawString(Integer.toString(score), windowwidth / 2 + 2 , windowheight / 2 - 85);
		}

	}

	public static void main(String[] args) {

		for (int i = 0; i < n; i++) {
			rockets[i] = new Rocket();
		}

		JFrame window = new JFrame("космос");
		window.setSize(windowwidth, windowheight);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel startPanel = new JPanel();
		JPanel gamePanel = new GamePanel();
		JPanel finalPanel = new FinalPanel();
		//window.add(startPanel);
		window.add(gamePanel);
		//JButton start = new JButton("start");

		//startPanel.add(start);

		window.setVisible(true);

		/**start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.remove(startPanel);
				window.add(gamePanel);
				window.repaint();
				window.setVisible(true);

			}
		});
		*/
		gamePanel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				Mousex = e.getX();
				Mousey = e.getY();
				window.repaint();
			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		Timer scoretimer = new Timer (100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				score = score + 1;
				
			}
		});
		scoretimer.start();

		Timer balltimer = new Timer(50, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				RBM = Math.sqrt((Ballx - Mousex) * (Ballx - Mousex) + (Bally - Mousey) * (Bally - Mousey) + 0.1);
				axBallCursor = gravitationconstantMouse * (Ballx - Mousex) / RBM / RBM;
				ayBallCursor = gravitationconstantMouse * (Bally - Mousey) / RBM / RBM;
				Ballvx = Ballvx - axBallCursor;
				for (int i = 0; i < rockets.length; i++) {
					Ballvx = Ballvx - getaccelerationx(rockets[i]);
				}

				Ballvy = Ballvy - ayBallCursor;
				for (int i = 0; i < rockets.length; i++) {
					Ballvy = Ballvy - getaccelerationy(rockets[i]);
				}

				;
				if ((Ballx < 0) || (Ballx > window.getWidth() - Ballsize)) {
					Ballvx = -Ballvx;

				}
				if ((Bally < 0) || (Bally > window.getHeight() - Ballsize * 2)) {
					Ballvy = -Ballvy;

				}

				Ballx = Ballx + Ballvx;
				Bally = Bally + Ballvy;
				gamePanel.repaint();
				window.repaint();
			}
		});
		balltimer.start();

		Timer bigrocketappeartimer = new Timer(20000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (bigrocket.existance == 0) {
					bigrocket.existance = 1;
					bigrocket.X = r.nextInt(windowwidth - 100);
				}
			}
		});
		bigrocketappeartimer.start();

		Timer rocketappeartimer = new Timer(3500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < rockets.length; i++) {
					if (rockets[i].existance == 0) {
						rockets[i].existance = 1;
						rockets[i].X = r.nextInt(windowwidth - 100);
						break;
					}
				}

			}
		});
		rocketappeartimer.start();
		Timer rocketmovetimer = new Timer(time, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < rockets.length; i++) {
					if (rockets[i].existance == 1) {
						move(rockets[i]);
					}
				}
				for (int i = 0; i < rockets.length; i++) {
					if (rockets[i].Y > windowheight) {
						rockets[i].existance = 0;
						rockets[i].Y = 0;
					}
				}
				if (bigrocket.Y >= 200 - bigrocket.height) {
					bigrocket.existance = 0;
					bigrocket.Y = 0;
					rocket1.existance = 1;
					rocket1.Y = 200;
					rocket1.X = bigrocket.X;
					rocket2.existance = 1;
					rocket2.Y = 200;
					rocket2.X = bigrocket.X;
					rocket1.vX = -2;
					rocket2.vX = 2;
				}
				if (bigrocket.existance == 1) {
					bigrocket.move();
				}
				
				if (rocket1.existance == 1) {
					move(rocket1);
				}
				if (rocket2.existance == 1) {
					move(rocket2);
				}
				if (rocket1.Y > windowheight) {
					rocket1.existance = 0;
					rocket1.Y = 0;
				}
				if (rocket2.Y > windowheight) {
					rocket2.existance = 0;
					rocket2.Y = 0;
				}

			}
		});
		rocketmovetimer.start();

		Timer bonusappeartimer = new Timer(15000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (bonus.existance == 0) {
					bonus.existance = 1;
					bonus.type = r.nextInt(3) + 1;
					bonus.Y = 0;
					bonus.X = r.nextInt(windowwidth - 50);
				}

			}
		});
		bonusappeartimer.start();

		Timer bonusmovetimer = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (bonus.existance == 1) {
					move(bonus);
				}
				if (bonus.Y > windowheight) {
					bonus.existance = 0;
					bonus.Y = 0;
				}

			}
		});
		bonusmovetimer.start();

		Timer rocketcollision = new Timer(300, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < rockets.length; i++) {
					crush(rockets[i]);
				}
				Bcrush(bigrocket);
				crush(rocket1);
				crush(rocket2);

			}
		});
		rocketcollision.start();

		Timer bonuscollision = new Timer(300, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				crush(bonus);
			}
		});
		bonuscollision.start();
		Timer acceleration = new Timer(5000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				constantv = constantv + 1;

			}
		});
		acceleration.start();
		Timer game = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (lives <= 0) {
					window.remove(gamePanel);
					window.add(finalPanel);
					window.setVisible(true);
					balltimer.stop();
					rocketappeartimer.stop();
					rocketmovetimer.stop();
					bonusappeartimer.stop();
					bonusmovetimer.stop();
					rocketcollision.stop();
					bonuscollision.stop();
					acceleration.stop();
					bigrocketappeartimer.stop();

				}
				if (constantv > 9) {
					acceleration.stop();
				}
				if (constantv == 0) {
					acceleration.start();
				}

			}
		});
		game.start();

	}

}