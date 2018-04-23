package no.ntnu.item.trainvisualsystem.errorrestartconsolehandler;

import javax.swing.JOptionPane;
import no.ntnu.item.arctis.runtime.Block;

public class ErrorRestartConsoleHandler extends Block {
	public boolean consoleOpen = false;
	public java.lang.String appendString;
	public int optionPane;

	public void open() {
		consoleOpen = true;
	}

	public void close() {
		consoleOpen = false;
		
		optionPane = JOptionPane.showConfirmDialog(null, "Would you like Re-establish Connection?", "Server Crashed!!",
				JOptionPane.YES_NO_OPTION);
		if(optionPane == JOptionPane.YES_OPTION)
		{
		    JOptionPane.showMessageDialog(null, "Reconnecting..");
		    sendToBlock("RESTART");
		}
		else
		{
		    JOptionPane.showMessageDialog(null, "Connection Ending!");
		    sendToBlock("CLOSE");
		}

	}
}
