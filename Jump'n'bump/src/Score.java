
//Polupanova Anna
//Class score
//02.05.18
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Score implements Drawable {
	public Color scoreColor = Color.WHITE;

	@Override
	public void draw(Graphics2D g2d) {
		int j = 40;
		int i = 1;
		for (Rabbit r : Main.l.rabbits) {
			g2d.setColor(scoreColor);
			g2d.setFont(new Font("Courier New", 1, 17));
			g2d.drawString("rabbit" +" " + Integer.toString(i) +" - "+ Integer.toString(r.score), 800, j);
			i = i + 1;
			j = j + 20;
		}

	}

}