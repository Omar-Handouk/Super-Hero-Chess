package model.pieces;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.*;

public interface Movable {
	void move(Direction r) throws OccupiedCellException, UnallowedMovementException, WrongTurnException;
	void moveDown() throws OccupiedCellException, UnallowedMovementException, WrongTurnException;
	void moveDownLeft() throws OccupiedCellException, UnallowedMovementException, WrongTurnException;
	void moveDownRight() throws OccupiedCellException, UnallowedMovementException, WrongTurnException;
	void moveUp() throws OccupiedCellException, UnallowedMovementException, WrongTurnException;
	void moveUpRight() throws OccupiedCellException, UnallowedMovementException, WrongTurnException;
	void moveUpLeft() throws OccupiedCellException, UnallowedMovementException, WrongTurnException;
	void moveRight() throws OccupiedCellException, UnallowedMovementException, WrongTurnException;
	void moveLeft() throws OccupiedCellException, UnallowedMovementException, WrongTurnException;
}
