package exceptions;

import model.pieces.Piece;

public class WrongTurnException extends GameActionException {

	public WrongTurnException() {
		super();	}

	public WrongTurnException(Piece trigger) {
		super(trigger);
	}

	public WrongTurnException(String s, Piece trigger) {
		super(s, trigger);
	}


}
