package exceptions;

import model.pieces.Piece;

abstract public class InvalidPowerUseException extends GameActionException {

	public InvalidPowerUseException() {
		super();
	}

	public InvalidPowerUseException(String s, Piece trigger) {
		super(s, trigger);
	}

	public InvalidPowerUseException(Piece trigger) {
		super(trigger);
	}

}
