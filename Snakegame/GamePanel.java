

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
public class GamePanel extends JPanel implements ActionListener{
    static final int SCREEN_width=600;
    static final int SCREEN_height=600;
    static final int UNIT_SIZE=25;
    static final int DELAY= 75;
    static final int GAME_UNITS=(SCREEN_width*SCREEN_height)/UNIT_SIZE;
    final int[] x= new int[GAME_UNITS];
    final int[] y= new int[GAME_UNITS];
    int bodyParts=6;
    int applesEaten;
    int applex;
    int appleY;
    char direction='R';
    boolean running=false;
    Timer timer;
    Random random;

    GamePanel(){
            random = new Random();
            this.setPreferredSize(new Dimension(SCREEN_width,SCREEN_height));
            this.setBackground(Color.BLACK);
            this.setFocusable(true);
            this.addKeyListener(new MykeyAdapter());
            startgame();
    }
    public void startgame(){
        newApple();
        running=true;
        timer = new Timer(DELAY, this);
        timer.start();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if(running){
    //caro
        for(int i=0;i<SCREEN_height/UNIT_SIZE;i++){
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_height);
                g.drawLine( 0, i*UNIT_SIZE, SCREEN_width,i*UNIT_SIZE);
        }
        g.setColor(Color.red);
        g.fillOval(applex, appleY,UNIT_SIZE, UNIT_SIZE);
        for(int i= 0;i<bodyParts;i++){
            if(i==0){
                g.setColor(Color.RED);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }else{
              g.setColor(new Color(80,180,0));
                //g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

            }
        }
        g.setColor(Color.red);
      g.setFont(new Font("INK FREE", Font.BOLD,40));
      FontMetrics metrics = getFontMetrics(g.getFont());
      g.drawString("Score:"+applesEaten,(SCREEN_width - metrics.stringWidth("Score:"+applesEaten))/2, g.getFont().getSize());
    }else{
        gameOver(g);
    }
    }
    public void newApple(){
        applex = random.nextInt((SCREEN_width/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((SCREEN_height/UNIT_SIZE))*UNIT_SIZE;
    }
    public void move(){
            for(int i=bodyParts;i>0;i--){
                x[i] = x[i-1];
                y[i] = y[i-1];
            }
            switch(direction){
                case 'U':
                y[0]=y[0]- UNIT_SIZE;
                break;
                case 'D':
                y[0]=y[0] +UNIT_SIZE;
                break;
                case 'L':
                x[0]=x[0]- UNIT_SIZE;
                break;
                case 'R':
                x[0]=x[0]+UNIT_SIZE;
                break;
                
            }
    }
    public void checkApple(){
      // TODO document why this method is empty
      if((x[0]==applex) &&(y[0]==appleY)){
          bodyParts++;
          applesEaten++;
          newApple();
    }
    }
    public void checkcollistion(){
      // TODO document why this method is empty
      //check if head collides wwith body
      for(int i= bodyParts;i>0;i--){
          if((x[0]==x[i])&&(y[0]==y[i])){
              running=false;
          }
      }//check if head touches lefft boreder
      if(x[0]<0){
          running=false;
      }
      //check if head touches right boreder
      if(x[0]>SCREEN_width){
          running=false;
      }
      //check if head touches top border
      if(y[0]<0){
        running=false;
    }//check iff head touches bottom borrder;
    if(y[0]>SCREEN_height){
        running=false;
    }
    if(!running){
        timer.stop();
    }

    }
    public void gameOver(Graphics g){
        //Score
        g.setColor(Color.red);
        g.setFont(new Font("INK FREE", Font.BOLD,40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score:"+applesEaten,(SCREEN_width - metrics1.stringWidth("Score:"+applesEaten))/2, g.getFont().getSize());
      // TODO document why this method is empty
      //game over txt
      g.setColor(Color.red);
      g.setFont(new Font("INK FREE", Font.BOLD,75));
      FontMetrics metrics2 = getFontMetrics(g.getFont());
      g.drawString("Game Over",(SCREEN_width - metrics2.stringWidth("Game Over"))/2, SCREEN_height/2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(running){
            move();
            checkApple();
            checkcollistion();
        }
        repaint();
        
    }
    public class MykeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
          // TODO document why this method is empty
          switch(e.getKeyCode()){
                case KeyEvent .VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
          }
        }
    }
    
}
