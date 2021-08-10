package Invaders;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	
	int currentState = MENU;
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(10, 10, 100, 100);
	}
	
	void UpdateMenuState() {
		
	}
	void UpdateGameState() {
		
	}
	void UpdateEndState() {
		
	}
}
