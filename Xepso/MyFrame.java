package Xepso;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class MyFrame extends JFrame {
    static final int N = 4;
    static final int M = 4;
    private JLabel lblCount;
    private JButton btnStart;
    private JPanel jPanel;
    private int currentMove=0;
    private JButton[][] matrix= new JButton[N][M];
    private boolean isPlaying=false;
    private int currentRow;
    private int currentColumn;

    public MyFrame(){
        super();
        iniUI();
    }
    private void iniUI(){
        this.setTitle("Game Xep So");
        //khoi tao panel
        JPanel con= new JPanel();
        con.setBackground(Color.GREEN);
        //con.setPreferredSize(new Dimension(400,600));
        
        //Thiet lap layout
        BoxLayout box=new BoxLayout(con,BoxLayout.Y_AXIS);
        con.setLayout(box);

        this.lblCount= new JLabel("0");
        con.add(this.lblCount);

        jPanel= new JPanel();
        jPanel.setLayout(new GridLayout(N,M));
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                JButton btn= new JButton(" ");
                btn.setPreferredSize(new Dimension(150,150));
                btn.addKeyListener(new KeyListener(){

                    @Override
                    public void keyTyped(KeyEvent e) {
                        // TODO Auto-generated method stub
                        if(!isPlaying){
                            return;
                        }
                        switch(e.getKeyChar()){
                            case 'w':
                            case'W':
                            moveUp();
                            break;
                            case 's':
                            case 'S':
                            moveDown();
                            break;
                            case 'a':
                            case 'A':
                           moveLeft();
                            break;
                            case 'd':
                            case 'D':
                            moveRight();
                            break;

                        }
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        // TODO Auto-generated method stub
                       
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        // TODO Auto-generated method stub
                        
                    }
                    
                });
                btn.setFont(new Font("Times New Roman",Font.BOLD,20));
                    matrix[i][j]=btn;
                    jPanel.add(btn);
            }
        
        }
        randomMatrix();

        //jPanel.setPreferredSize(new Dimension(200,200));
       // jPanel.setBackground(Color.RED);
        con.add(jPanel);

        this.btnStart= new JButton("Start");
        //this.btnStart.addKeyListener(new KeyListener()));
        this.btnStart.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(isPlaying){
                    reset();
                }else{
                    isPlaying=true;
                    btnStart.setText(("STOP"));
                }
                
            }
            
        });
        con.add(this.btnStart);
        
        
        
        this.setContentPane(con);
        this.pack();

        
        //can giua
        Dimension di= Toolkit.getDefaultToolkit().getScreenSize();
        int x=((int)di.getWidth()/2-(this.getWidth()/2));
        int y=((int)di.getHeight()/2-(this.getHeight()/2));
        this.setLocation(x, y);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    
    }
    private void reset(){
        btnStart.setText("Start");
        isPlaying= false;
        currentMove=0;
        lblCount.setText(currentMove+"");
        randomMatrix();
    }
    private void randomMatrix(){
        ArrayList<Integer> list= new ArrayList<>();
        int ketqua=N*M;
        for(int i=0;i<ketqua;i++){
            list.add(i);
        }
        Random ran= new Random();
        //do du lieu
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                //JButton btn=new JButton(" ");
                JButton btn=this.matrix[i][j];
                int pos=ran.nextInt(list.size());
                int value=list.get(pos);
                btn.setText(value+" ");
                list.remove(pos);

                if(value==0){
                    currentRow=i;
                    currentColumn=j;
                    btn.setForeground(Color.RED);
                }else{
                    btn.setForeground(Color.BLACK);
                }
            }
        }
    }
    private void moveUp(){
        if(currentRow==0){
            return;
        }
        swapButton(currentRow,currentColumn,--currentRow,currentColumn);
    }
    private void moveDown(){
        if(currentRow>=N-1){
            return;
        }
        swapButton(currentRow,currentColumn,++currentRow, currentColumn);
    }
    private void moveLeft(){
        if(currentColumn==0){
            return;
        }
        swapButton(currentRow,currentColumn,currentRow,--currentColumn);
    }
    private void moveRight(){
        if(currentColumn>=M-1){
            return;
        }
        swapButton(currentRow,currentColumn,currentRow, ++currentColumn);
    }
    
    private void swapButton( int r, int Col,int toR, int toCol){
        JButton fromButton=this.matrix[r][Col];
        fromButton.setForeground(Color.black);
        JButton toBut=this.matrix[toR][toCol];
        toBut.setForeground(Color.red);

        String tmp=toBut.getText();
        toBut.setText(fromButton.getText());
        fromButton.setText(tmp);

        currentMove++;
        lblCount.setText((currentMove+ " "));
        if(this.checkWin()){
            JOptionPane.showMessageDialog(this,"You Win:"+ currentMove);
            reset();
        }

    }
    private boolean checkWin(){
        ArrayList<Integer> list = new ArrayList<>();
        int result = N*M;

        for(int i = 1; i < result; i++){
            list.add(i);
        }
        list.add(0);

        int k = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                JButton btn = matrix[i][j];
                String text = btn.getText();

                if(!String.valueOf(list.get(k)).equals(text)){
                    return false;
                }

                k++;
            }
        }

        return true;
    }
}
