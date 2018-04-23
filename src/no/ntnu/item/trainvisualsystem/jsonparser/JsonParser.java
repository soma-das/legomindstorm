package no.ntnu.item.trainvisualsystem.jsonparser;


import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import no.ntnu.item.trainvisualsystem.jsonparser.TrainInformation;
//import no.ntnu.item.trainvisualsystem.trainmovementcontroller.TrainMovementController;
import no.ntnu.item.arctis.runtime.Block;


	public class JsonParser extends Block {
	
			public String xCord;
			public String yCord;
			public String width;
			public String height;
			public String trainNo;
			public String speed;
			public String destinationStation;
			public long msgDelay,currenttime, totalDelay;
	
	
	
			public void parseJsonParameters(String para) {	
			
						Gson gson = new Gson();
						Map<String, String> map = new HashMap<String, String>();
		
						TrainInformation tf = gson.fromJson(para, TrainInformation.class);
		

//						System.out.println("Train X Position= " + tf.getxCoordinate() + " " + "Train Y Position= " + tf.getyCoordinate());

		
						xCord = Double.toString(tf.getxCoordinate()); 
						yCord = Double.toString(tf.getyCoordinate());
						trainNo = Integer.toString(tf.getTrainNo());
						speed =  Double.toString( tf.getSpeed());
						destinationStation = tf.getStation();
		
		
						map.put("xCord", xCord);
						map.put("yCord", yCord);
						map.put("Width", width);
						map.put("Height", height);
						map.put("TrainNo", trainNo);
						map.put("Speed", speed);
						map.put("DestinationStation", destinationStation);
			
			
						sendToBlock("PARAMETERS",map);
					}
			
			
	
			public JsonParser() {
						
			}



			public void delayMeasurment(String trainDetails) {
				
				Gson gson2 = new Gson();
				TrainInformation trainf = gson2.fromJson(trainDetails, TrainInformation.class);
				
				System.out.println("Train Position X = " + trainf.getxCoordinate() + " " + "Train Position Y = " + trainf.getyCoordinate());
				msgDelay = trainf.getDelay();
				currenttime	= System.currentTimeMillis();
				totalDelay = (currenttime - msgDelay);
				System.out.println("Remote Monitoring Delay = " + totalDelay + "miliSeconds");
				
			}
	}
