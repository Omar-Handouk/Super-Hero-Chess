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

public class Tech extends ActivatablePowerHero {

	public Tech() {
		super();
	}

	public Tech(Player player, Game game, String name) {
		super(player, game, name);
	}

	
	public void moveDown() throws OccupiedCellException,
			UnallowedMovementException {
		throw new UnallowedMovementException(
				"INVALID MOVEMENT , Please try another cell", this,
				Direction.DOWN);

	}

	public void moveUp() throws OccupiedCellException,
			UnallowedMovementException {
		throw new UnallowedMovementException(
				"INVALID MOVEMENT , Please try another cell", this,
				Direction.UP);
	}

	public void moveRight() throws OccupiedCellException,
			UnallowedMovementException {
		throw new UnallowedMovementException(
				"INVALID MOVEMENT , Please try another cell", this,
				Direction.RIGHT);
	}

	public void moveLeft() throws OccupiedCellException,
			UnallowedMovementException {
		throw new UnallowedMovementException(
				"INVALID MOVEMENT , Please try another cell", this,
				Direction.LEFT);

	}

	@Override
	public void usePower(Direction d, Piece target, Point newPos)
			throws WrongTurnException, PowerAlreadyUsedException,
			InvalidPowerTargetException, InvalidPowerDirectionException {
		super.usePower(d, target, newPos);
		if (target.getOwner() == this.getOwner()) {
			if (newPos == null) 
			{
				if (target instanceof ActivatablePowerHero) { 
					if (((ActivatablePowerHero) target).isPowerUsed()){
						((ActivatablePowerHero) target).setPowerUsed(false);
						
					}
					else
						throw new InvalidPowerTargetException(
								"The selected target didn't use its power, please select a valid piece",
								this, target);
				} else if (target instanceof Armored) 
				{
					if (!((Armored) target).isArmorUp()){
						((Armored) target).setArmorUp(true);
					}
					else
						throw new InvalidPowerTargetException(
								"The selected target didn't use its armour, please select a valid piece",
								this, target);
				}
			} else { 
				if (this.getOwner() != target.getOwner()) {
					throw new InvalidPowerTargetException(
							"you selected an enemy piece to teleport , please select your own pieces to telebort",
							this, target);
				} else if (this.getGame().getCellAt(newPos.x, newPos.y).getPiece() == null) {
					
					this.getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
					target.setPosI(newPos.x);
					target.setPosJ(newPos.y);
					
					this.getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(target);
				} else{
					
					throw new InvalidPowerTargetException(
							"the cell is already Occupied ,please choose another cell",
							this, target);
			}}
		} else 
		{
			if(newPos != null)
			{
				throw new InvalidPowerTargetException(
						"you selected an enemy piece to teleport , please select your own pieces to telebort",
						this, target);
			}
			if (target instanceof ActivatablePowerHero) {
				if (!(((ActivatablePowerHero) target).isPowerUsed())){
					((ActivatablePowerHero) target).setPowerUsed(true);

				}
				else
					throw new InvalidPowerTargetException(
							"The selected target already used its power, please select a valid piece",
							this, target);
			} else if (target instanceof Armored) {
				if (((Armored) target).isArmorUp()){
					((Armored) target).setArmorUp(false);}
				else
					throw new InvalidPowerTargetException(
							"The selected target already used its armor, please select a valid piece",
							this, target);
			}
			
			else
				throw new InvalidPowerTargetException(
						"The selected target cannot be hacked, please select a valid piece",
						this, target);
		}

		this.setPowerUsed(true);
	}
	
	@Override
	public String toString() {
		return "T";
	}
}
