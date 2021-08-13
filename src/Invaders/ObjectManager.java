package Invaders;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship rship;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random ran = new Random();
	
	ObjectManager(Rocketship rship) {
		this.rship = rship;
	}
	
	void addProjectile(Projectile pro) {
		projectiles.add(pro);
		for (Projectile p: projectiles) {
			update();
			if (p.y >= 704 && p.y <= 5) {
				p.isActive = false;
			}
			PurgeObjects(projectiles, p);
			draw(null);
		}
	}
	
	void addAlien() {
		aliens.add(new Alien(ran.nextInt(LeagueInvaders.WIDTH),0,50,50));
		for (Alien a: aliens) {
			update();
			if (a.y >= 704 && a.y <= 5) {
				a.isActive = false;
			}
			PurgeObjects(aliens, a);
			draw(null);
		}
	}
	
	void update() {
		
	}
	
	void draw(Graphics g) {
		rship.draw(g);
	}
	
	void PurgeObjects(ArrayList list, GameObject go) {
		if (!go.isActive) {
			list.remove(go);
		}
	}
}
