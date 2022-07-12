package Tower_Defense;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class TurretProjectile extends GameObject{
	public BufferedImage image;
	public boolean needImage = true;
	public boolean gotImage = false;
	
	TurretProjectile(int x2, int y2, int width2, int height2) {
		super(x2, y2, width2, height2);
		speed = 4;
		if (needImage) {
		    loadImage ("arrow.png");
		}
	}
	TurretProjectile(int x2, int y2, int width2, int height2, int speed, String image2) {
		super(x2, y2, width2, height2);
		this.speed = speed; 
		if (needImage) {
		    loadImage (image2);
		}
	}
void update(){
	super.update();
	x +=speed;
	if(speed == 5) {
	y -=speed;	
	}
	}
	void Draw(Graphics g){
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
		g.setColor(Color.magenta);
        g.fillRect(x, y, width, height);
		}
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
