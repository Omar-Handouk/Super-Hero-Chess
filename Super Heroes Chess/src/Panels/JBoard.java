package Panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Hero;
import model.pieces.heroes.Medic;
import frames.JGame;
import model.pieces.heroes.Speedster;
import model.pieces.sidekicks.SideKick;
import view.JCell;

@SuppressWarnings("serial")
public class JBoard extends JPanel implements ActionListener {
	private Game game;
	private JCell[][] cells;
	private JCell current;
	private Piece[] pieces = new Piece[24];
	private JGame JGame;
	private Piece techTarget;
	private boolean techTargetFlag;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int screenWidth = (int) screenSize.getWidth();
	private int screenHeight = (int) screenSize.getHeight();

	public boolean isTechTargetFlag() {
		return techTargetFlag;
	}

	public void setTechTargetFlag(boolean techTargetFlag) {
		this.techTargetFlag = techTargetFlag;
	}

	private Image image; 

	public JBoard(Game game) throws IOException {

		// game = new Game(new Player("1"), new Player("2"));
		this.game = game;
		this.initialize();
	}

	private void initialize() throws IOException {
		if (screenWidth < 1110 || screenHeight < 900) {
			image = Toolkit.getDefaultToolkit().createImage("resources/board.png");
		}
		else
			image = Toolkit.getDefaultToolkit().createImage("resources/board1.png");
		this.setLayout(new GridLayout(7, 6));
		this.cells = new JCell[7][6];

		for (int i = 0; i < game.getBoardHeight(); ++i) {

			for (int j = 0; j < game.getBoardWidth(); ++j) {

				JCell c = new JCell(game.getCellAt(i, j).getPiece());

				c.setI(i);
				c.setJ(j);

				c.addActionListener(this);

				if (c.getPiece() != null) {

					int p_i = 0;
					while (pieces[p_i] != null) {
						p_i++;
					}
					pieces[p_i] = c.getPiece();
					switch (c.getPiece().toString()) {

					case "K":
						c.setIcon(
								(c.getPiece().getOwner() == game.getPlayer1()) ? (new ImageIcon("resources\\Zombies\\2sidekick.png"))
										: (new ImageIcon("resources\\Witches\\sidekick.png")));
						break;

					case "A":
						c.setIcon((c.getPiece().getOwner() == game.getPlayer1())
								? (new ImageIcon("resources\\Zombies\\2armored.png"))
								: (new ImageIcon("resources\\Witches\\armored.png")));
						break;

					case "P":
						c.setIcon(
								(c.getPiece().getOwner() == game.getPlayer1()) ? (new ImageIcon("resources\\Zombies\\2super.png"))
										: (new ImageIcon("resources\\Witches\\super.png")));
						break;

					case "S":
						c.setIcon((c.getPiece().getOwner() == game.getPlayer1())
								? (new ImageIcon("resources\\Zombies\\2speedster.png"))
								: (new ImageIcon("resources\\Witches\\speedster.png")));
						break;

					case "R":
						c.setIcon((c.getPiece().getOwner() == game.getPlayer1())
								? (new ImageIcon("resources\\Zombies\\2ranged.png"))
								: (new ImageIcon("resources\\Witches\\ranged.png")));
						break;

					case "M":
						c.setIcon(
								(c.getPiece().getOwner() == game.getPlayer1()) ? (new ImageIcon("resources\\Zombies\\2medic.png"))
										: (new ImageIcon("resources\\Witches\\medic.png")));
						break;

					case "T":
						c.setIcon(
								(c.getPiece().getOwner() == game.getPlayer1()) ? (new ImageIcon("resources\\Zombies\\2tech.png"))
										: (new ImageIcon("resources\\Witches\\tech.png")));
						break;

					default:
						break;
					}
				}

				c.setContentAreaFilled(false);
				this.cells[i][j] = c;
				this.add(c);
			}
		}

		this.updateToolTips();

	}

	public JCell[][] getCells() {
		return cells;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(image, 0, 0, null);

		this.update();
	}

	public void update() {
		this.repaint();
		this.revalidate();
	}

	public void paintIcons() {

		for (int i = 0; i < game.getBoardHeight(); ++i) {

			for (int j = 0; j < game.getBoardWidth(); ++j) {

				JCell c = cells[i][j];

				Piece p = game.getCellAt(i, j).getPiece();
				c.setPiece(p);
				if (p != null) {

					switch (p.toString()) {

					case "K":
						c.setIcon((p.getOwner() == game.getPlayer1()) ? (new ImageIcon("resources\\Zombies\\2sidekick.png"))
								: (new ImageIcon("resources\\Witches\\sidekick.png")));
						break;

					case "A":
						c.setIcon((p.getOwner() == game.getPlayer1()) ? (new ImageIcon("resources\\Zombies\\2armored.png"))
								: (new ImageIcon("resources\\Witches\\armored.png")));
						break;

					case "P":
						c.setIcon((p.getOwner() == game.getPlayer1()) ? (new ImageIcon("resources\\Zombies\\2super.png"))
								: (new ImageIcon("resources\\Witches\\super.png")));
						break;

					case "S":
						c.setIcon((p.getOwner() == game.getPlayer1()) ? (new ImageIcon("resources\\Zombies\\2speedster.png"))
								: (new ImageIcon("resources\\Witches\\speedster.png")));
						break;

					case "R":
						c.setIcon((p.getOwner() == game.getPlayer1()) ? (new ImageIcon("resources\\Zombies\\2ranged.png"))
								: (new ImageIcon("resources\\Witches\\ranged.png")));
						break;

					case "M":
						c.setIcon((p.getOwner() == game.getPlayer1()) ? (new ImageIcon("resources\\Zombies\\2medic.png"))
								: (new ImageIcon("resources\\Witches\\medic.png")));
						break;

					case "T":
						c.setIcon((p.getOwner() == game.getPlayer1()) ? (new ImageIcon("resources\\Zombies\\2tech.png"))
								: (new ImageIcon("resources\\Witches\\tech.png")));
						break;

					default:
						break;

					}
				} else
					c.setIcon(null);
			}
		}

		this.updateToolTips();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// System.err.println(((JCell) e.getSource()).getName());
		
		JCell check = (JCell) e.getSource();
		if(check.getPiece() != null && check.getPiece() instanceof Medic)
			JGame.setMedicpower(true);
		else {
			JGame.setMedicpower(false);
			JGame.setRevive_target(null);
		}


		if (!(JGame.isTechPower())) {
			if (check.getPiece() != null) { // && check.getPiece().getOwner() == game.getCurrentPlayer()
				if(!(isTechTargetFlag()))
					current = check;
				else
					setTechTarget(check.getPiece());
				setTechTargetFlag(false);
			}
		} else {
			JGame.setNewPos(new Point(check.getI(), check.getJ()));
			JGame.setTechPower(false);

		}

		// System.err.println(check);

		// current = null;
	}

	public JCell getCurrent() {
		return current;
	}

	public void setCurrent(JCell jCell) {
		this.current = jCell;
	}

	public Piece[] getPieces() {
		return pieces;
	}

	// public void setPieces(ArrayList<Piece> pieces) {
	// this.pieces = pieces;
	// }

	public void repaintBoard() {

		for (int row = 0; row < game.getBoardHeight(); ++row) {
			for (int col = 0; col < game.getBoardWidth(); ++col) {
				Piece piece = game.getCellAt(row, col).getPiece();

				JCell currentCell = cells[row][col];

				if (piece != null) {
					switch (piece.toString()) {
					case "K":
						if (piece.getOwner() == game.getPlayer1()) {
							currentCell.setIcon(new ImageIcon("C:\\Users\\ahmed\\eclipse-workspace\\Super Heroes Chess\\resources\\arrows\\up.png"));
						} else {
							currentCell.setIcon(new ImageIcon("resources/SecurityAndMaintenance_Error.png"));
						}
						break;
					case "A":
						if (piece.getOwner() == game.getPlayer1()) {
							currentCell.setIcon(new ImageIcon("resources/2armored.png"));
						} else {
							currentCell.setIcon(new ImageIcon("resources/armored.png"));
						}
						break;
					case "P":
						if (piece.getOwner() == game.getPlayer1()) {
							currentCell.setIcon(new ImageIcon("resources/2super.png"));
						} else {
							currentCell.setIcon(new ImageIcon("resources/super.png"));
						}
						break;
					case "S":
						if (piece.getOwner() == game.getPlayer1()) {
							currentCell.setIcon(new ImageIcon("resources/2speedster.png"));
						} else {
							currentCell.setIcon(new ImageIcon("resources/speedster.png"));
						}
						break;
					case "R":
						if (piece.getOwner() == game.getPlayer1()) {
							currentCell.setIcon(new ImageIcon("resources/2ranged.png"));
						} else {
							currentCell.setIcon(new ImageIcon("resources/ranged.png"));
						}
						break;
					case "M":
						if (piece.getOwner() == game.getPlayer1()) {
							currentCell.setIcon(new ImageIcon("resources/2medic.png"));
						} else {
							currentCell.setIcon(new ImageIcon("resources/medic.png"));
						}
						break;
					case "T":
						if (piece.getOwner() == game.getPlayer1()) {
							currentCell.setIcon(new ImageIcon("resources/2tech.png"));
						} else {
							currentCell.setIcon(new ImageIcon("resources/tech.png"));
						}
						break;

					}

				} else {
					currentCell.setIcon(null);
				}
			}
		}

		this.current = null;

	}

	public String[] piecesNames() {
		updatePieces();
		int l = pieces.length;
		String[] names = new String[l];
		for (int i = 0; i < l; i++) {
			if (pieces[i].getOwner() == JGame.getGame().getPlayer1())
				names[i] = pieces[i].toString() + "1";
			else
				names[i] = pieces[i].toString() + "2";
		}
		return names;
	}

	public String[] herosNames() {
		updatePieces();
		int l = 0;
		for (int i = 0; i < pieces.length; i++) {
			if (pieces[i] instanceof ActivatablePowerHero || pieces[i] instanceof Armored)
				l++;
		}
		String[] names = new String[l];
		int names_i = -1;
		for (int i2 = 0; i2 < pieces.length; i2++) {
			if (pieces[i2] instanceof ActivatablePowerHero || pieces[i2] instanceof Armored) {
				names_i++;
				if (pieces[i2].getOwner() == JGame.getGame().getPlayer1())
					names[names_i] = pieces[i2].toString() + "1";
				else
					names[names_i] = pieces[i2].toString() + "2";
			}
		}
		return names;
	}

	public void setJGame(frames.JGame jGame2) {
		JGame = jGame2;
	}

	private String findType(String name) {
		switch (name) {

		case "K":
			return "Sidekick";

		case "A":
			return "Armoured";

		case "P":
			return "Super";

		case "S":
			return "Speedster";

		case "R":
			return "Ranged";

		case "M":
			return "Medic";

		case "T":
			return "Tech";

		}

		return "Undefined";
	}

	public void updateToolTips() {

		for (int i = 0; i < 7; ++i) {
			for (int j = 0; j < 6; ++j) {
				Piece piece = cells[i][j].getPiece();

				if (piece == null) {
					cells[i][j].setToolTipText(null);
				} else if (piece instanceof ActivatablePowerHero) {
					String owner = piece.getOwner().getName();
					boolean power = ((ActivatablePowerHero) piece).isPowerUsed();
					String type = findType(piece.toString());
					String name = piece.toString();

					cells[i][j].setToolTipText(
							"<html><b>Owner: </b>" + owner + "<br>" + "<b>Is Power Used: </b>" + power + "<br>"
									+ "<b>Piece Type: </b>" + type + "<br>" + "<b>Piece Name: </b>" + name + "</html>");
				} else if (piece instanceof Armored) {
					String owner = piece.getOwner().getName();
					boolean power = ((Armored) piece).isArmorUp();
					String type = findType(piece.toString());
					String name = piece.toString();

					cells[i][j].setToolTipText(
							"<html><b>Owner: </b>" + owner + "<br>" + "<b>Is Armor UP: </b>" + power + "<br>"
									+ "<b>Piece Type: </b>" + type + "<br>" + "<b>Piece Name: </b>" + name + "</html>");
				} else if (piece instanceof Speedster) {
					String owner = piece.getOwner().getName();
					String power = "<b>Power: </b>Passive";
					String type = findType(piece.toString());
					String name = piece.toString();

					cells[i][j].setToolTipText("<html><b>Owner: </b>" + owner + "<br>" + power + "<br>"
							+ "<b>Piece Type: </b>" + type + "<br>" + "<b>Piece Name: </b>" + name + "</html>");
				} else if (piece instanceof SideKick) {
					String owner = piece.getOwner().getName();
					String type = findType(piece.toString());
					String name = piece.toString();

					cells[i][j].setToolTipText("<html><b>Owner: </b>" + owner + "<br>" + "<b>Piece Type: </b>" + type
							+ "<br>" + "<b>Piece Name: </b>" + name + "</html>");
				}
			}
		}
	}

	public void updatePieces() {
		int p_i = 0;
		int p_length = 0;
		for (int i = 0; i < game.getBoardHeight(); ++i) {
			for (int j = 0; j < game.getBoardWidth(); ++j) {
				Piece p = cells[i][j].getPiece();
				if (p != null) {
					p_length++;
				}
			}
		}
		pieces = new Piece[p_length];
		for (int i = 0; i < game.getBoardHeight(); ++i) {
			for (int j = 0; j < game.getBoardWidth(); ++j) {
				Piece p = cells[i][j].getPiece();
				if (p != null) {
					pieces[p_i] = p;
					p_i++;
				}
			}
		}
	}

	public Piece getTechTarget() {
		return techTarget;
	}

	public void setTechTarget(Piece techTarget) {
		this.techTarget = techTarget;
	}
}
