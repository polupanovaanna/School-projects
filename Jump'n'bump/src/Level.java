//Polupanova Anna 2020a
//class Level
//27.04.18
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Level {

	ArrayList<Wall> blocks = new ArrayList<Wall>();
	ArrayList<Rabbit> rabbits = new ArrayList<Rabbit>();
	ArrayList<Drawable> drawable = new ArrayList<Drawable>();
	ArrayList<Hitable> hitable = new ArrayList<Hitable>();
	static BufferedImage background;

	Level(String filename) throws IOException {
		Scanner sc = new Scanner(new File(filename));
		int numberOfBlocks = sc.nextInt();
		for (int i = 0; i < numberOfBlocks; i++) {
			blocks.add(new Wall(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.next()));
		}
		int numberOfRabbits = sc.nextInt();
		for (int i = 0; i < numberOfRabbits; i++) {
			rabbits.add(new Rabbit(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.next()));
		}
		background = ImageIO.read(new File("background.jpg"));
		Background background = new Background();
		Score score = new Score();
		hitable.addAll(rabbits);
		hitable.addAll(blocks);
		drawable.add(background);
		drawable.addAll(rabbits);
		drawable.addAll(blocks);
		drawable.add(score);
	}

}