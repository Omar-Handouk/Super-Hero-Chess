package model.pieces.sidekicks;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;

public class SideKickP2 extends SideKick {

	public SideKickP2() {
		super();
	}

	public SideKickP2(Game game, String name) {
		super(game.getPlayer2(), game, name);
	}

	
	public void moveUp() throws OccupiedCellException,
			UnallowedMovementException {
		throw new UnallowedMovementException(
				"INVALID MOVEMENT , Please try another cell", this,
				Direction.UP);

	}

	public void moveUpLeft() throws OccupiedCellException,
			UnallowedMovementException {
		throw new UnallowedMovementException(
				"INVALID MOVEMENT , Please try another cell", this,
				Direction.UPLEFT);
	}

	public void moveUpRight() throws OccupiedCellException,
			UnallowedMovementException {
		throw new UnallowedMovementException(
				"INVALID MOVEMENT , Please try another cell", this,
				Direction.UPRIGHT);
	}
}
