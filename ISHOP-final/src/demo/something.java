package demo;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class something extends JFrame{
	public static void main(String[] args) {
		new something();
	}
	
	something(){
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, 820, 950);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setLayout(null);

		final JScrollPane scrollPanel = new JScrollPane(
		    panel,
		    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
		);
		
		scrollPanel.setBounds(10, 10, 800, 900);
		panel.setBackground(Color.black);
		panel.setBounds(0, 0, 1920, 1080);
		panel.setPreferredSize(new Dimension(1920, 1080));
		frame.getContentPane().add(scrollPanel);
		
		frame.setLayout(null);
		setBounds(0,0,5000,5000);
		setVisible(true);
	}
}
