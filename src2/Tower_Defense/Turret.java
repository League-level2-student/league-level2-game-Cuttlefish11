package Tower_Defense;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class Turret extends GameObject{

	Turret(int x2, int y2, int width2, int height2) {
		super(x2, y2, width2, height2);
	}
	void draw(Graphics g) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(x, y, width, height);
		}
	public TurretProjectile getProjectile() {
        return new TurretProjectile(x+width/2, y, 10, 10);
} 
}
