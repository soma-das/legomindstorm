package no.ntnu.item.trainvisualsystem.trainmovementexecutor;

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
import no.ntnu.item.trainvisualsystem.visualstationcontroller.StationGraphics;

import no.ntnu.item.trainvisualsystem.visualstationcontroller.TrackBuild;
import no.ntnu.item.trainvisualsystem.visualstationcontroller.TrainBuild;



public class TrainMovementExecutor extends Block {
	public java.lang.String trainElements;
	public javax.swing.JFrame trainFrame;
	public java.util.Map<java.lang.String, java.lang.String> trainRoute;
	public int stationNo;
	//public String backgroungImg = "images/green.jpg";
	public String backgroungImg = "images/jungle.jpg";
	public String trdIcon = "images\\ticon.png";
	public final int x;
	public final int y;
	TrackBuild trackb;
	TrainBuild trainb;
	StationGraphics sg;
	int trainNo;
	double avgDistanceMeters = 100;
	double avgTrainSpeedMps;
	int trainTimeMSec;

	@SuppressWarnings("serial")
	public void trainMovement() {
		
		
		trainFrame.getContentPane().removeAll();
		
		avgTrainSpeedMps = Double.parseDouble(trainRoute.get("Speed"));
		trainNo = Integer.parseInt(trainRoute.get("TrainNo"));
				
		trainb = new TrainBuild(backgroungImg, trainNo, trainRoute);
		trainb.setLayout(new BorderLayout());
		
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
		
		trainFrame.getContentPane().add(trainb, BorderLayout.CENTER);
		
		
		//trainFrame.pack();
		trainFrame.setSize(new Dimension(x, y));
		trainFrame.setVisible(true);
	}

	public void hideTrain() {
		trainFrame.setVisible(false);
	}

	public TrainMovementExecutor(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@SuppressWarnings("rawtypes")
	public int getStation(String jsonData) {
		Map jsonJavaRootObject = new Gson().fromJson(jsonData, Map.class);
		String str = (String) jsonJavaRootObject.get("Station");
		if (new String("Trondheim").equals(str)) {
			return 0;
		} else if (new String("Oslo").equals(str)) {
			return 1;
		} else if (new String("Bergen").equals(str)) {
			return 2;
		} else if (new String("Oslo").equals(str)) {
			return 3;
		} else if (new String("Stavanger").equals(str)) {
			return 4;
		} else if (new String("Modle").equals(str)) {
			return 5;
		} else {
			return 6;
		}
	}

//	public void test(java.util.Map<java.lang.String, java.lang.String> trainRoute) {
//		System.out.println("soma" + trainRoute.get("xCord"));
//	}
}
