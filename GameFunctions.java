import java.awt.Graphics;
import java.awt.Color;
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

public class GameFunctions extends JFrame //implements KeyListener
{	
   int invadersMovement;
   int count = 0;
   public static void gameOver(GameSettings settings, Graphics g)
   {
      BufferedImage img;
      try
      {
         img = ImageIO.read(new File("gameover.jpg")); 
         g.drawImage(img, 0, 0, settings.screenWidth, settings.screenHeight, null);     
      }
      catch (IOException e) 
      {
		   e.printStackTrace();
	   }
   }
   
   public static void drawBackground(GameSettings settings, Graphics g)
   {
      BufferedImage bg;
      try
      {
         bg = ImageIO.read(new File("background.jpg")); 
         g.drawImage(bg, 0, 0, settings.screenWidth, settings.screenHeight, null);     
      }
      catch (IOException e) 
      {
		   e.printStackTrace();
	   }
   }
   
   public static void positionInvaders(int[] invadersX, int[] invadersY)
   {
      int currentVertical;
      //BufferedImage invader;
		// try 
//       {
			currentVertical = 50;
         invadersX[0] = 0;
         invadersY[0] = 0;
			for(int i = 0; i < 33; i++) 
         {
				invadersX[i+1] = invadersX[i] + 40;
				invadersY[i] = currentVertical;
				
				if(i == 10 || i == 21) 
            {
					currentVertical += 40;
					invadersX[i+1] = invadersX[0];
				}
				
				//invader = ImageIO.read(new File("spaceInvaders.png"));
				
		   }
		//}
      // catch (IOException e) 
//       {
// 			// TODO Auto-generated catch block
// 			e.printStackTrace();
// 		}
   }
   
   public void moveInvaders(int[] invadersX, int[] invadersY)
   {
      //int invadersMovement;
      // try
//       {
         // currentVerticalPosition = 50;
			for(int i = 0; i < 33; i++) 
         {
				
				if(invadersX[0] <=10) 
            {
					invadersMovement = 25;
               //invadersX[i] = invadersX[i] + invadersMovement;
               invadersY[i] = invadersY[i] + 40;
				}
            else if(invadersX[0] >= 250) 
            {
					invadersMovement = -25;
               //invadersX[i] = invadersX[i] + invadersMovement;
               invadersY[i] += 40;

				}
				
				invadersX[i] = invadersX[i] + invadersMovement;
			}
         //System.out.println("this moved");
      // }
//       catch (IOException e) 
//       {
// 		   e.printStackTrace();
// 	   }
   }
   
   public static void drawInvaders(int[] alive, int[] invadersX, int[] invadersY, Graphics g)
   {
      BufferedImage invader;
      try
      {
         // currentVerticalPosition = 50;
         //System.out.println("this is ran");
			for(int i = 0; i < 33; i++) 
         {
				
				invader = ImageIO.read(new File("spaceInvaders.png"));
            if(alive[i] == 1)
				   g.drawImage(invader, invadersX[i], invadersY[i], 26, 26, null);
			}
         //System.out.println("this drew");
      }
      catch (IOException e) 
      {
		   e.printStackTrace();
	   }
   }

   public static void drawShip(int shipX, GameSettings settings, Graphics g)
   {
      BufferedImage ship;
      
		if(shipX <= 20) 
      {
			shipX = 0;
		}
      else if(shipX >=580) 
      {
			shipX = 570;
		}	
      try
      {
         ship = ImageIO.read(new File("user.png"));
         g.drawImage(ship, shipX, 500, settings.shipWidth, settings.shipHeight, null);     
      }
      catch (IOException e) 
      {
		   e.printStackTrace();
	   }
   }
   
   public static void drawBullet(int currentBullet, int[] shipBulletX, int[] shipBulletY, GameSettings settings, Graphics g)
   {
      for(int j = 0; j < currentBullet; j++)
         {
            g.setColor(Color.red);
            g.fillRect(shipBulletX[j], shipBulletY[j], settings.bulletWidth, settings.bulletHeight);
            shipBulletY[j] = shipBulletY[j] - 25;
         }
   }
   
   public void drawInvaderBullet(int currentInvaderBullet, int[] invaderBulletX, int[] invaderBulletY, GameSettings settings, Graphics g)
      {
         
         for(int k = 0; k < currentInvaderBullet; k++)
         {
            g.setColor(Color.yellow);
            g.fillRect(invaderBulletX[k], invaderBulletY[k], settings.bulletWidth, settings.bulletHeight);
            invaderBulletY[k] = invaderBulletY[k] + 50;
         }      
      }
   
   public static boolean checkInvaderHitShip(int currentInvaderBullet, int[] invaderBulletX, int[] invaderBulletY, int shipX)
   {
      for(int w = 0; w < currentInvaderBullet; w++) 
      {
         if(invaderBulletX[w] >= shipX && invaderBulletX[w] <= shipX+60 && invaderBulletY[w] >= 420 && invaderBulletY[w] <= 440)
         {
            invaderBulletY[w] = -20;
            //System.out.println("true");
            return true;
         }
      }
      return false;   
   }

   public static boolean checkShipHitInvader(int currentBullet, int[] alive, int[] shipBulletX, int[] shipBulletY, int[] invadersX, int[] invadersY)
   {
      for(int y = 0; y < 33; y++)
      {
         for(int z = 0; z < currentBullet; z++)
         {
            if(alive[y] == 1 && shipBulletX[z] >= invadersX[y] && shipBulletX[z] <= invadersX[y]+20 && shipBulletY[z] >= invadersY[y] && shipBulletY[z] <= invadersY[y]+20)
            {
               alive[y] = 0;
               shipBulletY[z] = -20;
               return true;
            }
         }
      } 
      return false;  
   }
   
   public static boolean checkInvaderReachBottom(int[] invadersY)
   {
      for(int b = 0; b < 33; b++)
      {
         if(invadersY[b] >= 600 || invadersY[b] >= 500) //bottom or touch ship
         {
   			System.out.println("true");
            return true;
   		}
      }
      return false;  
   }

}
