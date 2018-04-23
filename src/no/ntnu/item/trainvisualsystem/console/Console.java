package no.ntnu.item.trainvisualsystem.console;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import no.ntnu.item.arctis.runtime.Block;

public class Console extends Block {
	private JFrame frame;
	private JTextArea textArea;

	public void show(String title) {
		
		frame = new JFrame(title);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = new Dimension((int) (screenSize.width / 2), (int) (screenSize.height / 2));
		int x = (int) (frameSize.width / 2);
		int y = (int) (frameSize.height / 2);
		frame.setBounds(x, y, frameSize.width, frameSize.height);
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(Color.black);
		textArea.setForeground(Color.white);
		textArea.setBorder(null);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
		JButton button = new JButton("Clear");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		frame.getContentPane().add(button, BorderLayout.SOUTH);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				sendToBlock("CLOSED");
			}
		});
		frame.setVisible(true);
	}

	public void appendString(String string) {
		textArea.append("" + string + "\n");
	}


}
