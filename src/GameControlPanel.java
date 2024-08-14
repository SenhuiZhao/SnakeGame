import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameControlPanel extends JPanel {
    private JButton startButton;
    private JButton restartButton;
    private SnakeGame snakeGame;

    public GameControlPanel(SnakeGame snakeGame) {
        this.snakeGame = snakeGame;

        startButton = new JButton("Start");
        restartButton = new JButton("Restart");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snakeGame.startGame();
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snakeGame.restartGame();
            }
        });

        add(startButton);
        add(restartButton);
    }
}
