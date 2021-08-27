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
	int score = 0;

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
		if (!rship.isActive) {
			
		}
		for (int i = 0; i < aliens.size(); i++) {
			Alien a = aliens.get(i);
			if (a.x >= 690 || a.x <= -10 || a.y >= 820) {
				a.isActive = false;
			}
			a.update();
			if (PurgeObjects(aliens, a)) {
				i--;
			}
		}
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			if (p.x >= 704 || p.x <= 5 || p.y <= -20) {
				p.isActive = false;
			}
			p.update();
			if (PurgeObjects(projectiles, p)) {
				i--;
			}
		}
		rship.update();
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

	boolean PurgeObjects(ArrayList list, GameObject go) {
		if (!go.isActive) {
			list.remove(go);
			return true;
		}
		return false;
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
				Projectile p = projectiles.get(j);
				if (p.collisionBox.intersects(a.collisionBox)) {
					p.isActive = false;
					a.isActive = false;
					score++;
				}
			}
		}
	}
	
	public int getScore() {
		return score;
	}
}
