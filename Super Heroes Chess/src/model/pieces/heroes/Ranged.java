package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.PowerAlreadyUsedException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Ranged extends ActivatablePowerHero {

	public Ranged() {
		super();
	}

	public Ranged(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public void usePower(Direction d, Piece target, Point newPos)
			throws WrongTurnException, PowerAlreadyUsedException,
			InvalidPowerTargetException, InvalidPowerDirectionException {
		super.usePower(d, target, newPos);

		int i = getPosI();
		int j = getPosJ();

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
		
		if((i == 0 && d == Direction.UP)||(i == 6 && d == Direction.DOWN)||(j== 0 && d == Direction.LEFT)||(j == 5 && d == Direction.RIGHT))
				throw new InvalidPowerDirectionException("You are trying to attack in an invalid direction , please choose another one", this, d);

		if (d == Direction.UP || d == Direction.DOWN) { 
			
			while (0 <= i && i <= 6) {
				
				i += change;
				
				if (this.getGame().getCellAt(i, j).getPiece() != null) {
					
					if (this.getGame().getCellAt(i, j).getPiece().getOwner() != this
							.getOwner()) {
						this.attack(this.getGame().getCellAt(i, j).getPiece());
						this.setPowerUsed(true);
						break;
					} else
						throw new InvalidPowerDirectionException(
								"A friendly piece will be hit in this direction "
										+ ", please select another one", this,
								d);
				}
			}
		} 
		else {
			while (0 <= j && j <= 5) {
				
				j += change;
				
				if (this.getGame().getCellAt(i, j).getPiece() != null) {
					if (this.getGame().getCellAt(i, j).getPiece().getOwner() != this
							.getOwner()) {
						this.attack(this.getGame().getCellAt(i, j).getPiece());
						this.setPowerUsed(true);
						break;
					}
					else
						throw new InvalidPowerDirectionException(
								"A friendly piece will be hitin this direction "
										+ ", please select another one", this,
								d);
				}
			}
		}
		
		if (!this.isPowerUsed()) {
			throw new InvalidPowerDirectionException(
					"No Enemies will be hit in this direction ,please select another one",
					this, d);
		}
		else
			this.getGame().switchTurns();
	}
	
	@Override
	public String toString() {
		return "R";
	}
}
