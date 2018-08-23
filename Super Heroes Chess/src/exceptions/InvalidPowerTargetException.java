package exceptions;

import model.pieces.Piece;

public class InvalidPowerTargetException extends InvalidPowerUseException {
	// ATTRIBUTES
	private Piece target;

	public InvalidPowerTargetException() {
		super();
	}

	public InvalidPowerTargetException(String s, Piece trigger, Piece target) {
		super(s, trigger);
		this.target = target;
	}

	public InvalidPowerTargetException(Piece trigger, Piece target) {
		super(trigger);
		this.target = target;
	}

	public Piece getTarget() {
		return target;
	}

}
