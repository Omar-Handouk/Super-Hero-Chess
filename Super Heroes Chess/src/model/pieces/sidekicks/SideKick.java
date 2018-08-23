package model.pieces.sidekicks;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import model.game.Cell;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.*;

abstract public class SideKick extends Piece {

	public SideKick() {
		super(null, null, null);
	}

	public SideKick(Player player, Game game, String name) {
		super(player, game, name);
	}
//
//	public void attack(Piece target) {
//		boolean flag = false;
//		if(target instanceof Armored && ((Armored) target).isArmorUp())
//		{
//			flag = true;
//		}
//		
//		if (target instanceof Hero) {
//			if (target instanceof Armored && !flag) {
//				this.getGame()
//						.getCellAt(this.getPosI(), this.getPosJ())
//						.setPiece(
//								new Armored(this.getOwner(), this.getGame(),
//										this.getName()));
//				this.getGame().getCellAt(this.getPosI(), this.getPosJ())
//						.getPiece().setPosI(this.getPosI());
//				this.getGame().getCellAt(this.getPosI(), this.getPosJ())
//				.getPiece().setPosJ(this.getPosJ());
//			}
//
//			}
//				if (target instanceof Medic) {
//					this.getGame()
//							.getCellAt(this.getPosI(), this.getPosJ())
//							.setPiece(
//									new Medic(this.getOwner(), this.getGame(),
//											this.getName()));
//				} 
//					if (target instanceof Ranged) {
//						this.getGame()
//								.getCellAt(this.getPosI(), this.getPosJ())
//								.setPiece(
//										new Ranged(this.getOwner(), this
//												.getGame(), this.getName()));
//					} 
//						if (target instanceof Speedster) {
//							this.getGame()
//									.getCellAt(this.getPosI(), this.getPosJ())
//									.setPiece(
//											new Speedster(this.getOwner(), this
//													.getGame(), this.getName()));
//						} 
//							if (target instanceof Super) {
//								this.getGame()
//										.getCellAt(this.getPosI(),
//												this.getPosJ())
//										.setPiece(
//												new Super(this.getOwner(), this
//														.getGame(), this
//														.getName()));
//							} 
//								if (target instanceof Tech) {
//									this.getGame()
//											.getCellAt(this.getPosI(),
//													this.getPosJ())
//											.setPiece(
//													new Tech(this.getOwner(),
//															this.getGame(),
//															this.getName()));
//								}
//				
//		super.attack(target);}
//	
	public void attack(Piece target) {

		Cell c = getGame().getCellAt(getPosI(), getPosJ());

		if (!(target instanceof SideKick)) {

			if (target instanceof Armored && !((Armored) target).isArmorUp()) {
				c.setPiece(new Armored(getOwner(), getGame(), getName()));
			}

			if (target instanceof Medic) {
				c.setPiece(new Medic(getOwner(), getGame(), getName()));
			}

			if (target instanceof Ranged) {
				c.setPiece(new Ranged(getOwner(), getGame(), getName()));
			}

			if (target instanceof Speedster) {
				c.setPiece(new Speedster(getOwner(), getGame(), getName()));
			}

			if (target instanceof Super) {
				c.setPiece(new Super(getOwner(), getGame(), getName()));
			}

			if (target instanceof Tech) {
				c.setPiece(new Tech(getOwner(), getGame(), getName()));
			}

		}

		super.attack(target);

	}

	@Override
	public String toString() {
		return "K";
	}
}
