

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class MatchField extends JPanel implements KeyListener,ActionListener{
    private JPanel p;
    private Timer timer;
    private Random r;
    private Thread t;
    private boolean play;
    private int ballX=750;
    private int ballY=400;
    private int playerX=300;
    private int playerY=300;
    private int scoreX=0;
    private int scoreY=0;
    private int delay=8;
    private int velocityX=-9;
    private int velocityY=-1;
    private boolean initial=true;

    public MatchField(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();
    }

    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(0,0,1500,800);

        //set border
        g.setColor(Color.RED);
        g.fillRect(0,0,1500,10);
        g.fillRect(0,750,1500,10);

        //playerX
        g.setColor(Color.blue);
        g.fillRect(50,playerX,10,100);
        g.fillRect(1430,playerY,10,100);

        //ballPosition
        g.setColor(Color.yellow);
        g.fillOval(ballX,ballY,40,40);

        //score board
        g.setColor(Color.RED);
        g.setFont(new Font("serif",Font.BOLD,30));
        g.drawString(scoreY+" : "+scoreX, 700, 200);

        if(initial){
        //Initial Instruction of the match
        g.setColor(Color.RED); 
        g.setFont(new Font("serif",Font.BOLD,30));
        g.drawString("Player 1",100,100);
        g.drawString("Press 'W' key to move Up",100,150);
        g.drawString("Press 'Z' key to move Down",100,200);
        g.drawString("Player 2",1000,100);
        g.drawString("Press 'Up' key to move Up",1000,150);
        g.drawString("Press 'Down' key to move Down",1000,200);
        g.setColor(Color.blue); 
        g.drawString("Player who score 10 goals wins the match",500,400);
        }
     


        if(ballX<-40){
            scoreY++;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD,25));

            timer.stop();
            g.drawString("GOAL!!!",100,300);
            try {

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timer.start();
            ReStart();
        }
        if(ballX>1500){
            scoreX++;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD,25));
            g.setColor(Color.red);

            timer.stop();
            g.drawString("GOAL!!!",1300,300);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timer.start();

            ReStart();
        }
        if(scoreX==10){
            play=false;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,35));
            g.drawString("You Won the Match",100,300);
        }
        if(scoreY==10){
            play=false;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,35));
            g.drawString("You Won the Match",1000,300);
        }

        g.dispose();

    }

    private void ReStart() {
        r=new Random();
        
        ballX=r.nextInt(400) + 400;
        ballY=r.nextInt(200) + 300;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        
        if(play){
            if(new Rectangle(ballX,ballY,40,40).intersects(new Rectangle(50,playerX,10,100))){
                velocityX=-velocityX;
            }
            if(new Rectangle(ballX,ballY,40,40).intersects(new Rectangle(1430,playerY,10,100))){
                velocityX=-velocityX;
            }
            if(ballY<0){
                velocityY=-velocityY;
            }
            if(ballY>700){
                velocityY=-velocityY;
            }

            ballX=ballX + velocityX;
            ballY=ballY + velocityY;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
         initial=false;
            if(e.getKeyCode()==KeyEvent.VK_UP){
                if(playerY<0){
                    playerY=0;
                }
                else{
                    moveUpY();
                }
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                if (playerY >= 650) {
                    playerY = 650;
                } else {
                    moveDownY();
                }
            }
            if(e.getKeyCode()==KeyEvent.VK_W){
                if(playerX<0){
                    playerX=0;
                }
                else{
                    moveUpX();
                }
            }
            if(e.getKeyCode()==KeyEvent.VK_Z){
                if(playerX>=650){
                    playerX=650;
                }
                else{
                    moveDownX();
                }
            }
    }

    private void moveDownY() {
        play=true;
        playerY=playerY+20;
    }

    private void moveUpY() {
        play=true;
        playerY=playerY-20;
    }
    private void moveDownX() {
        play=true;
        playerX=playerX+20;
    }

    private void moveUpX() {
        play=true;
        playerX=playerX-20;
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
