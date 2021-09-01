//Polupanova Anna 2020a
//class Main
//27.04.18
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class Main {
	static Level l;
	
	static final int windowWidth = 1000;
	static final int windowHeight = 700;

	static class GamePanel extends JPanel {

		@Override
		protected void paintComponent(Graphics arg0) {
			// TODO Auto-generated method stub
			super.paintComponent(arg0);
			Graphics2D g2d = (Graphics2D) arg0;
			for (Drawable d : l.drawable) {
				d.draw(g2d);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		l = new Level("level.txt");
		JFrame window = new JFrame("jump'n'bump");
		window.setSize(windowWidth, windowHeight);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel gamePanel = new GamePanel();
		window.add(gamePanel);
		gamePanel.setFocusable(true);
		window.setVisible(true);

		gamePanel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				for (int i = 0; i < l.rabbits.size(); i++) {
					l.rabbits.get(i).keyreleased(arg0.getKeyCode());
				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				for (int i = 0; i < l.rabbits.size(); i++) {
					l.rabbits.get(i).keypressed(arg0.getKeyCode());
				}

			}
		});
		Timer rabbittimer = new Timer(15, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < l.rabbits.size(); i++) {
					l.rabbits.get(i).update();
				}
				window.repaint();
			}
		});
		rabbittimer.start();

	}

}