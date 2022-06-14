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
		trollSpawn = new Timer(1700, this);
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
			}
		}
		if (om.money >= 300) {
			currentState = MENU;
			foeSpawn.stop();
			trollSpawn.stop();
			om = new ObjectManager();
			JOptionPane.showMessageDialog(null, "You have prevailed against your foes. Victory!");
		}
		om.update();
		if (om.time < 1400 && ts == false) {
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
		g.setColor(Color.white);
		g.fillRect(0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT);
		om.draw(g);

		g.setFont(titlePage);
		g.setColor(Color.black);
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
		g.drawString("You built towers(good job)", 115, 400);
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
				om.money = 25;
			}
			if (currentState == END) {
				foeSpawn.stop();
				om = new ObjectManager();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			JOptionPane.showMessageDialog(null,
					          "In this game, the goal is to have no foes pass\n"
							+ "the end of the window. To acheive this you must\n"
							+ "place towers. Each standard tower costs 25 money\n"
							+ "and shoots towards the left. Magic towers cost 40\n"
							+ "money and are stronger. Click q to place a magic\n"
							+ "tower. Killing an enemy gives you money and you\n"
							+ "start with 25 money. Good luck!");
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
		if (e.getButton() == e.BUTTON1)
			om.addTurret(e.getX(), e.getY());
		if (e.getButton() == e.BUTTON2)
			om.addTower(e.getX(), e.getY());
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
}
