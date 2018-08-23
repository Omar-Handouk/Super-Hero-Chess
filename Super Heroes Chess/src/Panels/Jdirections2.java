package Panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.game.Direction;
import view.JDirection;

public class Jdirections2 extends JPanel {
	private Direction current ;
	private JDirection upright = new JDirection(Direction.UPRIGHT);		
	private JDirection upleft = new JDirection(Direction.UPLEFT);	
	private JDirection downright = new JDirection(Direction.DOWNRIGHT);	
	private JDirection downleft = new JDirection(Direction.DOWNLEFT);	
	private JCP JCP;
	
	public Jdirections2() {
		this.setLayout(new GridLayout(2, 2));
		this.add(upleft);
		upleft.setIcon(new ImageIcon("resources/arrows/left up arrow diagonal .png"));
		
		this.add(upright);
		upright.setIcon(new ImageIcon("resources/arrows/right up arrow diagonal .png"));
		this.add(downleft);
		downleft.setIcon(new ImageIcon("resources/arrows/left down arrow diagonal .png"));
		this.add(downright);
		downright.setIcon(new ImageIcon("resources/arrows/right down arrow diagonal .png"));
		this.repaint();
		this.revalidate();
		
	}
	
	public void setActionListeners()
	{
		upleft.addActionListener(getJCP());
		upright.addActionListener(getJCP());
		downleft.addActionListener(getJCP());
		downright.addActionListener(getJCP());
	}
	
	public JCP getJCP() {
		return JCP;
	}
	public void setJCP(JCP jCP) {
		JCP = jCP;
	}
}
