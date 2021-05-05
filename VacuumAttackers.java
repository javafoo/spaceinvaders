import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class VacuumAttackers extends JFrame implements KeyListener
{
	JPanel panel;
	int shipX = 300;
	int [] invadersX = new int[34];
	int [] invadersY = new int[34];
	int[] shipBulletX = new int[1000];
	int[] shipBulletY = new int[1000];
	int currentBullet = 0;
   int[] invaderBulletX = new int[1000];
	int[] invaderBulletY = new int[1000];
	int currentInvaderBullet = 0;
   int count = 0;
   int alive[] = new int[34];
   Scoreboard scoreboard = new Scoreboard();
   GameSettings settings = new GameSettings();
   GameFunctions f = new GameFunctions();
   int invadersMovement;
   int currentVerticalPosition;
   BufferedImage invader;
   Graphics g;
   Toolkit t = Toolkit.getDefaultToolkit(); 

   
   boolean gameover = false;
   
	
	public VacuumAttackers() 
   {
		super("Space Invaders");
      setSize(settings.screenWidth, settings.screenHeight);
      setResizable(false);
      f.positionInvaders(invadersX, invadersY);
		init();
		setVisible(true);
	}
	
	public void init() 
   {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		addKeyListener(this);
		System.out.println("gameover status" + gameover);
      for(int x = 0; x < alive.length; x++)
         alive[x] = 1;

		panel = new JPanel() 
      {
			protected void paintComponent(Graphics g) 
         {
            if(gameover) 
            {
               f.gameOver(settings, g);
               System.out.println("gameover" + gameover);
            }
            else 
            {
					f.drawBackground(settings, g);
               
               f.moveInvaders(invadersX, invadersY);
               f.drawInvaders(alive, invadersX, invadersY, g);
               
					f.drawShip(shipX, settings, g);
               
               f.drawBullet(currentBullet, shipBulletX, shipBulletY, settings, g);

               if(count == 10)
               {
                  Random r = new Random();
                  int randomInvader = r.nextInt(33);

                  if( alive[randomInvader] == 1)
                  {
                     invaderBulletX[currentInvaderBullet] = invadersX[randomInvader];
                     invaderBulletY[currentInvaderBullet] = invadersY[randomInvader];
                  }
                  currentInvaderBullet++;
                  count = 0;  
               }
               
               f.drawInvaderBullet(currentInvaderBullet, invaderBulletX, invaderBulletY, settings, g); 
               // for(int k = 0; k < currentInvaderBullet; k++)
//                {
//                   g.setColor(Color.yellow);
//                   g.fillRect(invaderBulletX[k], invaderBulletY[k], 5, 10);
//                   invaderBulletY[k] = invaderBulletY[k] + 50;
//                }
                                 
               if(f.checkShipHitInvader(currentBullet, alive, shipBulletX, shipBulletY, invadersX, invadersY))
               {
                  System.out.println("score increased");
                  scoreboard.updateScore();
               }
               if(f.checkInvaderHitShip(currentInvaderBullet, invaderBulletX, invaderBulletY, shipX))
               {
                  //gameover = true;
                  System.out.println("score decreased");
                  scoreboard.decreaseScore();
               }
               if(f.checkInvaderReachBottom(invadersY))
               {
                  gameover = true;
                  System.out.println("game now over" + gameover);
               }

               count++;
					repaint();
               //super.paintComponent(g);
               t.sync();

			   }
         }
		};
      
      panel.add(scoreboard.score);
		add(panel);
	}
	public static void main(String[] args) {
		new VacuumAttackers();
      
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 37) {
			shipX -= 25;
		}else if(e.getKeyCode() == 39) {
			shipX += 25;
		}
		
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 32) {
			shipBulletX[currentBullet] = shipX + 36;
   		shipBulletY[currentBullet] = 500;
   		currentBullet++;
      }
		repaint();
	}
   
   
}
