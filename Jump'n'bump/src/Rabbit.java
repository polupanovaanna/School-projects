
//Polupanova Anna 2020a
//class Rabbit
//27.04.18
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Rabbit extends Hitable implements Drawable {
	static final int size = 40;
	static final int horisontalSpeed = 2;
	static final int verticalSpeed = 10;
	static final double acceleration = 0.2;
	static final Color rabbitcolor = Color.BLACK;
	Random r = new Random();

	double vx;
	double vy;
	int score;
	int keyleft;
	int keyright;
	int keyup;
	boolean isUpPushed = false;
	boolean isDownPushed = false;
	boolean isLeftPushed = false;
	boolean isRightPushed = false;
	BufferedImage image;

	Rabbit(int left, int up, int right, String filename) throws IOException {
		keyleft = left;
		keyup = up;
		keyright = right;
		width = size;
		height = size;
		x = r.nextInt(400);
		image = ImageIO.read(new File(filename));
	}

	void keypressed(int key) {
		if ((key == keyleft) && (isLeftPushed == false)) {
			vx = -horisontalSpeed;
		}
		if ((key == keyright) && (isRightPushed == false)) {
			vx = horisontalSpeed;
		}
		if ((key == keyup) && (isDownPushed == true)) {

			vy = -verticalSpeed;

		}

	}

	void keyreleased(int key) {
		if (key == keyleft) {
			vx = 0;
		}
		if (key == keyright) {
			vx = 0;
		}

	}

	public void update() {
		isUpPushed = false;
		isDownPushed = false;
		isLeftPushed = false;
		isRightPushed = false;
		for (Hitable h : Main.l.hitable) {
			if (h != this) {
				if ((h.hitTest(x, y, size) == north) && (vy >= 0)) {
					isDownPushed = true;
					if (h.getClass() == Rabbit.class) {
						h.x = 0;
						h.y = 0;
						score = score + 1;
					}
				}
				if (((h.hitTest(x, y, size) == east) && (vx < 0)) || (x < 0)) {
					isLeftPushed = true;
				}
				if ((h.hitTest(x, y, size) == south) && (vy < 0)) {
					isUpPushed = true;
				}
				if (((h.hitTest(x, y, size) == west) && (vx >= 0)) || (x > Main.windowWidth - size)) {
					isRightPushed = true;
				}
			}
		}
		if ((isDownPushed == true)) {
			vy = 0;
		} else {
			if (isUpPushed == true) {
				vy = 0;
			}
			vy = vy + acceleration;
			y = y + vy;
		}
		if ((isLeftPushed == true) && (vx < 0)) {
			vx = 0;
		}
		if ((isRightPushed == true) && (vx > 0)) {
			vx = 0;
		}
		x = x + vx;

	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, (int) x, (int) y, (int) x + size, (int) y + size, 0, 0, 300, 300, null);
	}

}
