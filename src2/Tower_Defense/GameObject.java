package Tower_Defense;

import java.awt.Rectangle;

public class GameObject {
	 int x;
	 int y;
	 int width;
	 int height;
	 int speed = 0;
	 boolean isActive = true;
Rectangle collisionBox;
	 
	 GameObject(int x2, int y2, int width2, int height2){
		 x = x2;
		 y = y2;
		 width = width2;
		 height = height2;
			height = height2;
			speed = 0;
			isActive = true;
			collisionBox = new Rectangle(x, y, width, height);
	 }
	 void update(){
		 collisionBox.setBounds(x, y, width, height);
	 }
}
