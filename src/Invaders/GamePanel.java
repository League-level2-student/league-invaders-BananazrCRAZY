package Invaders;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	final int INSTRUCTIONS = 3;
	
	int currentState = MENU;
	Font titleFont;
	Font enterFont;
	Font instFont;
	Timer frameDraw;
	Rocketship rship;
	ObjectManager om;
	Timer alienSpawn;
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	GamePanel() {
		 titleFont = new Font("Arial", Font.PLAIN, 48);
		 enterFont = new Font("Arial", Font.PLAIN, 20);
		 instFont = new Font("Arial", Font.PLAIN, 21);
		 rship = new Rocketship(250, 700, 50, 50);
		 om = new ObjectManager(rship);
		 
		 if (needImage) {
			    loadImage ("space.png");
			}
		 
		 frameDraw = new Timer(1000/60,this);
		 frameDraw.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		} else if (currentState == INSTRUCTIONS) {
			drawIntructionsState(g);
		}
	}
	
	void UpdateMenuState() {
		
	}
	void UpdateGameState() {
		om.update();
		if (!rship.isActive) {
			currentState = END;
		}
	}
	void UpdateEndState() {

	}
	void updateInstruState() {
		
	}
	
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 18, 143);
		
		g.setFont(enterFont);
		g.drawString("Press ENTER to Start", 148, 400);
		
		g.setFont(instFont);
		g.drawString("Press SPACE for Instructions", 111, 525);
	}
	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		
		om.draw(g);
	}
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAMEOVER", 108, 143);
		
		g.setFont(titleFont);
		g.drawString("SCORE: " + om.getScore(), 134, 450);
	}
	void drawIntructionsState(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("INSTRUCTIONS", 70, 143);
		
		g.setFont(instFont);
		g.drawString("Press the ARROW KEYS to MOVE", 80, 350);
		
		g.setFont(instFont);
		g.drawString("Press SPACE to SHOOT", 111, 525);
		
		g.setFont(instFont);
		g.drawString("Press ENTER to CONTINUE", 100, 650);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    UpdateMenuState();
		}else if(currentState == GAME){
		    UpdateGameState();
		}else if(currentState == END){
		    UpdateEndState();
		} else if (currentState == INSTRUCTIONS) {
			updateInstruState();
		}
		repaint();
		
		// resets if ship goes out of bounds
		if (rship.y <= 5) {
			rship.y = 6;
		}
		if (rship.y >= 704) {
			rship.y = 703;
		}
		if (rship.x <= 5) {
			rship.x = 6;
		}
		if (rship.x >= 431) {
			rship.x = 430;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		        rship = new Rocketship(250, 700, 50, 50);
		        om = new ObjectManager(rship);
		    } else if (currentState == INSTRUCTIONS) {
		    	currentState = MENU;
		    } else {
		        currentState++;
		        if (currentState == 1) {
		        	startGame();
		        }else if (currentState == 2) {
		        	alienSpawn.stop();
		        }
		    }
		}
		// ship movement
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		    if (rship.y > 5) {
		    	rship.up();
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			if (rship.x > 5) {
		    	rship.left();
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    if (rship.x < 431) {
		    	rship.right();
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			if (rship.y < 704) {
		    	rship.down();
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			if (currentState == GAME) {
				om.addProjectile(rship.GetProjectile());
			} else if (currentState == MENU) {
				currentState = INSTRUCTIONS;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	
	void startGame() {
		alienSpawn = new Timer(1000, om);
		alienSpawn.start();
	}
}
