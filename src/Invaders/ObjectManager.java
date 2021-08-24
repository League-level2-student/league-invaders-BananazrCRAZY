package Invaders;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	Rocketship rship;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random ran = new Random();

	ObjectManager(Rocketship rship) {
		this.rship = rship;
	}

	void addProjectile(Projectile pro) {
		projectiles.add(pro);
	}

	void addAlien() {
		aliens.add(new Alien(ran.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}

	void update() {
		for (Alien a: aliens) {
			if (a.y >= 704 && a.y <= 5) {
				a.isActive = false;
			}
			a.update();
			PurgeObjects(aliens, a);
		}

		for (Projectile p: projectiles) {
			if (p.y >= 704 && p.y <= 5) {
				p.isActive = false;
			}
			p.update();
			PurgeObjects(projectiles, p);
		}
		CheckCollision();
	}

	void draw(Graphics g) {
		for (Alien a: aliens) {
			a.draw(g);
		}

		for (Projectile p: projectiles) {
			p.draw(g);
		}

		rship.draw(g);
	}

	void PurgeObjects(ArrayList list, GameObject go) {
		if (!go.isActive) {
			list.remove(go);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
	}

	public void CheckCollision() {
		for(int i = 0; i < aliens.size(); i++){
			Alien a = aliens.get(i);
			if (rship.collisionBox.intersects(a.collisionBox)) {
				rship.isActive = false;
				a.isActive = false;
				break;
			}
			for(int j = 0; j < projectiles.size(); j++){
				Projectile p = projectiles.get(i);
				if (p.collisionBox.intersects(a.collisionBox)) {
					p.isActive = false;
					a.isActive = false;
				}
			}
		}
	}
}
