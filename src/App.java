import java.awt.BorderLayout;

import javax.swing.*;


public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 600;
        int boardHeight = boardWidth;

        JFrame frame = new JFrame("Snake Game");
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight + 50);
        frame.setLocationRelativeTo(null);;
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        SnakeGame snakeGame = new SnakeGame(boardWidth, boardHeight);
        GameControlPanel controlPanel = new GameControlPanel(snakeGame);

        frame.setLayout(new BorderLayout());
        frame.add(snakeGame, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.pack();
        snakeGame.requestFocus();

    }
}
