package Tower_Defense;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class Turret extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	 
	
	Turret(int x2, int y2, int width2, int height2) {
		super(x2, y2, width2, height2);
		if (needImage) {
		    loadImage ("Turret.jpeg");
		}

	}
	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
		g.setColor(Color.DARK_GRAY);
			g.fillRect(x, y, width, height);
		}
		}
	public TurretProjectile getProjectile() {
        return new TurretProjectile(x+width/2, y, 30, 15);
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
