package no.ntnu.item.trainvisualsystem.visualstationcontroller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import no.ntnu.item.trainvisualsystem.tracksystem.TrackSystem;


@SuppressWarnings("serial")
public class TrainBuild extends JComponent {
	
	
	private Timer timer;
	BufferedImage img;
	int tno;
	double xTrainPosition, yTrainPosition;
	
	

	public TrainBuild(String nameOfBGImg, int trainNo, Map<String,String> routeMap) {
		
		
		xTrainPosition = Double.parseDouble(routeMap.get("xCord"));
		yTrainPosition = Double.parseDouble(routeMap.get("yCord"));
		
		
		this.tno = trainNo;
		this.setLayout(null);
				
		try {
			img = ImageIO.read(new File(nameOfBGImg));
			this.setPreferredSize(new Dimension(1500, 1000));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
																							
		
	}

	public void startPaiting(int trainDelay) {  
		
		
		timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				repaint();
			}
		}, 0, trainDelay);   
	}

	public void pause() {
		timer.cancel();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		
		
		TrackSystem fra = new TrackSystem();
		
		HashMap<Integer,String> hm1 = fra.hm1;
		HashMap<Integer,String> hm2 = fra.hm2;
		HashMap<Integer,String> hm3 = fra.hm3;
		HashMap<Integer, String> hm4 = fra.hm4;
		
		g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		
		for (int temp = 0; temp < hm1.size(); temp++) {
			double X = Double.parseDouble(hm1.get(temp));
			double Y = Double.parseDouble(hm2.get(temp));
			double Width = Double.parseDouble(hm3.get(temp));
			double Height = Double.parseDouble(hm4.get(temp));
			RoundRectangle2D rect2 = new RoundRectangle2D.Double(X, Y, Width, Height, 10, 10);
			
			g2.setColor(Color.lightGray);
			g2.fill(rect2);
			Stroke stroke1 = new BasicStroke(3f);
			g2.setStroke(stroke1);
			g2.setColor(Color.DARK_GRAY);
			g2.draw(rect2);
		}
		
		Ellipse2D.Double eD = new Ellipse2D.Double(xTrainPosition-13, yTrainPosition-20, 35.1, 31.1);
		GradientPaint trainLook = new GradientPaint(5, 7, Color.white, 200, 7, Color.YELLOW);
		g2.setPaint(trainLook);
		g2.fill(eD);
		Stroke strokeED = new BasicStroke(6f);
		g2.setStroke(strokeED);
		g2.setColor(Color.BLACK);
		g2.draw(eD);
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		g2.drawString("" + tno, (float) (xTrainPosition), (float) yTrainPosition);
		g2.setColor(Color.BLACK);
		
		
	}
}