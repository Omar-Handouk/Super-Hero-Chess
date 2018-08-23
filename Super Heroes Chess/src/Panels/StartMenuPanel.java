package Panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import frames.JGame;
import frames.StartMenu;
import model.game.Game;
import model.game.Player;

public class StartMenuPanel extends JPanel implements ActionListener {

    private JButton startGame;
    private JButton credits;
    private JButton close;
    private JPanel buttons;

    private JButton debugging;

    private StartMenu mainView;

    public StartMenuPanel(StartMenu mainView) {

        this.mainView = mainView;

        this.setLayout(null);
        this.setOpaque(false);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(400, 300));

        buttons = new JPanel(new GridLayout(5, 1));
        buttons.setOpaque(false);

        startGame = new JButton("New Game");
        credits = new JButton("Credits");
        close = new JButton("Exit Game");

        JLabel invisibleLabelOne = new JLabel();
        //invisibleLabelOne.setSize(300, 300);
        invisibleLabelOne.setOpaque(false);
        invisibleLabelOne.setVisible(false);

        JLabel invisibleLabelTwo = new JLabel();
        invisibleLabelTwo.setOpaque(false);
        invisibleLabelTwo.setVisible(false);

        buttons.add(startGame);
        buttons.add(invisibleLabelOne);
        buttons.add(credits);
        buttons.add(invisibleLabelTwo);
        buttons.add(close);

        startGame.addActionListener(this);
        credits.addActionListener(this);
        close.addActionListener(this);

        buttons.setBounds(150, 100, 100, 100);

        debugging = new JButton("Debugging Mode");
        debugging.setHorizontalAlignment(SwingConstants.CENTER);

        debugging.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Game game =
                        new Game(new Player("Player One"), new Player("Player Two"));

                try
                {
                    JGame gameFrame = new JGame(game);
                }
                catch (Exception error)
                {
                    error.printStackTrace();
                }

                mainView.setVisible(false);
                mainView.dispose();
            }
        });

        debugging.setBounds(5, 275, 150, 20);

        this.add(debugging);
        this.add(buttons);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        Image img = new ImageIcon("resources/city.gif").getImage();
        g2d.drawImage(img, 0, 0, this);

        try {

            g2d.drawImage((BufferedImage) ImageIO.read(new File("resources/Title.png")),
                    50, 50, this);

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.repaint();
        this.revalidate();

        /*g2d.setColor(Color.RED);
        g2d.fillRect(300 / 2 - 100, 300 / 2 - 100, 100, 100);*/
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startGame) { //This part must take the user inputs

            JTextField userOne = new JTextField();
            userOne.setHorizontalAlignment(SwingConstants.CENTER);
            userOne.setColumns(10);

            JTextField userTwo = new JTextField();
            userTwo.setHorizontalAlignment(SwingConstants.CENTER);
            userTwo.setColumns(10);

            Object[] message = {
                    "Player One's name:", userOne,
                    "Player Two's name:", userTwo
            };

            while (true) {
                int Option = JOptionPane.showConfirmDialog(null, message, "New Game", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (Option == JOptionPane.OK_OPTION) {

                    if (!userOne.getText().isEmpty() && !userTwo.getText().isEmpty() &&
                            checkNames(userOne.getText(), userTwo.getText()) &&
                            checkNamesLength(userOne.getText(), userTwo.getText())) {

                        Game game = new Game(new Player(userOne.getText()), new Player(userTwo.getText()));

                        try {
                            JGame gameFrame = new JGame(game);
                        } catch (Exception error) {
                            error.printStackTrace();
                        }

                        System.err.println(userOne.getText() + " " + userTwo.getText());

                        mainView.setVisible(false);
                        mainView.dispose();
                        break;
                    } else if (userOne.getText().isEmpty() || userTwo.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please input both player one's and two's name", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (!checkNames(userOne.getText(), userTwo.getText())) {
                        JOptionPane.showMessageDialog(null, "A player's name can not be a space, Please re-enter a correct name", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "A player's name must be between 5 to 15 character", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    break;
                }
            }
        } else if (e.getSource() == credits) {

            JOptionPane.showMessageDialog(null,
                    "          Programmers: Mahmood Salah - Omar Handouk - Ahmad El-Zamarany\n"
                            + "Character and User Interface Design: Sarah Amr - Sondos Hosssam - Mostafa Azazy", "Credits", JOptionPane.INFORMATION_MESSAGE);


        } else {
            System.exit(0);
        }
    }

    private boolean checkNames(String userOne, String userTwo) {
        boolean one = false;
        for (int i = 0; i < userOne.length(); ++i)
            if (userOne.charAt(i) != ' ')
                one = true;

        boolean two = false;
        for (int i = 0; i < userTwo.length(); ++i)
            if (userTwo.charAt(i) != ' ')
                two = true;

        return (one && two);
    }

    private boolean checkNamesLength(String userOne, String userTwo) {
        return (5 <= userOne.length() && userOne.length() <= 15 && 5 <= userTwo.length() && userTwo.length() <= 15);
    }

}

