package Tower_Defense;

import java.awt.Graphics;

import javax.swing.JFrame;

public class TowerDefense {
	JFrame frame;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	GamePanel gp = new GamePanel();

	TowerDefense() {
		frame = new JFrame();
		frame.addKeyListener(gp);
		frame.addMouseListener(gp);
	}

	void setup() {
		frame.add(gp);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		TowerDefense td = new TowerDefense();
		td.setup();
	}

}