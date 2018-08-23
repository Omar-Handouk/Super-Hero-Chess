package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Super extends ActivatablePowerHero {

	public Super() {
		super();
	}

	public Super(Player player, Game game, String name) {
		super(player, game, name);
	}

	public void moveDownLeft() throws OccupiedCellException,
			UnallowedMovementException {
		throw new UnallowedMovementException(
				"INVALID MOVEMENT , Please try another cell", this,
				Direction.DOWNLEFT);
	}

	public void moveDownRight() throws OccupiedCellException,
			UnallowedMovementException {
		throw new UnallowedMovementException(
				"INVALID MOVEMENT , Please try another cell", this,
				Direction.DOWNRIGHT);
	}

	public void moveUpLeft() throws OccupiedCellException,
			exceptions.UnallowedMovementException {
		throw new UnallowedMovementException(
				"INVALID MOVEMENT , Please try another cell", this,
				Direction.UPLEFT);
	}

	public void moveUpRight() throws OccupiedCellException,
			exceptions.UnallowedMovementException {
		throw new UnallowedMovementException(
				"INVALID MOVEMENT , Please try another cell", this,
				Direction.UPRIGHT);
	}

	public void usePower(Direction d, Piece target, Point newPos)
			throws WrongTurnException, PowerAlreadyUsedException,
			InvalidPowerTargetException, InvalidPowerDirectionException {

		super.usePower(d, target, newPos); 

		int i = getPosI();
		int j = getPosJ();
		int c = 0; 

		int change;
		switch (d) {

		case UP:
		case LEFT:
			change = -1;
			break;
		case DOWN:
		case RIGHT:
			change = 1;
			break;
		default:
			throw new InvalidPowerDirectionException(
					"The direction selected is invalid, please select a valid direction",
					this, d);
		}
		if (d == Direction.UP || d == Direction.DOWN) { 
			while (0 <= i && i <= 6 && c != 2) {

				i += change;
				if(!(0 <= i && i <= 6))
					break;
				Piece tempTarget = this.getGame().getCellAt(i, j).getPiece();
				if (tempTarget != null
						&& tempTarget.getOwner() != this.getOwner()) {
					
					this.attack(tempTarget);

				}
				c += 1;
			}
		} else {
			while (0 <= j && j <= 5 && c != 2) {
				j += change;
				if(!(0 <= j && j <= 5))
					break;
				Piece tempTarget = this.getGame().getCellAt(i, j).getPiece();
				if (tempTarget != null
						&& tempTarget.getOwner() != this.getOwner()) {
					
					this.attack(tempTarget);

				}
				c += 1;
			}
		}
		this.setPowerUsed(true);
		this.getGame().switchTurns();

	}

	@Override
	public String toString() {
		return "P";
	}
}
