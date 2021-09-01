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
		JPanel up = new JPanel(); // ������� ������
		JPanel left = new JPanel(); // ������� ������
		JPanel central = new JPanel(); // ������ � ��-���� ������������ � �����
		JPanel down = new JPanel(); // ������ ������ � ���������
		window.add(up, BorderLayout.NORTH);
		window.add(left, BorderLayout.WEST);
		window.add(down, BorderLayout.SOUTH);
		window.add(central, BorderLayout.CENTER);
		// �������
		up.setLayout(new FlowLayout());
		JPanel up1 = new JPanel(); // ����������� ��� �����
		JPanel up2 = new JPanel(); // ������������ ��� �����
		JPanel up3 = new JPanel(); // �����������
		up.add(up1);
		up.add(up2);
		up.add(up3);
		
		up1.setLayout(new GridLayout(2,1));
		JRadioButton mandelbrot = new JRadioButton("�����������");
		JRadioButton julia = new JRadioButton("�����");
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
		
		JButton repaint = new JButton("������������");
		up3.add(repaint);
		
		//������� ������
		left.setLayout(new GridLayout(2,1));
		JCheckBox uppanel = new JCheckBox("����� ���������");
		JCheckBox downpanel = new JCheckBox("���������������");
		left.add(uppanel);
		left.add(downpanel);
		
		//������ ������ 
		down.setLayout(new FlowLayout());
		down.add(new JLabel("x"));
		TextField x = new TextField("");
		down.add(x);
		down.add(new JLabel("y"));
		TextField y = new TextField("");
		down.add(y);
		down.add(new JLabel("�������"));
		TextField scale = new TextField("");
		down.add(scale);
		
		//������ � ������� �������
		
		
		
	

		window.setVisible(true);
	}

}
