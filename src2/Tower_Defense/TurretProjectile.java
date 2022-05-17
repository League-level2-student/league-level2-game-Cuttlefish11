package Tower_Defense;

import java.awt.Color;
import java.awt.Graphics;

public class TurretProjectile extends GameObject{
	
	
	TurretProjectile(int x2, int y2, int width2, int height2) {
		super(x2, y2, width2, height2);
		speed = 4;
	}
void update(){
	super.update();
	x +=speed;
	}
	void Draw(Graphics g){
		g.setColor(Color.magenta);
        g.fillRect(x, y, width, height);
	}
}
