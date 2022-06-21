package Tower_Defense;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class Turret extends GameObject{
	public BufferedImage image;
	public boolean needImage = true;
	public boolean gotImage = false;	 
	int projectileSpeed = 3;
	String image2 = "Turret.jpeg";
	public boolean isTower;
	
	Turret(int x2, int y2, int width2, int height2) {
		super(x2, y2, width2, height2);
		if (needImage) {
		    loadImage (image2);
		}
	}
	Turret(int x3, int y3, int width3, int height3, int speed, String image, boolean isTower){
		super(x3, y3, width3, height3);
		projectileSpeed = speed;
		image2 = image;
		this.isTower = isTower;
		if (needImage) {
		    loadImage (image2);
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
        return new TurretProjectile(x+width/2, y, 30, 15, projectileSpeed);
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
