package model.game;

import java.util.*;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.pieces.Piece;
import model.pieces.heroes.*;
import model.pieces.sidekicks.SideKickP1;
import model.pieces.sidekicks.SideKickP2;

public class Game {
	
	private final int payloadPosTarget = 6;
	private final int boardWidth = 6;
	private final int boardHeight = 7;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private Cell[][] board;

	
	public Game() {

	}

	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		this.currentPlayer = player1;
		this.board = new Cell[boardHeight][boardWidth];
		assemblePieces();
	}

	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public int getPayloadPosTarget() {
		return payloadPosTarget;
	}

	public int getBoardWidth() {
		return boardWidth;
	}

	public int getBoardHeight() {
		return boardHeight;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	

	public void assemblePieces() {
		
		for (int i = 0; i < boardHeight; ++i)
			for (int j = 0; j < boardWidth; ++j)
				board[i][j] = new Cell();
		
		for (int j = 0; j < boardWidth; j++) {
			Piece p1 = new SideKickP1(this, "Duckling Scouts"); 
		
			p1.setPosI(4);
			p1.setPosJ(j);

			Piece p2 = new SideKickP2(this, "Black Mask Gangsters"); 
		
			p2.setPosI(2);
			p2.setPosJ(j);

		
			board[4][j].setPiece(p1);
			board[2][j].setPiece(p2);
		}


		Hero[] heros1 = assemblePiecesHelper(this.getPlayer1(), this, "LOZ",
				"ALGADA BATA", "MAHZOUZ", "SUPER BONDO2", "ABQARINO",
				"SUPER BATOT");


		Hero[] heros2 = assemblePiecesHelper(this.getPlayer2(), this, "DONGOL",
				"___", "SONIA", "ALSHABAH ALASWAD", "", "");
		Random rand = new Random();

		for (int idx = 0; idx < 6; idx++) 
		{
			while (true) {

				int n = rand.nextInt(6);
				if (board[5][n].getPiece() == null) {
					board[5][n].setPiece(heros1[idx]);
					board[5][n].getPiece().setPosI(5);
					board[5][n].getPiece().setPosJ(n);

					break;
				}
			}
		}

		for (int idx = 0; idx < 6; idx++) 
		{
			while (true) {

				int n = rand.nextInt(6);
				if (board[1][n].getPiece() == null) {
					board[1][n].setPiece(heros2[idx]);
					board[1][n].getPiece().setPosI(1);
					board[1][n].getPiece().setPosJ(n);
					break;
				}
			}
		}
	}

	public Cell getCellAt(int i, int j) {
		return board[(i + 7) % 7][(j + 6) % 6];
	}

	public void switchTurns() {

		if (getCurrentPlayer() == getPlayer1()) {
			setCurrentPlayer(getPlayer2());
		} else {
			setCurrentPlayer(getPlayer1());
		}
	}

	public boolean checkWinner() {

		if (currentPlayer.getPayloadPos() == payloadPosTarget) {
			return true;
		}

		return false;
	}

	private Hero[] assemblePiecesHelper(Player player, Game game,
			String armored, String medic, String ranged, String speedster,
			String Super, String tech) {
		Hero[] heroes = { new Armored(player, game, armored),
				new Medic(player, game, medic),
				new Ranged(player, game, ranged),
				new Speedster(player, game, speedster),
				new Super(player, game, Super), new Tech(player, game, tech) };

		return heroes;
	}
	
	@Override
	public String toString() {
		String s = "";
		System.out.println("      " + getPlayer2().getName());
		System.out.print("| ");
		for (int i = 0; i < board[0].length; i++)
			System.out.print("--");
		System.out.println("|");
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == null)
					System.out.print("n ");
				else
					System.out.print(board[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.print("| ");
		for (int i = 0; i < board[0].length; i++)
			System.out.print("--");
		System.out.println("|");
		System.out.println("    " + getPlayer1().getName());
		return s;
	}
	public static void main(String[] args) throws UnallowedMovementException, OccupiedCellException, WrongTurnException {
		Game g = new Game(new Player("!"), new Player("?"));
		Piece p1 = g.getCellAt(5, 4).getPiece();
		Piece p2 = g.getCellAt(1, 1).getPiece();
		System.out.println(g);

		p1.moveDown();
		p2.moveUp();
		
		System.out.println(g);
	}

}
