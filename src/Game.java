
/**
 * Description	:Engine for the brick game
 * Copyright	:Copyright (c) 2014
 * Company		:Embla Software Innovations (Pvt) Ltd
 * Created on	:2014.09.01
 *
 * @author :Chandimal
 * @version :1.0
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Game extends Canvas implements KeyListener {

    private static final long serialVersionUID = 1L;
    
    
    BufferedImage buffer; // Create the buffer

    BufferedImage bgImg; // Create the buffer
    BufferedImage batImg;
    BufferedImage ballImg;
    BufferedImage brickImg;

    Ball ball;
    Bat bat;
    List<Brick> bricks;

    boolean isKeyLeft;
    boolean isKeyRight;
    boolean isKeySpace = false;
    boolean gameFinished;
    int score;
    
    Dimension dim;

    /**
     * Create the game using the width and the height specified
     */
    public Game(Dimension dim) {
        this.dim = dim;
        reset(this.dim);
    }

    public void reset(Dimension dim){
        buffer = new BufferedImage(dim.width, dim.height,
                BufferedImage.TYPE_INT_RGB);
        int ballStartX = 0;
        int ballStartY = 100;
        ball = new Ball(dim.width, dim.height, 10, ballStartX, ballStartY, 5);
        bat = new Bat(dim.width, dim.height, 100, 10,
                dim.width / 2, dim.height - 40, 5);

        bricks = new ArrayList<Brick>();
        for (int i = 0; i < 50; i++) {
            Brick brick = new Brick(dim.width, dim.height, 38, 19,
                    (i % 10) * 40, (i / 10) * 20);
            bricks.add(brick);
        }

        try {
            bgImg = ImageIO.read(new File("./img/bg.png"));
            batImg = ImageIO.read(new File("./img/bat.png"));
            ballImg = ImageIO.read(new File("./img/ball.png"));
            brickImg = ImageIO.read(new File("./img/brick.png"));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.setIgnoreRepaint(true); // Ignore repainting as we are doing all
        // the drawing stuff
        isKeyLeft = false;
        isKeyRight = false;
    }
    /**
     * Start the game
     */
    public void Start() {
        //JOptionPane.showMessageDialog(this, "Start the Game");
        while (!gameFinished) {

            // Check keys
            if (isKeyLeft) {
                bat.setLeft();
                if (!isKeySpace){
                    //ball.setLeft();
                }
            }
            if (isKeyRight) {
                bat.setRight();
                if (!isKeySpace){
                    //ball.setRight();
                }
            }
            // while(isKeySpace = false){
            // JOptionPane.showOptionDialog(null, "Hello","Empty?", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
            // JOptionPane.showOptionDialog(this, "You Won");
            if (isKeySpace) {
                ball.update();
            }//}

            bat.update();
//
//            JLabel jlabel = new JLabel("Score : " + score);
//            jlabel.setFont(new Font("Verdana", 10, 10));
//            jlabel.setSize(25, 25);
//            jlabel.setLocation(10, 580);

            // Collision detection
            detectCollision();

            if (bricks.size() == 0) {
                gameFinished = true;
                JOptionPane.showMessageDialog(this, "You Won" + "\nYou want to Restart");
                gameFinished = false;
                reset(dim);
            }
            if (ball.getY() > bat.getY() + 10) {
                gameFinished = true;
                
                JOptionPane.showMessageDialog(this, "Game Over \n score:" + score + "\nYou want to Restart?");
                gameFinished = false;
                
                reset(dim);
                
                
            }
            // Draw the buffer
            drawBuffer();
            // Paint the buffer on screen
            drawScreen();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Detect collision
     */
    public void detectCollision() {

        Rectangle rectBall = new Rectangle(ball.getX(), ball.getY(),
                ball.getHeight(), ball.getWidth());
        Rectangle rectBat = new Rectangle(bat.getX(), bat.getY(),
                bat.getWidth(), bat.getHeight());

        if (rectBat.intersects(rectBall)) {
            if (isKeyLeft) {
                ball.leftsidemove();
            }
             if (isKeyRight) {
                ball.rightsidemove();
            }
            ball.reverse();
        }
        for (int i = 0; i < bricks.size(); i++) {
            Brick brick = bricks.get(i);
            Rectangle brickRect = new Rectangle(brick.getX(), brick.getY(),
                    brick.getWidth(), brick.getHeight());
            if (rectBall.intersects(brickRect)) {
                ball.reverse();
                bricks.remove(brick);
                score += 100;
            }
        }

    }

    /**
     * Draw the image buffer
     */
    public void drawBuffer() {
        Graphics2D b = buffer.createGraphics();

        // Background	
        //b.setColor(Color.BLACK);	
        //b.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
        b.drawImage(bgImg, 0, 0, this);

        // Draw ball	
        //b.setColor(Color.WHITE);		
        //b.fillOval( ball.getX() , ball.getY(), ball.getWidth(), ball.getHeight());
        b.drawImage(ballImg, ball.getX(), ball.getY(),
                ball.getWidth(), ball.getHeight(), this);

        // Draw bat
        //b.setColor(Color.white);
        //b.fillRoundRect( bat.getX() , bat.getY(), bat.getWidth(), bat.getHeight(),  3, 3 );
        b.drawImage(batImg, bat.getX(), bat.getY(),
                bat.getWidth(), bat.getHeight(), this);

        // Draw bricks
        for (Brick brick : bricks) {
            b.drawImage(brickImg, brick.getX(), brick.getY(),
                    brick.getWidth(), brick.getHeight(), this);
        }
    }

    /**
     * Update it to the screen
     */
    public void drawScreen() {
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.drawImage(buffer, 0, 0, this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        // TODO Auto-generated method stub
//                if ( evt.getKeyCode() == 37 ){
//			isKeyLeft = true;
//		}
        if (evt.getKeyCode() == 37) {
            isKeyLeft = true;
        }

        if (evt.getKeyCode() == 39) {
            isKeyRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent evt) {
        // TODO Auto-generated method stub
        if (evt.getKeyCode() == 37) {
            isKeyLeft = false;
        }
//		if ( evt.getKeyCode() == 37 ){
//			isKeyLeft = false;
//		}		
        if (evt.getKeyCode() == 39) {
            isKeyRight = false;
        }

    }

    @Override
    public void keyTyped(KeyEvent ev) {
        if (Character.isSpaceChar(ev.getKeyChar())) {

            isKeySpace = true;
        }

    }

}
