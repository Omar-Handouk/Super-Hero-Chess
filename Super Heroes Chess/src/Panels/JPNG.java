package Panels;

import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import view.JCell;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import frames.JGame;

public class JPNG extends JPanel implements ActionListener {

	private String playerName;
	private JPanel graveyard;
	private JProgressBar payload;
	private JGame JGame;

	public JGame getJGame() {
		return JGame;
	}

	public void setJGame(JGame jGame) {
		JGame = jGame;
	}

	private JCell[] characterButtons;

	private Game game;
	public void paintGraveyard()
	{
		for(int i =0;i<9 && characterButtons[i].getPiece()!= null;i++)
		{
			JCell c = characterButtons[i];
			Piece p = c.getPiece();
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
		}
		this.revalidate();
		this.repaint();
	}
	public JPNG(String playerName, Game game) {

		this.game = game;
		this.playerName = playerName;

		characterButtons = new JCell[9];

		this.setLayout(new BorderLayout(10, 10));
		this.setBorder(new EtchedBorder(EtchedBorder.RAISED));

		try {
			this.initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() throws Exception {

		graveyard = new JPanel(new GridLayout(9, 1));
		graveyard.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5),playerName + " Graveyard"));

		for (int i = 0; i < 9; ++i) {
			JCell cell = new JCell();
			cell.setVisible(false);
			cell.addActionListener(this);
			characterButtons[i] = cell;
			graveyard.add(cell);
		}

		payload = new JProgressBar(SwingConstants.VERTICAL);
		payload.setMaximum(6);
		payload.setPreferredSize(new Dimension(50, 50));

		this.add(graveyard, BorderLayout.CENTER);
		this.add(payload, ((playerName == game.getPlayer1().getName()) ? BorderLayout.WEST : BorderLayout.EAST));

		this.repaint();
		this.revalidate();
	}

	public void updatePayload() {
		if (playerName == game.getPlayer1().getName())
			payload.setValue(game.getPlayer1().getPayloadPos());
		else
			payload.setValue(game.getPlayer2().getPayloadPos());

		System.err.println("I AM HERE " + game.getPlayer1().getPayloadPos());
		System.err.println(game.getPlayer2().getPayloadPos());
	}
	public void removeRevivedCharacter(Piece piece) {

        for (int i = 0 ; i < 9 ; ++i)
        {
            if (characterButtons[i].getPiece() == piece)
            {
                characterButtons[i].setPiece(null);
                characterButtons[i].setVisible(false);
                characterButtons[i].setText("");
                break;
            }
        }
    }
    private void renullify() throws Exception
    {
       this.remove(graveyard);

		graveyard = new JPanel(new GridLayout(9, 1));
		graveyard.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), playerName +" Graveyard"));

		for (int i = 0; i < 9; ++i) {
			JCell cell = new JCell();
			cell.setVisible(false);
			cell.addActionListener(this);
			characterButtons[i] = cell;
			graveyard.add(cell);
		}

		this.add(graveyard, BorderLayout.CENTER);
    }
	  public void updateGraveyards(int playerNumber) throws Exception{

		this.renullify();

	        Player player;
	        if (playerNumber == 1)
	            player = game.getPlayer1();
	        else
	            player = game.getPlayer2();

	        for (Piece piece : player.getDeadCharacters())
	        {
	            int pos = checkFirstEmpty();
	            characterButtons[pos].setPiece(piece);
	            characterButtons[pos].setVisible(true);
	            characterButtons[pos].setText(piece.toString());
	        }
	        paintGraveyard();
	    }

	public void updateGraveyards() {
		
		/*for(JCell c: characterButtons)
		{
			Piece p = c.getPiece();
			if(game.getPlayer1().getDeadCharacters().contains(p) || game.getPlayer2().getDeadCharacters().contains(p))
			{
				
			}
			else
			{
				graveyard.remove(c);
			}
		}*/
		for (Piece piece : game.getCurrentPlayer().getDeadCharacters()) {
			boolean notAdded = true;

			for (int i = 0; i < checkFirstEmpty(); ++i)
				if (piece == characterButtons[i].getPiece())
					notAdded = false;

			if (notAdded) {
				System.out.println("S");
				int pos = checkFirstEmpty();
				characterButtons[pos].setPiece(piece);
				characterButtons[pos].setVisible(true);
				characterButtons[pos].setText(piece.toString());
			}
		}
	}

	private int checkFirstEmpty() {
		for (int i = 0; i < 9; ++i)
			if (characterButtons[i].getPiece() == null)
				return i;
		return -1; // IF no place in graveyard
	}

	public JCell[] getCharacterButtons() {
		return characterButtons;
	}

	public JPanel getGraveyard() {
		return graveyard; 
	}

	public JProgressBar getPayload() {
		return payload;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JCell c = (JCell) e.getSource();
		if (JGame.isMedicpower()) {
			JGame.setRevive_target(c.getPiece());
		/*	if (JGame.getGame().getCurrentPlayer() == JGame.getGame().getPlayer1()) {
				JGame.getPlayerOnePanel().graveyard.remove(c);
			} else
				JGame.getPlayerTwoPanel().graveyard.remove(c);
*/
		}
		JGame.setMedicpower(false);
	}
}
