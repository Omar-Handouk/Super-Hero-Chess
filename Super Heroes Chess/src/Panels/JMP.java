package Panels;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class JMP extends JPanel{
	private JButton move = new JButton("move");
	private JButton sp = new JButton("Power");
	private JCP JCP;
	
	public JCP getJCP() {
		return JCP;
	}
	public void setJCP(JCP jCP) {
		JCP = jCP;
	}
	public JButton getMove() {
		return move;
	}
	public JButton getSp() {
		return sp;
	}
	public JMP() {
		this.setLayout(new GridLayout(2,1));
		
		this.add(move);
		this.add(sp);
		

		
		this.repaint();
		this.revalidate();
	}
	
	public void setActionListeners()
	{
		move.addActionListener(getJCP());
		sp.addActionListener(getJCP());
	}
}
