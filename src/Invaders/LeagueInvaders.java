package Invaders;
import java.awt.Color;

import javax.swing.JFrame;

public class LeagueInvaders {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	JFrame f;
	GamePanel gp;
	public static void main(String[] args) {
		LeagueInvaders linvade = new LeagueInvaders();
	}
	
	public LeagueInvaders() {
		f = new JFrame();
		gp = new GamePanel();
		setup(WIDTH, HEIGHT);
		f.addKeyListener(gp);
	}
	
	public void setup(int wid, int hei) {
		f.add(gp);
		f.setSize(wid, hei);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
