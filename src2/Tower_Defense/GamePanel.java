package Tower_Defense;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Font titleFont;
	Font titlePage;
	Font endTitle;
	Font endPage;
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Timer frameDraw;
	Turret turret = new Turret(200, 200, 50, 50);//CHANGE X AND Y
	ObjectManager om = new ObjectManager(turret);
	Timer foeSpawn;

	GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		titlePage = new Font("Arial", Font.PLAIN, 20);
		endTitle = new Font("Arial", Font.PLAIN, 48);
		endPage = new Font("Arial", Font.PLAIN, 20);
		frameDraw = new Timer(1000/60,this);
		frameDraw.start();
		}

	void updateMenuState() {

	}

	void updateGameState() {
om.update();
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
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT);

		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Game Over", 115, 100);

		g.setFont(titlePage);
		g.setColor(Color.WHITE);
		//g.drawString("Money: " + om.money, 25, 25);
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
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
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
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } else {
		        currentState++;
		    }
		    if (currentState == GAME) {
				startGame();
				    } 
		    if (currentState == END) {
		   foeSpawn.stop();
		    } 
		} 
		

	}
	void startGame(){
		 foeSpawn = new Timer(1500 , om);
		    foeSpawn.start();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
