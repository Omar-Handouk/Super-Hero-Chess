package Panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import javafx.scene.image.Image;
import model.game.Direction;
import view.JCell;
import view.JDirection;

@SuppressWarnings("serial")
public class Jdirections1 extends JPanel {
	private JDirection up = new JDirection(Direction.UP);
	private JDirection down = new JDirection(Direction.DOWN);
	private	JDirection right = new JDirection(Direction.RIGHT);
	private	JDirection left = new JDirection(Direction.LEFT);
	private JCP JCP;
	
	public Jdirections1() {
		this.setLayout(	new GridLayout(3, 3));
		this.add(new JLabel());
		this.add(up);
		up.setIcon(new ImageIcon("resources/arrows/up .png"));
		up.setOpaque(false);
		
		this.add(new JLabel());
		this.add(left);
		left.setIcon(new ImageIcon("resources/arrows/left.png"));
		
		this.add(new JLabel());
		this.add(right);
		right.setIcon(new ImageIcon("resources/arrows/right.png"));
		
		this.add(new JLabel());
		this.add(down);	
		down.setIcon(new ImageIcon("resources/arrows/down.png"));
		
		this.add(new JLabel());
		
		this.repaint();
		this.revalidate();
	}
	
	public void setActionListeners()
	{
		up.addActionListener(getJCP());
		left.addActionListener(getJCP());
		right.addActionListener(getJCP());
		down.addActionListener(getJCP());
	}
	
	public JCP getJCP() {
		return JCP;
	}
	public void setJCP(JCP jCP) {
		JCP = jCP;
	}
}
