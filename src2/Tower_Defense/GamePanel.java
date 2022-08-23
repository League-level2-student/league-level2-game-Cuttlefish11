package Tower_Defense;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	Font titleFont;
	Font titlePage;
	Font endTitle;
	Font endPage;
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Timer frameDraw;
	ObjectManager om = new ObjectManager();
	static Timer foeSpawn;
	static Timer trollSpawn;
	boolean ts = false;

	GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		titlePage = new Font("Arial", Font.PLAIN, 20);
		endTitle = new Font("Arial", Font.PLAIN, 48);
		endPage = new Font("Arial", Font.PLAIN, 20);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		trollSpawn = new Timer(1700, om);
		if (needImage) {
			loadImage("forest.jpg");
		}
	}

	void updateMenuState() {

	}

	void updateGameState() {
		for (Foe foe : om.foes) {
			if (foe.y > TowerDefense.HEIGHT) {
				currentState = END;
				foeSpawn.stop();
				trollSpawn.stop();
				om = new ObjectManager();
				ts = false;
				trollSpawn = new Timer(1700, om);
				return;
			}
		}
		if (om.money >= 300) {
			currentState = MENU;
			foeSpawn.stop();
			trollSpawn.stop();
			om = new ObjectManager();
			JOptionPane.showMessageDialog(null, "You have prevailed against your foes. Victory!");
			trollSpawn = new Timer(1600, om);
			ts = false;
			return;
		}
		om.update();
		if (om.time <= 1500 && ts == false) {
			trollSpawn.start();
			ts = true;
		}
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT);

		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Tower Defense", 80, 100);

		g.setFont(titlePage);
		g.setColor(Color.WHITE);
		g.drawString("Press ENTER to start", 115, 400);
		g.drawString("Press SPACE for instructions", 115, 500);
	}

	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT, null);
			om.draw(g);
		} else {
			g.setColor(Color.white);
			g.fillRect(0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT);
			om.draw(g);
		}

		g.setFont(titlePage);
		g.setColor(Color.red);
		g.drawString("Money: " + om.money, 25, 25);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT);

		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Game Over", 115, 100);

		g.setFont(titlePage);
		g.setColor(Color.WHITE);
		g.drawString("You lost. Try to do better next time!", 115, 400);
		g.drawString("Press ENTER to restart", 115, 500);
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				currentState = MENU;
			} else {
				currentState++;
			}
			if (currentState == GAME) {
				startGame();
				om.money = 30;
trollSpawn = new Timer(1700, om);
			}
			if (currentState == END) {
				foeSpawn.stop();
				om = new ObjectManager();
				
			}
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			JOptionPane.showMessageDialog(null, "Bloodthirsty monsters are invading the pristine\n"
					+ "forest! Your goal is to have no foe pass the end\n"
					+ "of the forest. To acheive this you must place\n"
					+ "towers. Each standard tower costs 25 money and\n"
					+ "shoots left. Magic towers cost 40 money and shoot\n"
					+ "diagonally to the left. When you click, choose\n"
					+ "your tower. Killing an enemy gives you money and\n" + "you start with 30 money. Good luck!");
		}
	}

	void startGame() {
		foeSpawn = new Timer(1500, om);
		foeSpawn.start();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (currentState == GAME) {
			String type = JOptionPane.showInputDialog(
					"Do you want a archer tower (cost 25, type in 'a')\n" + "or a magic tower (cost 35, type in 'm')?");
			if (type.equals("a")) {
				om.addTurret(e.getX(), e.getY());
			} else if (type.equals("m")) {
				om.addTower(e.getX(), e.getY());
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}
}
