import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Scoreboard extends JLabel 
{
   JLabel score;
   int currentScore;
   int pointFactor;
   public Scoreboard()
   {
      
      currentScore = 0;
      pointFactor = 2;
      score = new JLabel("SCORE: " + currentScore);
      score.setFont(score.getFont().deriveFont(14.0f));
      score.setForeground(Color.red);
   	//
   }
   public void updateScore() 
   {
      currentScore += pointFactor;
      score.setText("SCORE: " + currentScore);
   }
   public void decreaseScore() 
   {
      currentScore -= pointFactor;
      score.setText("SCORE: " + currentScore);
   }

}