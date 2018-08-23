package model.pieces.sidekicks;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;

public class SideKickP1 extends SideKick {

	public SideKickP1() {
		super();
	}

	public SideKickP1(Game game, String name) {
		super(game.getPlayer1(), game, name);
	}
	public void moveDown() throws OccupiedCellException,
			UnallowedMovementException {
		throw  new UnallowedMovementException("INVALID MOVEMENT , Please try another cell" , this , Direction.DOWN) ;

	}
	public void moveDownLeft() throws OccupiedCellException,
			UnallowedMovementException {
		throw  new UnallowedMovementException("INVALID MOVEMENT , Please try another cell" , this , Direction.DOWNLEFT) ;
	}
	public void moveDownRight() throws OccupiedCellException,
			UnallowedMovementException {
		throw  new UnallowedMovementException("INVALID MOVEMENT , Please try another cell" , this , Direction.DOWNRIGHT) ;
	}
}
