import java.awt.Color;
import java.awt.Graphics2D;

public class Background implements Drawable {
	static final Color backgroundColor = Color.BLUE;

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(Main.l.background, 0, 0, null);

	}

}
