public class GameSettings
{
   // screen settings
   public static int screenWidth; 
   public static int screenHeight;
   
   // game settings
   public static int gameSpeed;
   
   // ship settings
   public static int shipWidth;
   public static int shipHeight;
   
   // bullet settings
   public static int bulletWidth;
   public static int bulletHeight;
   
   // invaders settings
   public static int invadersWidth;
   public static int invadersHeight;
   
   public GameSettings()
   {
      //screen settings
      screenWidth = 700; 
      screenHeight = 600;
      
      // game settings
      gameSpeed = 500;
      
      // ship settings
      shipWidth = 80;
      shipHeight = 80;
      
      // bullet settings
      bulletWidth = 5;
      bulletHeight = 10;
      
      // invaders settings
      invadersWidth = 25;
      invadersHeight = 25;
   }
   
}

