
//Polupanova Anna 2020a
//class Wall
//27.04.18
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall extends Hitable implements Drawable {
	static final Color wallColor = Color.GREEN;
	BufferedImage blocks;

	Wall(int x0, int y0, int w, int h, String filename) throws IOException {
		x = x0;
		y = y0;
		width = w;
		height = h;
		blocks = ImageIO.read(new File(filename));
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(blocks, (int)x, (int)y, (int)x + width, (int)y + height, 0, 0, 1000, 1000, null);
	}

}