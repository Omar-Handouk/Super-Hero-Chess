package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.InvalidPowerUseException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Medic extends ActivatablePowerHero {

	private static final Exception UnallowedMovementException = null;

	public Medic() {
		super();
	}

	public Medic(Player player, Game game, String name) {
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

	
	@Override
	public void usePower(Direction d, Piece target, Point newPos)
			throws WrongTurnException, PowerAlreadyUsedException,
			InvalidPowerTargetException, InvalidPowerDirectionException {

		super.usePower(d, target, newPos);
		if (target.getOwner() != this.getOwner()){
			throw new InvalidPowerTargetException(
					"Enemy target selected, please choose a friendly piece to revive",
					this, target);}
		boolean t = this.getOwner().getDeadCharacters().contains(target);

		if (!t)
			throw new InvalidPowerTargetException(
					"Target is already alive, please select a dead target",
					this, target);

		int i = getPosI();
		int j = getPosJ();
		switch (d) {
		case UP:
			i--;
			break;
		case DOWN:
			i++;
			break;
		case RIGHT:
			j++;
			break;
		case LEFT:
			j--;
			break;
		case UPRIGHT:
			i--;
			j++;
			break;
		case UPLEFT:
			i--;
			j--;
			break;
		case DOWNRIGHT:
			i++;
			j++;
			break;
		case DOWNLEFT:
			i++;
			j--;
			break;
		}
		
		if (i > 6 || i < 0 || j > 5 || j < 0)
		{
			this.setPowerUsed(true);
			this.getGame().switchTurns();
		}
		
			if (this.getGame().getCellAt(i, j).getPiece() == null) {
				this.getGame().getCellAt(i, j).setPiece(target);
				this.getOwner().getDeadCharacters().remove(target);
				target.setPosI(i);
				target.setPosJ(j);
				if(target instanceof ActivatablePowerHero)
					((ActivatablePowerHero) target).setPowerUsed(false);
				else
					if(target instanceof Armored)
						((Armored) target).setArmorUp(true);

			} else
				throw new InvalidPowerTargetException(
						"You selected an Occupied Cell , please select another",
						this, target);

		
		this.setPowerUsed(true);
		this.getGame().switchTurns();

	}
	
	@Override
	public String toString() {
		return "M";
	}
}
