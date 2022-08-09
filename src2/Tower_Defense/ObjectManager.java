package Tower_Defense;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

public class ObjectManager implements ActionListener {
	Turret t;
	ArrayList<Foe> foes = new ArrayList<Foe>();
	ArrayList<Troll> trolls = new ArrayList<Troll>();
	ArrayList<Turret> turrets = new ArrayList<Turret>();
	ArrayList<TurretProjectile> tp = new ArrayList<TurretProjectile>();
	Random ran = new Random();
	int money = 30;
	Timer projectileTimer = new Timer(1500, this);
	Timer towerTime = new Timer(1000, this);
	int time = 1500;

	ObjectManager() {
		projectileTimer.start();
		towerTime.start();
	}

	public int getMoney() {
		return money;
	}

	void draw(Graphics g) {
		for (Foe foe : foes) {
			foe.draw(g);
		}
		for (Troll troll : trolls) {
			troll.draw(g);
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
		}
		for (Troll troll : trolls) {
			troll.update();
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
		checkCollision();
		purgeObjects();
	}

	void purgeObjects() {
		for (int i = 0; i < foes.size(); i++) {
			Foe et = foes.get(i);
			if (et.isActive == false) {
				foes.remove(i);
			}
			for (int i1 = 0; i1 < trolls.size(); i1++) {
				Troll tn = trolls.get(i1);
				if (tn.isActive == false) {
					trolls.remove(i1);
				}
			}
			for (int i1 = 0; i1 < tp.size(); i1++) {
				TurretProjectile missile = tp.get(i1);
				if (missile.isActive == false) {
					tp.remove(i1);
				}
			}
		}
	}

	void addProjectile(TurretProjectile p) {
		tp.add(p);
	}

	void addTurret(int x, int y) {
		if (x > 190 && x < 310) {
		} else if (money < 25) {
		} else {
			turrets.add(new Turret(x - 25, y - 50, 75, 99));
			money -= 25;
		}
	}
	void addTower(int x, int y) {
		if (x > 190 && x < 310) {
		} else if (money < 40) {
		} else {
			turrets.add(new Turret(x - 25, y - 50, 75, 99, 3, "fireball.png", "Tower.png", true));
			money -= 40;
		}
	}

	void addFoe() {
		foes.add(new Foe(ran.nextInt(100) + 200, 0, 50, 39));
	}

	void addTroll() {
		trolls.add(new Troll(ran.nextInt(100) + 200, 0, 50, 50));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == projectileTimer) {
			if (!turrets.isEmpty()) {
				for (Turret turret : turrets) {
					if (!turret.isTower) {
						addProjectile(turret.getProjectile());
					}
				}
			}
		} 
		else if (e.getSource() == towerTime) {
			if (!turrets.isEmpty()) {
				for (Turret turret : turrets) {
					if (turret.isTower) {
						addProjectile(turret.getProjectile());
					}
				}
			}
		}
		else if (e.getSource() == GamePanel.trollSpawn) {
			addTroll();
			GamePanel.trollSpawn.setDelay(time -= 2);
			if (time < 10) {
				time = 10;
			}
		} else {
			addFoe();
			GamePanel.foeSpawn.setDelay(time -= 2);
			if (time < 10) {
				time = 10;
			}
		}

	}

	void checkCollision() {
		for (Foe foe : foes) {
			for (TurretProjectile projectile : tp) {
				if (projectile.collisionBox.intersects(foe.collisionBox)) {
					foe.isActive = false;
					projectile.isActive = false;
					money++;
				}

			}
		}
		for (Troll troll : trolls) {
			for (TurretProjectile projectile : tp) {	
				if (projectile.collisionBox.intersects(troll.collisionBox)) {
					troll.isActive = false;
					projectile.isActive = false;
					money += 2;
					}
				}

			}
		}
	}

