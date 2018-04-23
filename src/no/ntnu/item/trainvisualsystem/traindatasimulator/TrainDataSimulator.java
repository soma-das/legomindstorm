package no.ntnu.item.trainvisualsystem.traindatasimulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.item.trainvisualsystem.traindatasimulator.TrainProperties;

public class TrainDataSimulator extends Block {
	
	public List<TrainProperties> TrainRoute;
	public int temp = 0;
	public int nextData = 0;
	public static HashMap<Integer, String> hm1 = new HashMap<Integer, String>();
	public static HashMap<Integer, String> hm2 = new HashMap<Integer, String>();
	public static HashMap<Integer, String> hm3 = new HashMap<Integer, String>();
	public static HashMap<Integer, String> hm4 = new HashMap<Integer, String>();
	public static HashMap<Integer, String> hm5 = new HashMap<Integer, String>();
	
	

	public String getTrainRouteFile() {
		
		
		File file = new File("files" + File.separator + "TRACK02.bbm");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String xml = "";
			String s;
			while ((s = br.readLine()) != null) {
				xml += s;
			}
			br.close();
			return xml;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Incorrect Train Route file chosen!");
			return null;
		}
	}

	public List<TrainProperties> TrainRouteInformation(Document document) {
		ArrayList<TrainProperties> list = new ArrayList<TrainProperties>();
		treeWalk(document.getRootElement());
		nextData++;
		return list;
	}

	public void treeWalk(Element element) {
		for (int i = 0, size = element.nodeCount(); i < size; i++) {
			Node node = element.node(i);
			if (node instanceof Element) {
				treeWalk((Element) node);
				Element element1 = (Element) node;
				if ((element1.elementText("X") != null) && (element1.elementText("Y") != null)
						&& (element1.elementText("Width") != null) && (element1.elementText("Height") != null)) {
					hm1.put(temp, element1.elementText("X"));
					hm2.put(temp, element1.elementText("Y"));
					hm3.put(temp, element1.elementText("TrainNumber"));
					hm4.put(temp, element1.elementText("Speed"));
					hm5.put(temp, element1.elementText("Station"));
					
					temp = temp + 1;
					break;
				}
			}
		}
	}

	public TrainProperties updateTrainProperty() {
		
		TrainProperties trainP = new TrainProperties();
		trainP.xCoordinate = Double.parseDouble(hm1.get(nextData));
		trainP.yCoordinate = Double.parseDouble(hm2.get(nextData));
		trainP.TrainNo = Integer.parseInt(hm3.get(nextData));
		trainP.Speed = Double.parseDouble(hm4.get(nextData));
		trainP.Station = hm5.get(nextData);
		trainP.Delay = System.currentTimeMillis();
		TrainProperties next = trainP;
		
				
		if(nextData<(temp-1)){
			nextData++;
		}
		return next;
		
	}

	
	
}
