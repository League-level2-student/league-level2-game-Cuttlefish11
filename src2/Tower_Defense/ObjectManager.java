package Tower_Defense;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Turret t;
	ArrayList<Foe> foes = new ArrayList<Foe>();
	ArrayList<Turret> turrets = new ArrayList<Turret>();
	ArrayList<TurretProjectile> tp = new ArrayList<TurretProjectile>();
	Random ran = new Random();
	int money = 50;

	ObjectManager() {

	}
	public int getMoney() {
		return money;
	}
	
	void draw(Graphics g) {
		for (Foe foe : foes) {
			foe.draw(g);
		}
		for (Turret turret : turrets) {
			turret.draw(g);
		}
		for (TurretProjectile projectile : tp) {
			projectile.Draw(g);
		}
	}

	void update() {
		for (Foe foe : foes) {
			foe.update();
			if (foe.y > TowerDefense.HEIGHT) {
				foe.isActive = false;
			}
		}
		for (TurretProjectile projectile : tp) {
			projectile.update();
			if (projectile.y > TowerDefense.HEIGHT) {
				projectile.isActive = false;
			}
		}
		for (Turret turret : turrets) {
			turret.update();
		}
	}

	void purgeObjects() {
		for (int i = 0; i < foes.size(); i++) {
			Foe et = foes.get(i);
			if (et.isActive == false) {
				foes.remove(i);
			}
		}
		for (int i1 = 0; i1 < tp.size(); i1++) {
			TurretProjectile missile = tp.get(i1);
			if (missile.isActive == false) {
				tp.remove(i1);
			}
		}
	}

	void addProjectile() {

	}

	void addTurret(int x, int y) {
		if (x > 190 && x < 310) {
		}
		else if (money < 50) {	
		}
		else {
			turrets.add(new Turret(x - 25, y - 50, 50, 50));
			money -= 50;
		}
	}

	void addFoe() {
		foes.add(new Foe(ran.nextInt(100) + 200, 0, 35, 35));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addFoe();

	}
	void checkCollision() {
		for (Foe foe : foes) {	
		for (TurretProjectile projectile : tp) {
				if (projectile.collisionBox.intersects(foe.collisionBox)) {
					foe.isActive = false;
					money++;
				}

}
		}
}
	}

