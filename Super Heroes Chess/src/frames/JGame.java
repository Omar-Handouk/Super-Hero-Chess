package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Panels.JBoard;
import Panels.JCP;
import Panels.JMP;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import Panels.JPNG;
import Panels.Jdirections1;
import Panels.Jdirections2;

@SuppressWarnings("serial")
public class JGame extends JFrame {

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int screenWidth = (int) screenSize.getWidth();
	private int screenHeight = (int) screenSize.getHeight();
	private Game game;
	private Point newPos;
	private Image image;

	private JBoard JBoard;
	private JCP JCP;
	private boolean flag;// is your screen "not "ready ?
	private boolean techPowerPos;
	private boolean medicpower;
	private JPNG playerOnePanel;
	private JPNG playerTwoPanel;
	private Piece revive_target;
	private JLabel currentplayer;

	// Add panel to put contents in

	public Game getGame() {
		return game;
	}

	public Piece getRevive_target() {
		return revive_target;
	}

	public void setRevive_target(Piece revive_target) {
		this.revive_target = revive_target;
	}

	public JGame(Game game) throws IOException {

		if (screenWidth < 1110 || screenHeight < 900) {
			flag = true;
			image = Toolkit.getDefaultToolkit().createImage("resources/background.png");
		} else
			image = Toolkit.getDefaultToolkit().createImage("resources/background2.png");

		this.setContentPane(new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.drawImage(image, 0, 0, null);
				this.repaint();
				this.revalidate();
			}
		});
		this.game = game;

		this.setSize((isFlag()) ? 1016 : 1290, (isFlag()) ? 720 : 940);
		this.setLayout(null);
		this.setTitle("SUPER HERO CHESS");
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JBoard = new JBoard(game);
		// Player one's Payload and Graveyard panel.
		playerOnePanel = new JPNG(game.getPlayer1().getName(), game);
		playerOnePanel.setJGame(this);
		playerOnePanel.getGraveyard().setSize((flag) ? new Dimension(140, 548) : new Dimension(173, 662));
		playerOnePanel.setBounds(0, 0, (flag) ? 200 : 243, (flag) ? 554 : 664);

		// Player two's Payload and Graveyard panel.
		playerTwoPanel = new JPNG(game.getPlayer2().getName(), game);
		playerTwoPanel.setJGame(this);
		playerTwoPanel.getGraveyard().setSize((flag) ? new Dimension(140, 548) : new Dimension(173, 662));
		playerTwoPanel.setBounds((flag) ? 810 : 1030, 0, (flag) ? 200 : 243, (flag) ? 554 : 664);

		/*
		 * JButton g1 = new JButton("Grave1"); JButton g2 = new JButton("Grave2");
		 * JButton pay1 = new JButton("PayLoad1"); JButton pay2 = new
		 * JButton("PayLoad2");
		 */
		//
		// JButton up = new JButton("UP");
		// //up.setBorder(new EmptyBorder(5, 5, 5, 5));
		// JButton down = new JButton("DOWN");
		// //down.setBorder(new EmptyBorder(5, 5, 5, 5));
		// JButton right = new JButton("RIGHT");
		// //right.setBorder(new EmptyBorder(5, 5, 5, 5));
		// JButton left = new JButton("LEFT");
		// //left.setBorder(new EmptyBorder(5, 5, 5, 5));
		// JButton move = new JButton("move");
		// JButton sp = new JButton("Power");
		// JButton upright = new JButton("UPright");
		// JButton downright = new JButton("downright");
		// JButton downleft = new JButton("downleft");
		// JButton upleft = new JButton("upleft");

		/*
		 * JPanel p1 = new JPanel(new BorderLayout()); JPanel p2 = new JPanel(new
		 * BorderLayout());
		 */
		// JPanel CP = new JPanel(new BorderLayout(5,5));
		// JPanel directions1 = new JPanel(new GridLayout(3, 3));
		// JPanel directions2 = new JPanel(new GridLayout(2, 2));
		// JPanel MP = new JPanel(new GridLayout(2,1));
		//
		// MP.add(move);
		// MP.add(sp);
		//
		// CP.add(MP, BorderLayout.CENTER);
		// CP.add(directions1, BorderLayout.EAST);
		//// directions1.add(new JLabel());
		// directions1.add(up);
		// directions1.add(new JLabel());
		// directions1.add(left);
		// directions1.add(new JLabel());
		// directions1.add(right);
		// directions1.add(new JLabel());
		// directions1.add(down);
		// directions1.add(new JLabel());
		//
		// CP.add(directions2, BorderLayout.WEST);
		// directions2.add(upleft);
		// directions2.add(upright);
		// directions2.add(downleft);
		// directions2.add(downright);
		//

		/*
		 * p1.add(g1,BorderLayout.CENTER); p1.add(pay1,BorderLayout.WEST);
		 * p2.add(pay2,BorderLayout.EAST); p2.add(g2,BorderLayout.CENTER);
		 */
		currentplayer = new JLabel(game.getCurrentPlayer().getName());
		this.add(currentplayer);
		JLabel l = new JLabel("Current player :");
		l.setBounds(1000, 650, 200, 100);
		l.setFont(new Font("Monospaced", Font.BOLD, 20));
		l.setVisible(true);
		this.add(l);
		currentplayer.setBounds(1100, 700, 200, 100);
		currentplayer.setBackground(Color.green);
		currentplayer.setFont(new Font("Monospaced", Font.BOLD, 20));
		currentplayer.setVisible(true);
		JMP jmp = new JMP();
		Jdirections1 jdirections1 = new Jdirections1();
		Jdirections2 jdirections2 = new Jdirections2();

		JCP = new JCP(jmp, jdirections1, jdirections2, this);
		jmp.setActionListeners();
		jdirections1.setActionListeners();
		jdirections2.setActionListeners();

		this.getContentPane().add(playerOnePanel);
		this.getContentPane().add(playerTwoPanel);
		this.getContentPane().add(JBoard);
		this.getContentPane().add(JCP);

		JBoard.setBounds((isFlag()) ? 256 : 320, 0, (isFlag()) ? 505 : 630, (isFlag()) ? 600 : 750);
		JCP.setBounds((isFlag()) ? 256 : 320, (isFlag()) ? 610 : 752, (isFlag()) ? 505 : 630, (isFlag()) ? 117 : 144);

		this.setVisible(true);

		this.update();
		JBoard.setJGame(this);

	}

	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(image, 0, 0, null);

		this.update();
	}

	private void update() {
		this.repaint();
		this.revalidate();
	}

	public JBoard getJBoard() {
		return JBoard;
	}

	public boolean isFlag() {
		return flag;
	}

	public static void main(String[] args) throws IOException {

		EventQueue.invokeLater(() -> {
			JGame game;
			try {
				game = new JGame(new Game(new Player(""), new Player("")));
				game.setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public void setTechPower(boolean b) {
		techPowerPos = b;

	}

	public boolean isTechPower() {
		// TODO Auto-generated method stub
		return techPowerPos;
	}

	public Point getNewPos() {
		return newPos;
	}

	public void setNewPos(Point newPos) {
		this.newPos = newPos;
	}

	public JPNG getPlayerOnePanel() {
		return playerOnePanel;
	}

	public JPNG getPlayerTwoPanel() {
		return playerTwoPanel;
	}

	public boolean isMedicpower() {
		return medicpower;
	}

	public void setMedicpower(boolean medicpower) {
		this.medicpower = medicpower;
	}

	public JLabel getCurrentplayer() {
		return currentplayer;
	}

	public void setCurrentplayer(JLabel currentplayer) {
		this.currentplayer = currentplayer;
	}

}
