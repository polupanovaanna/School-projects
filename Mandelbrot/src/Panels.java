import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

public class Panels {

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
		
		up1.setLayout(new GridLayout(2,1));
		JRadioButton mandelbrot = new JRadioButton("Мандельброт");
		JRadioButton julia = new JRadioButton("Жулиа");
		up1.add(mandelbrot);
		up1.add(julia);
		ButtonGroup bg = new ButtonGroup();
		bg.add(mandelbrot);
		bg.add(julia);

		up2.setLayout(new GridLayout(2,2));
		TextField alpha = new TextField("");
		TextField beta = new TextField("");
		up2.add(new JLabel("a"));
		up2.add(alpha);
		up2.add(new JLabel("b"));
		up2.add(beta);
		
		JButton repaint = new JButton("перерисовать");
		up3.add(repaint);
		
		//боковая панель
		left.setLayout(new GridLayout(2,1));
		JCheckBox uppanel = new JCheckBox("выбор множества");
		JCheckBox downpanel = new JCheckBox("масштабирование");
		left.add(uppanel);
		left.add(downpanel);
		
		//нижняя панель 
		down.setLayout(new FlowLayout());
		down.add(new JLabel("x"));
		TextField x = new TextField("");
		down.add(x);
		down.add(new JLabel("y"));
		TextField y = new TextField("");
		down.add(y);
		down.add(new JLabel("Масштаб"));
		TextField scale = new TextField("");
		down.add(scale);
		
		//работа с верхней панелью
		
		
		
	

		window.setVisible(true);
	}

}
