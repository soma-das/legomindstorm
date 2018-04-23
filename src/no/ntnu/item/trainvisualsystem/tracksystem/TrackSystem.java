package no.ntnu.item.trainvisualsystem.tracksystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import javax.swing.JFileChooser;
//import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import no.ntnu.item.arctis.runtime.Block;

public class TrackSystem extends Block {
	
	public int temp = 0, index = 0;
	public java.lang.String directory;
	public int optionPane;
	public static HashMap<Integer, String> hm1 = new HashMap<Integer, String>();
	public static HashMap<Integer, String> hm2 = new HashMap<Integer, String>();
	public static HashMap<Integer, String> hm3 = new HashMap<Integer, String>();
	public static HashMap<Integer, String> hm4 = new HashMap<Integer, String>();

	public void open() {
		File file = null;
		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Open Bluebrick file");
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
			}
			BufferedReader br = new BufferedReader(new FileReader(file));
			String xml = "";
			String s;
			while ((s = br.readLine()) != null) {
				xml += s;
			}
			br.close();
			sendToBlock("PARSE", xml);
			
		} catch (Exception e) {
			e.printStackTrace();
			sendToBlock("ERROR");
			
		}
	}

	public void passXMLdata(final Document document) {
		treeWalk(document.getRootElement());
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
					hm3.put(temp, element1.elementText("Width"));
					hm4.put(temp, element1.elementText("Height"));
					index = 0;
					temp = temp + 1;
				}
			} else {
			}
		}
	}

		public void fixSelectionErr() {
		optionPane = JOptionPane.showConfirmDialog(null, "Would you like to Re-Select?", "Wrong Track File!!",
				JOptionPane.YES_NO_OPTION);
		if(optionPane == JOptionPane.YES_OPTION)
		{
		    JOptionPane.showMessageDialog(null, "Select Correct Bluebrick Map File!");
		    sendToBlock("REPEAT");
		}
		else
		{
		    JOptionPane.showMessageDialog(null, "No Monitoring possible !");
		    sendToBlock("CLOSE");
		}
	}

	
}
