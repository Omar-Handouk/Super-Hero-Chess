package model.game;

import model.pieces.*;

public class Cell {
	private Piece piece;

	public Cell() {

	}

	public Cell(Piece piece) {
		this.piece = piece;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	@Override
	public String toString() {
		if(piece != null)
			return piece.toString();
		
		return "n";
	}
}
