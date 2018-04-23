package no.ntnu.item.trainvisualsystem.visualstationcontroller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JComponent;


import no.ntnu.item.trainvisualsystem.tracksystem.*;



@SuppressWarnings("serial")
public class TrackBuild extends JComponent {
		
	BufferedImage img;

	public TrackBuild(String nameOfBGImg, String destin, int trainLength) {

		this.setLayout(null);
		
		
		try {
			img = ImageIO.read(new File(nameOfBGImg));
			this.setPreferredSize(new Dimension(1500, 1000));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();
	}

	@SuppressWarnings("static-access")
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
	}
}
