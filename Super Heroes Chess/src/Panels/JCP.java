package Panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.print.attribute.standard.Media;
import javax.swing.ComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

/*import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
*/
import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import frames.JGame;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKick;
import view.JCell;
import view.JDirection;

public class JCP extends JPanel implements ActionListener {

	private JGame JGame;
	private Direction r;
	private JCell currentCell;
	private JMP MP;
	private Piece target;

	public JCP(JMP MP, Jdirections1 directions1, Jdirections2 directions2, JGame JGame) {
		this.setLayout(new BorderLayout(5, 5));
		this.MP = MP;
		this.add(this.MP, BorderLayout.CENTER);
		MP.setJCP(this);
		this.add(directions1, BorderLayout.EAST);
		directions1.setJCP(this);
		this.add(directions2, BorderLayout.WEST);
		directions2.setJCP(this);
		this.JGame = JGame;
		//
		// System.err.println("JCP'S MP\n" + MP.getJCP());
		// System.err.println("JCP'S D1\n" + directions1.getJCP());
		// System.err.println("JCP'S D2\n" + directions2.getJCP());
		/*
		 * MP.setJCP(this); System.err.println("In JCP Constructor\n" + MP.getJCP());
		 */

		this.repaint();
		this.revalidate();
	}

	public void Gameover(Player player) {
		int l = JOptionPane.showConfirmDialog(null,
				"gameOver\n" + player.getName() + " has Won\n Would you like to play again? ", "GameOver",
				JOptionPane.OK_CANCEL_OPTION);
		if (l == JOptionPane.OK_OPTION) {
			Game game = new Game(new Player(JGame.getGame().getPlayer1().getName()),
					new Player(JGame.getGame().getPlayer2().getName()));

			try {
				JGame gameFrame = new JGame(game);
			} catch (Exception error) {
				error.printStackTrace();
			}
			JGame.setVisible(false);
			JGame.dispose();
		} else {
			JGame.dispose();
		}
	}

	public void whoWon() {
		if (JGame.getGame().getPlayer1().getPayloadPos() == 6) {
			Gameover(JGame.getGame().getPlayer1());
		} else if (JGame.getGame().getPlayer2().getPayloadPos() == 6) {
			Gameover(JGame.getGame().getPlayer2());

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// System.err.println(e.getSource());

		currentCell = JGame.getJBoard().getCurrent(); // Piece to move

		Object check = e.getSource(); // Check if direction, power and move

		if (check instanceof JDirection)
			r = ((JDirection) check).getDirection(); // Update direction if needed

		System.err.println(currentCell);

		if (check == ((JMP) MP).getMove() && r != null && this.currentCell != null) { // This if condition handles
																						// movement
			try {
				Piece currentPiece = currentCell.getPiece();
				System.err.println(JGame.getGame().toString());

				currentPiece.move(r); // This will throw an exception
				System.out.println(currentPiece);
				System.err.println(JGame.getGame().toString());
				/*
				 * int i_new = currentPiece.getPosI(); int j_new = currentPiece.getPosJ();
				 */

				// JGame.getJBoard().repaintBoard();

				JGame.getJBoard().paintIcons();

				/*
				 * System.err.println("I OLD:" + i +"\n" + "J OLD:" + j + "\n" + "I NEW:" +
				 * i_new + "\n" + "J NEW:" +j_new);
				 */

				/*
				 * if (i != i_new || j != j_new) { currentCell.setPiece(null); // ana s7 bs
				 * handouk dma3'o nashfa ImageIcon currentIcon = (ImageIcon)
				 * currentCell.getIcon(); //Moving the icon currentCell.setIcon(null);
				 * JGame.getJBoard().getCells()[i_new][j_new].setPiece(currentPiece);
				 * JGame.getJBoard().getCells()[i_new][j_new].setIcon(currentIcon);
				 */
				// System.out.println(JGame.getGame());
				// }

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid move", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			r = null;
		}
		if (check == ((JMP) MP).getSp() && currentCell.getPiece() instanceof ActivatablePowerHero) {
			if (currentCell.getPiece() instanceof Medic) {
				target = JGame.getRevive_target();

				try {
					((ActivatablePowerHero) currentCell.getPiece()).usePower(r, target, null);
					if (JGame.getGame().getCurrentPlayer() == JGame.getGame().getPlayer1()) {
						// JGame.getPlayerOnePanel().removeRevivedCharacter(target);
						JGame.setMedicpower(false);

					} else
						// JGame.getPlayerTwoPanel().removeRevivedCharacter(target);
						;
				} catch (PowerAlreadyUsedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Power used", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (InvalidPowerTargetException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid Target", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (InvalidPowerDirectionException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid direction",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (WrongTurnException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Wrong turn", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (Exception e1) {

				}

			} else {
				if (currentCell.getPiece() instanceof Tech) {
					// while (true) {
					JFrame f = new JFrame();
					f.setLayout(new BorderLayout());
					f.setSize(400, 300);
					f.setVisible(true);
					JButton btarget = new JButton("Pick a Target");
					f.add(btarget, BorderLayout.WEST);

					btarget.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							JGame.getJBoard().setTechTargetFlag(true);
							f.setAlwaysOnTop(true);
						}
					});
					// JDialog.setDefaultLookAndFeelDecorated(true);
					JButton pos = new JButton("Pick a new Pos");
					f.add(pos, BorderLayout.EAST);
					pos.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							/*
							 * JFrame f2 = new JFrame("Pick a point"); f2.setVisible(true); f2.setSize(
							 * (JGame.isFlag()) ? 505 : 630, (JGame.isFlag()) ? 600 : 750); JPanel
							 * temp_board = new JPanel(new GridLayout(7, 6)); temp_board =
							 * JGame.getJBoard(); f2.add(temp_board); if (temp_board.currentCell)
							 */
							JGame.setTechPower(true);
							f.setAlwaysOnTop(true);
							// f.setState(JFrame.ICONIFIED);

						}
					});
					JButton ok = new JButton("OK");
					f.add(ok, BorderLayout.CENTER);
					ok.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							if (JGame.getJBoard().getTechTarget() != null
									&& JGame.getJBoard().getTechTarget().getOwner() == currentCell.getPiece().getOwner()
									&& JGame.getJBoard().getTechTarget() instanceof SideKick) {
								f.dispose();
							} else {
								try {

									target = JGame.getJBoard().getTechTarget();
									((ActivatablePowerHero) currentCell.getPiece()).usePower(r, target,
											JGame.getNewPos());
									JGame.getJBoard().paintIcons();
									f.dispose();
									// break;
								} catch (PowerAlreadyUsedException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Power used",
											JOptionPane.ERROR_MESSAGE);
									e1.printStackTrace();
								} catch (InvalidPowerTargetException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid Target",
											JOptionPane.ERROR_MESSAGE);
									e1.printStackTrace();
								} catch (InvalidPowerDirectionException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid direction",
											JOptionPane.ERROR_MESSAGE);
									e1.printStackTrace();
								} catch (WrongTurnException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Wrong turn",
											JOptionPane.ERROR_MESSAGE);
									e1.printStackTrace();
									f.dispose();
									// break;
								} catch (NullPointerException e1) {

								}
							}
						}
					});

				} else {
					try {
						((ActivatablePowerHero) currentCell.getPiece()).usePower(r, null, null);
						JGame.getJBoard().paintIcons();
					} catch (PowerAlreadyUsedException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Power used", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (InvalidPowerTargetException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid Target",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (InvalidPowerDirectionException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid direction",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (WrongTurnException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Wrong turn", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
			r = null;
		}
		JGame.getJBoard().paintIcons();

		// target = null;
		// r = null;
		// currentCell = null;
		// JGame.setRevive_target(null);
		JGame.getCurrentplayer().setText(JGame.getGame().getCurrentPlayer().getName());

		JGame.getPlayerOnePanel().updatePayload();
		JGame.getPlayerTwoPanel().updatePayload();

		try

		{
			JGame.getPlayerOnePanel().updateGraveyards(1);
			JGame.getPlayerTwoPanel().updateGraveyards(2);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JGame.getJBoard().setTechTarget(null);
		this.repaint();
		this.revalidate();

		whoWon();
	}
}
