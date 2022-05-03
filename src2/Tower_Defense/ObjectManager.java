package Tower_Defense;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Turret t;
	ArrayList<Foe> foes = new ArrayList<Foe>();
	ArrayList<TurretProjectile> tp = new ArrayList<TurretProjectile>();
	Random ran = new Random();

	ObjectManager(Turret q) {
		t = q;

	}

	void draw(Graphics g) {
		t.draw(g);
		for (Foe foe : foes) {
			foe.draw(g);
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

	void addFoe() {
		foes.add(new Foe(ran.nextInt(100) + 200, 0, 35, 35));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addFoe();

	}

}
