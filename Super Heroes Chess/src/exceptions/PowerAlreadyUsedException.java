package exceptions;

import model.pieces.Piece;

public class PowerAlreadyUsedException extends InvalidPowerUseException {

	public PowerAlreadyUsedException() {
		super();
	}

	public PowerAlreadyUsedException(String s, Piece trigger) {
		super(s, trigger);
	}

	public PowerAlreadyUsedException(Piece trigger) {
		super(trigger);
	}

}
