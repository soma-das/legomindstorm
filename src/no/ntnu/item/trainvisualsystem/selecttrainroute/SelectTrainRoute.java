package no.ntnu.item.trainvisualsystem.selecttrainroute;

import javax.swing.JOptionPane;

import no.ntnu.item.arctis.runtime.Block;

public class SelectTrainRoute extends Block {

	Thread t;
	public void openRouteDialogBox() {
		Runnable r = new Runnable() {
			public void run() {
				// NEW CODE
				String[] routes = {"TRACK00","TRACK01","TRACK02","TRACK04","TRACK123"};
				String route_response = (String) JOptionPane.showInputDialog(null, "Choose Route: ", "Trondheim Railway", JOptionPane.QUESTION_MESSAGE, null, routes, routes[1]);
				if (route_response != null) {
					sendToBlock("ROUTE", route_response);				// data also has to be sent
					
					// .....? required
					// System.out.println(route_response);
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Wrong Route Selection!", "Warning", JOptionPane.WARNING_MESSAGE);
					sendToBlock("CANCEL");
				}
			}
		};
		t = new Thread(r);
		t.start();
	}

	public String checkBBM(String getRoute) {
		if (getRoute.endsWith(".bbm")) {
			return getRoute;
		}
		else
			return getRoute + ".bbm";
	}

	public void close() {
		
		JOptionPane.showMessageDialog(null, "Wrong Route Selection!", "Warning",
		        JOptionPane.WARNING_MESSAGE);
		
		t.interrupt();
		t = null;
	}

}
