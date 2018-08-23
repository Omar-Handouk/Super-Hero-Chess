package view;

import javax.swing.JButton;

import model.game.Direction;

public class JDirection extends JButton{
	private Direction r;
	
	public Direction getDirection() {
		return r;
	}

	public JDirection(Direction r) {
		this.r = r;
	}

}
