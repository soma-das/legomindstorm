package no.ntnu.item.trainvisualsystem.visualstationcontroller;

import java.awt.*;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import no.ntnu.item.arctis.runtime.Block;

public class VisualStationController extends Block {
	
	
	public int trainLength;
	
	public javax.swing.JFrame frame;
	public javax.swing.JPanel panel;
	
	public java.lang.String destinationInput;
	//public String backgroungImg = "images/green.jpg";
	public String backgroungImg = "images/jungle.jpg";
	public int stationNO = 0;
	public String trdIcon = "images\\ticon.png";
	TrackBuild trackb;
	TrainBuild trainb;
	StationGraphics sg;
	public final int x;
	public final int y;
	public java.lang.String trainParameters;

	public javax.swing.JFrame trackDisplay() {
		frame = new JFrame("Lego-Mindstorm Train Monitoring System");
		frame.addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				sendToBlock("EXIT");
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}
		});
		trackb = new TrackBuild(backgroungImg, destinationInput, trainLength);
		trackb.setLayout(new BorderLayout());
		frame.getContentPane().add(trackb, BorderLayout.CENTER);
		frame.getContentPane().add(new JPanel() {
			{
				this.setLayout(new BorderLayout());
				this.setBorder(new TitledBorder(new EtchedBorder(), " Station     Information "));
				JPanel StationPanel = new JPanel();
				sg = new StationGraphics(stationNO, trdIcon);
				StationPanel.add(sg);
				this.add(StationPanel, BorderLayout.NORTH);
			}
		}, java.awt.BorderLayout.NORTH);
		frame.pack();
		frame.setSize(new Dimension(x, y));
		frame.setVisible(true);
		return frame;
	}

	public String ERROR() {
		frame.dispose();
		frame.setVisible(false);
		frame.pack();
		return ("Visualization of Remote Monitoring of Lego Trains has been Stopped!! \n close the console window to exit or restart system!");
	}

	public VisualStationController(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
