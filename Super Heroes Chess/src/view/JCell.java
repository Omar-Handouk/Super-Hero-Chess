package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.game.*;
import model.pieces.Piece;

public class JCell extends JButton {
	private Piece p;
	int i ;
	int j;
	
	public JCell() throws IOException {
		super();
	}
	public JCell(Piece p) throws IOException {
		super();
		this.p = p;

	}
	public Piece getPiece() {
		return p;
	}
	public void setPiece(Piece p) {
		this.p = p;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	
/*	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(image, 150, 150, null);
	}
*/	
}
