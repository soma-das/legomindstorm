package no.ntnu.item.trainvisualsystem.visualstationcontroller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import java.awt.*;

@SuppressWarnings("serial")
public class StationGraphics extends JComponent{
	
	int station;
	 BufferedImage imgIcon = null;
	
	
	 public StationGraphics(int s, String trdIcon){
		this.station = s;
		
		
		 try {
	            imgIcon = ImageIO.read(new File(trdIcon));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}
	
	

	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
           
      
        g2.drawString("TRONDHEIM", 2, 36);
        g2.drawString("OSLO", 153, 36);
        g2.drawString("BERGEN", 288, 36);
        g2.drawString("STAVANGER", 418, 36);
        g2.drawString("MOLDE", 577, 36);
        g2.drawString("ALESUND", 698, 36);
        
       
        
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(25, 11, 742, 11);
        g2.setColor(Color.gray);
        g2.drawLine(25, 11, 25+(station*143), 11);
        
       
        
        g2.setStroke(new BasicStroke(9));
       int x = 23;
         
        for (int i = 0; i < 6; i++) {
            g2.drawOval(x, 7, 10, 10);
            x = x + 143;
        }
        
        //stoke according to station entered 
        
        g2.setColor(Color.black);
        x = 23 + (station*143);
        for (int i = 0; i < 6; i++) {
            g2.drawOval(x, 7, 10, 10);
            x = x + 143;
        }

        g2.setColor(Color.white);
        x = 23;
        for (int i = 0; i < 6; i++) {
            g2.fillOval(x, 7, 10, 10);
            x = x + 143;
        }
        
        g2.setStroke(new BasicStroke(9));
        g2.setColor(Color.BLACK);
        g2.drawOval(19, 5, 20, 20);
        g2.drawImage(imgIcon, 19, 5, 20, 20, null);
        //g2.drawImage(imgIcon, 19, 5, 20, 20, Color.GREEN, null);
        g2.setColor(Color.red);
        g2.fillOval(23 + (143*station), 7, 10, 10);
       
            
	}
	// size of panel
	
	 @Override
	    public Dimension getPreferredSize() {
	        return new Dimension(800, 40);
	        
	        
	 }
	 
}

