package no.ntnu.item.trainvisualsystem.buttonarray;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JFrame;
import no.ntnu.item.arctis.runtime.Block;

public class ButtonArray extends Block {
	private JFrame frame;
	public final java.lang.String buttons;
	public final java.lang.String title;
	public final boolean vertical;

	public void show() {
		show2(title);
	}

	public void show2(String title) {
		frame = new JFrame(title);
		frame.setLocationByPlatform(true);
		StringTokenizer t = new StringTokenizer(buttons, ",");
		if (vertical) {
			frame.setLayout(new GridLayout(t.countTokens(), 1));
		} else {
			frame.setLayout(new GridLayout(1, t.countTokens()));
		}
		while (t.hasMoreTokens()) {
			String buttonText = t.nextToken().trim();
			final JButton button = new JButton();
			button.setText(buttonText);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sendToBlock("PUSHED", button.getText());
				}
			});
			frame.getContentPane().add(button);
		}
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				sendToBlock("CLOSED");
			}
		});
		frame.setVisible(true);
		frame.pack();
	}

	public void hide() {
		frame.setVisible(false);
	}

	public static void main(String[] args) {
		ButtonArray a = new ButtonArray("Button 1, Button 2, Button 3, Button 4", "Title", true);
		a.show();
	}

	public ButtonArray(java.lang.String buttons, java.lang.String title, boolean vertical) {
		this.buttons = buttons;
		this.title = title;
		this.vertical = vertical;
	}
}
