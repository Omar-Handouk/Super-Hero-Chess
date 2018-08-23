package exceptions;

import model.game.Direction;
import model.pieces.Piece;

abstract public class InvalidMovementException extends GameActionException {
	private Direction direction;

	public InvalidMovementException() {
		super();
	}

	public InvalidMovementException(String s, Piece trigger, Direction direction) {
		super(s, trigger);
		this.direction = direction;
	}

	public InvalidMovementException(Piece trigger, Direction direction) {
		super(trigger);
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}
}
