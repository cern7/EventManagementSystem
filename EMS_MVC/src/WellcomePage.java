import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class WellcomePage implements ActionListener {

	public WellcomePage() {
		createFrame();
	}

	public void createFrame() {
		Border border = BorderFactory.createLineBorder(Color.black, 3);
		Border border2 = BorderFactory.createLineBorder(Color.red, 3);

		// JButton = a button that performs an action when clicked on 
		JButton register = new JButton();
		
		JLabel label = new JLabel(); // creating a label
		label.setText("Wellcome to Home page of the Event Management System"); // set text of label
		label.setForeground(new Color(25, 9, 3));
		label.setFont(new Font("Helvetica", Font.BOLD, 20));
		label.setBackground(new Color(83, 82, 81));
		label.setOpaque(true); // display background color
//		label.setBorder(border);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(0, 0, 1000, 50); // set x,y position within frame as well as dimensions

		JPanel bluePanel = new JPanel();
		bluePanel.setBackground(Color.blue);
		bluePanel.setBounds(251, 1, 100, 100);
		
		JFrame frame = new JFrame("Event Management System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setLayout(null);

		frame.setVisible(true);
		frame.add(label);
		frame.add(bluePanel);

//		frame.pack();// 

	}

//	public void createButtons(JFrame frame) {
//
//		register.setBounds(300, 150, 100, 30);
//		login.setBounds(150, 150, 100, 30);
//		exit.setBounds(500, 399, 60, 25);
//		frame.getContentPane().add(register);
//		frame.getContentPane().add(login);
//		frame.getContentPane().add(exit);
//	}

	public static void main(String[] args) {
		WellcomePage page = new WellcomePage();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
