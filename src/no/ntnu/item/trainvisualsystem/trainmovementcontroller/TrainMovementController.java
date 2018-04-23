package no.ntnu.item.trainvisualsystem.trainmovementcontroller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.google.gson.Gson;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.item.trainvisualsystem.graphicsbox.StationGraphics;
import no.ntnu.item.trainvisualsystem.graphicsboxnew.TrackBuild;
import no.ntnu.item.trainvisualsystem.graphicsboxnew.TrainBuild;



public class TrainMovementController extends Block {
	
	public java.lang.String trainElements;
	public javax.swing.JFrame trainFrame;    // this trainFrame is same as outside frame
	public java.util.Map<java.lang.String,java.lang.String> trainRoute;
	public int stationNo;
	
		public String backgroungImg = "images/green.jpg";
		public String trdIcon = "images\\ticon.png";
		
		// Instance parameter. Edit only in overview page.
		public final int x;
		// Instance parameter. Edit only in overview page.
		public final int y;
		
		//public int stationNO =2;   // parse string to int value
		TrackBuild trackb;
		TrainBuild trainb;
		StationGraphics sg;
		
		
		int trainNo;
		//String destinationInput;
		//int trainLength;
		double avgDistanceMeters = 100;
		double avgTrainSpeedMps;
		int trainTimeMSec;
		
		
		@SuppressWarnings("serial")
		public void trainMovement() {
			
			
		
			System.out.println("Train movemnt box ! \n"); // test print

//			double xValue = Double.parseDouble(trainRoute.get("xCord"));
//			double yCoordinate = Double.parseDouble(trainRoute.get("yCord"));
			//int trainSpeed = (int)Double.parseDouble(trainRoute.get("Speed"));
			avgTrainSpeedMps = Double.parseDouble(trainRoute.get("Speed"));
			trainNo = Integer.parseInt(trainRoute.get("TrainNo"));
			
			//taking speed and converting into time
			trainTimeMSec = (int)((avgDistanceMeters/avgTrainSpeedMps))*1000;
					
		
			//trainFrame.remove(trackb); // old track frame  removing 
			trainFrame.getContentPane().removeAll();
		
			trainb = new TrainBuild(backgroungImg, trainTimeMSec, trainNo,trainRoute);  // //trainb = new TrainBuild(backgroungImg, speedTrain, trainNum,trainLength, trainRoute);  // change parameter
			trainb.setLayout(new BorderLayout());
		
		 //station panel
		 trainFrame.getContentPane().add(new JPanel() {
				{
					int stationNO = stationNo;
					this.setLayout(new BorderLayout());
					this.setBorder(new TitledBorder(new EtchedBorder(), " Station     Information "));
					JPanel StationPanel = new JPanel();
					sg = new StationGraphics(stationNO, trdIcon);
					StationPanel.add(sg);
					this.add(StationPanel, BorderLayout.NORTH);
				}
			}, java.awt.BorderLayout.NORTH);
		
		//track + train second panel
		trainFrame.getContentPane().add(trainb, BorderLayout.CENTER);
		
		//panel for resume button
		trainFrame.getContentPane().add(new JPanel() {
			{
				this.setBackground(Color.PINK);
				add(new JButton("Pause") {
					{
						addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand().equals("Pause")) {
									trainb.pause();
									setText("Resume");
								} else {
									trainb.startPaiting(trainTimeMSec);                         // later edit! what should I do of this
									setText("Pause");
								}
							}
						});
					}
				});
			}
		}, java.awt.BorderLayout.SOUTH);
		
		
		//trainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 trainFrame.pack();
		trainFrame.setSize(new Dimension(x, y));
		trainFrame.setVisible(true);
		
		
		
		
		
	}

	public void hideTrain() {
		trainFrame.setVisible(false);
		//trainFrame.remove(trainb);
	}

	// Do not edit this constructor.
	public TrainMovementController(int x, int y) {
	    this.x = x;
	    this.y = y;
	}

	@SuppressWarnings("rawtypes")
	public int getStation(String jsonData) {
		
			Map jsonJavaRootObject = new Gson().fromJson(jsonData, Map.class);
			String str =(String) jsonJavaRootObject.get("Station");
		 
			if (new String("Trondheim").equals(str)){
				return 0;
			}
			else if(new String("Oslo").equals(str)){
				return 1;
			}
			else if(new String("Bergen").equals(str)){
				return 2;
			}
			else if(new String("Oslo").equals(str)){
				return 3;
			}
			else if(new String("Stavanger").equals(str)){
				return 4;
			}
			else if(new String("Modle").equals(str)){
				return 5;
			}
			else{
				return 6;
			}
	}

	
		
	public void test(java.util.Map<java.lang.String,java.lang.String> trainRoute) {
			
			System.out.println("soma"+trainRoute.get("xCord"));
		}
		
	
}
