import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GraphicalUserInterface extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton registerBtn, loginBtn, exitBtn;
	JPanel titlePanel, footerPanel, bodyPanel;
	JLabel titleLabel, footerLabel;
	
	/*
	 * Layout Manager = Defines the natural layout for components whitin a container
	 * 
	 * 3 common managers:
	 * 
	 * BorderLayout = A borderLayout places components in five areas: NORTH, SOUTH,
	 * EAST,CENTER
	 * 
	 * FlowLayout = places components in row, sized at their preferred size. If the
	 * horizontal space in the container is too small, the FlowLayout class uses the
	 * next available row.
	 * 
	 * GridLayout = places components in a grid of cells. Each component takes all
	 * the available space within its cell and each cell is the same size
	 * 
	 * 
	 * JLayeredPane = Swing container that provides a third dimension for
	 * positioning components ex: depth, Z-index
	 * 
	 * JOptionPane = pop up a standard dialog box that prompts user for a value of
	 * inform them of something
	 */

	public GraphicalUserInterface() {
//		ImageIcon registerIcon = new ImageIcon("register.jpeg");

		// Register Button
		registerBtn = new JButton();
		registerBtn.setBounds(200, 100, 100, 50);
//		registerBtn.addActionListener(e -> System.out.println("click"));
		registerBtn.addActionListener(this);
		registerBtn.setText("Register");
		registerBtn.setFocusable(false);
//		registerBtn.setIcon(registerIcon);
		registerBtn.setHorizontalTextPosition(JButton.CENTER);
		registerBtn.setVerticalTextPosition(JButton.BOTTOM);
		registerBtn.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 15));
		registerBtn.setForeground(new Color(197, 197, 197));
		registerBtn.setBackground(new Color(0x171f27));
		registerBtn.setOpaque(false);
		registerBtn.setBorder(BorderFactory.createEmptyBorder());

		// Login Button
		loginBtn = new JButton("Login");
		loginBtn.setBounds(200, 100, 100, 50);
		loginBtn.addActionListener(this);
		loginBtn.setFocusable(false);
		loginBtn.setHorizontalTextPosition(JButton.CENTER);
		loginBtn.setVerticalTextPosition(JButton.BOTTOM);
		loginBtn.setFont(new Font("Helvetica", Font.PLAIN, 15));
		loginBtn.setForeground(new Color(197, 197, 197));
		loginBtn.setBackground(new Color(0x171f27));
		loginBtn.setOpaque(false);
		loginBtn.setBorder(BorderFactory.createEmptyBorder());

		// Exit Button
		exitBtn = new JButton();
		exitBtn.setBounds(200, 100, 100, 50);
//		registerBtn.addActionListener(e -> System.out.println("click"));
		exitBtn.addActionListener(this);
		exitBtn.setText("Exit");
		exitBtn.setFocusable(false);
//		registerBtn.setIcon(registerIcon);
		exitBtn.setHorizontalTextPosition(JButton.CENTER);
		exitBtn.setVerticalTextPosition(JButton.BOTTOM);
		exitBtn.setFont(new Font("Helvetica", Font.BOLD, 15));
		exitBtn.setForeground(new Color(197, 197, 197));
		exitBtn.setBackground(new Color(0x171f27));
		exitBtn.setOpaque(false);
		exitBtn.setBorder(BorderFactory.createEmptyBorder());

		// creating three JPanel s for title, body and footer
		titlePanel = new JPanel();
		bodyPanel = new JPanel();
		footerPanel = new JPanel();

		titlePanel.setBackground(new Color(11, 16, 32));
		bodyPanel.setBackground(new Color(40, 47, 71));
		footerPanel.setBackground(new Color(8, 9, 14));

		titlePanel.setPreferredSize(new Dimension(0, 50));
		bodyPanel.setPreferredSize(new Dimension(0, 500));
		footerPanel.setPreferredSize(new Dimension(0, 100));

		// creating JLabel s
		titleLabel = new JLabel("Event Management System");
		titleLabel.setForeground(new Color(212, 213, 218));
		titleLabel.setFont(new Font("Helvetica", Font.PLAIN, 35));
//		titleLabel.setBackground(new Color(83, 82, 81));
//		titleLabel.setOpaque(true); // display background color

		footerLabel = new JLabel("KernelSystem\u00A9");
		footerLabel.setForeground(new Color(212, 213, 218));
		footerLabel.setFont(new Font("Helvetica", Font.ITALIC, 15));
		
		
		
		
		// -------------------SUB PANELS----------------------//

		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(titleLabel);

		bodyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 100));
		bodyPanel.add(registerBtn);
		bodyPanel.add(loginBtn);
		bodyPanel.add(exitBtn);

		footerPanel.setLayout(new FlowLayout());
		footerPanel.add(footerLabel);

		// -------------------SUB PANELS----------------------//
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout(0, 1));
		this.setSize(700, 700);
		this.setVisible(true);

		this.add(titlePanel, BorderLayout.NORTH);
		this.add(bodyPanel, BorderLayout.CENTER);
		this.add(footerPanel, BorderLayout.SOUTH);

	}

	public static void main(String[] args) {
		new GraphicalUserInterface();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == registerBtn) {
			System.out.println("click");
			// creating JOptionPane dialog box
			JOptionPane.showMessageDialog(null, "Registration began", "register", JOptionPane.INFORMATION_MESSAGE);
			registerBtn.setEnabled(false);
		}
		if (arg0.getSource() == exitBtn) {
			this.dispose();
		}

	}

}
