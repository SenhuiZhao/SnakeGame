import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;



public class SnakeGame extends JPanel implements ActionListener, KeyListener{
    int boardWidth;
    int boardHeight;
    int tileSize = 25;

    private class Tile {
        int x;
        int y;

        Tile (int x, int y){
            this.x = x;
            this.y = y;        
        }
    }

    Tile snakeHead; 
    ArrayList<Tile> snakeBody;
    Tile food;
    Random random;


    Timer gameTimer;

    int velocityX;
    int velocityY;

    boolean gameover = false;
    boolean gamestart = false;


    SnakeGame(int boardWidth, int boardHeight){

        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;


        setPreferredSize(new Dimension(this.boardHeight, this.boardHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        initializeGame();

        // snakeHead = new Tile(10,10);
        // snakeBody = new ArrayList<Tile>();

        // food = new Tile(15,15);
        // random = new Random();
        // placeFood();

        // velocityX = 0;
        // velocityY = 0;

        // gameTimer = new Timer(100, this);
        // gameTimer.start();

    }

    public void initializeGame() {
        snakeHead = new Tile(8, 8);
        snakeBody = new ArrayList<>();
        food = new Tile(15, 8);
        random = new Random();
        // placeFood();

        System.out.println(gamestart);
        if (gamestart) {
            placeFood();
            velocityX = 1;
            velocityY = 0;
        }else{            
            velocityX = 0;
            velocityY = 0;
        }

        gameover = false;

        gameTimer = new Timer(100, this);
        gameTimer.start();
        requestFocusInWindow();
    }

    public void startGame() {
        gamestart = true;
        initializeGame();
        // if (gameTimer.isRunning()) {
        //     System.out.println("1");
        //     return;
        // }
    }

    public void restartGame() {
        if (gameTimer.isRunning()) {
            gameTimer.stop();
        }
        initializeGame();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);    
    }

    public void draw(Graphics g){
        // for (int i = 0; i<boardWidth/tileSize; i++){
        //     g.drawLine(i*tileSize, 0, i*tileSize, boardHeight);
        //     g.drawLine(0, i*tileSize, boardWidth, i*tileSize);
        // }

        g.setColor(Color.red);
        // g.fillRect(food.x*tileSize, food.y*tileSize, tileSize, tileSize);
        g.fill3DRect(food.x*tileSize, food.y*tileSize, tileSize, tileSize,true);
        

        g.setColor(Color.green);
        // g.fillRect(snakeHead.x*tileSize, snakeHead.y*tileSize, tileSize, tileSize);
        g.fill3DRect(snakeHead.x*tileSize, snakeHead.y*tileSize, tileSize, tileSize,true);


        for (int i =0; i <snakeBody.size(); i++){
            Tile snakeBodyPart = snakeBody.get(i);
            // g.fillRect(snakeBodyPart.x*tileSize, snakeBodyPart.y*tileSize, tileSize, tileSize);
            g.fill3DRect(snakeBodyPart.x*tileSize, snakeBodyPart.y*tileSize, tileSize, tileSize, true);
        }


        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if (gameover) {
            g.setColor(Color.red);
            g.drawString("Game Over: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
        }
        else {
            g.drawString("Score: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
        }
    }

    public void placeFood(){
        food.x = random.nextInt(boardWidth/tileSize);
        food.y = random.nextInt(boardHeight/tileSize);
    }

    public boolean collison(Tile tile1, Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move() {

        if (collison(snakeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();   
        }

        for (int i = snakeBody.size()-1; i>=0; i--){
            Tile snakeBodayPart = snakeBody.get(i);

            if (i ==0){
                snakeBodayPart.x = snakeHead.x;
                snakeBodayPart.y = snakeHead.y;
            }else{
                Tile preSnakeBodyPart = snakeBody.get(i-1);
                snakeBodayPart.x = preSnakeBodyPart.x;
                snakeBodayPart.y = preSnakeBodyPart.y;
            }

        }

        snakeHead.x += velocityX;
        snakeHead.y += velocityY;


        //gameover

        for (int i = 0; i < snakeBody.size()-1; i++){
            Tile snakeBodyPart = snakeBody.get(i);
            if (collison(snakeBodyPart, snakeHead)) {
                gameover = true;
            }
        }

        if (snakeHead.x*tileSize < 0 || snakeHead.x*tileSize > boardWidth || snakeHead.y*tileSize < 0 || snakeHead.y*tileSize > boardHeight) {
            gameover = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        if (!gameover) {
            move();
        }
        repaint();
        if (gameover) {
            gameTimer.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");

        if (gamestart) {

        if (e.getKeyCode()== KeyEvent.VK_UP && velocityY !=1){
            velocityX = 0;
            velocityY = -1;
        }
        
        else if (e.getKeyCode()== KeyEvent.VK_DOWN && velocityY!=-1){
            velocityX = 0;
            velocityY = 1;
        }

        else if (e.getKeyCode()== KeyEvent.VK_LEFT && velocityX != 1){
            velocityX = -1;
            velocityY = 0;
        }
        
        else if (e.getKeyCode()== KeyEvent.VK_RIGHT && velocityX != -1){
            velocityX = 1;
            velocityY = 0;
        }

    }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
    
}
