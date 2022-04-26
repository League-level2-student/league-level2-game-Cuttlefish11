package Tower_Defense;

import java.awt.Color;
import java.awt.Graphics;

public class Foe extends GameObject{
	
	
	Foe(int x2, int y2, int width2, int height2) {
		super(x2, y2, width2, height2);
		speed = 1;
	}
	
	@Override
	void update(){
		super.update();
		y += speed;
	}
	void draw(Graphics g){
		g.setColor(Color.ORANGE);
        g.fillRect(x, y, width, height);
	}
}
