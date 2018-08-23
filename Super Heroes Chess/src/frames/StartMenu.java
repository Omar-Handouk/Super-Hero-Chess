package frames;

import java.awt.*;

import javax.swing.*;

import Panels.StartMenuPanel;

public class StartMenu extends JFrame{
	public StartMenu() {

		this.setTitle("SUPER HERO CHESS");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

		this.getContentPane().add(new StartMenuPanel(this));

		this.pack();
		this.repaint();
		this.revalidate();

		this.setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(StartMenu::new);
	}
}
