import javax.swing.*;
import java.awt.*;

import java.awt.Font;
import java.awt.event.*;
public class caculator implements ActionListener{
    JFrame frame;
    JTextField txtfield;
    JButton[] numberButton= new JButton[10];
    JButton[] functionButton= new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equaButton, delButton, clrButton,negButton;
    JPanel panel;

    Font myfont= new Font("Aria", Font.BOLD,30);
    double num1=0,num2=0,result=0;
    char operatior;
    caculator(){
        frame= new JFrame("Calculator");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(550,550);
        frame.setLayout(null);
        txtfield= new JTextField();
        txtfield.setBounds(50,20,420,70);
        txtfield.setFont(myfont);
        txtfield.setEditable(false);
        addButton= new JButton("+");
        subButton= new JButton("-");
        mulButton= new JButton("*");
        divButton= new JButton("/");
        decButton= new JButton(".");
        equaButton= new JButton("=");
        delButton= new JButton("Delete");
        clrButton= new JButton("Clear");
        negButton= new JButton("(-)");
        functionButton[0]= addButton;
        functionButton[1]= subButton;
        functionButton[2]= mulButton;
        functionButton[3]= divButton;
        functionButton[4]= decButton;
        functionButton[5]= equaButton;
        functionButton[6]= delButton;
        functionButton[7]= clrButton;
        functionButton[8]=negButton;

        for(int i=0;i<9;i++){
            functionButton[i].addActionListener(this);
            functionButton[i].setFont(myfont);
            functionButton[i].setFocusable(false);
        }
        for(int i=0;i<10;i++){
            numberButton[i]=new JButton(String.valueOf(i));
            numberButton[i].addActionListener(this);
            numberButton[i].setFont(myfont);
            numberButton[i].setFocusable(false);
        }
        negButton.setBounds(50,430,100,50);
        delButton.setBounds(165,430,145,50);
        clrButton.setBounds(325,430,145,50);
        panel= new JPanel();
        panel.setBounds(50,100,420, 300);
        panel.setLayout(new GridLayout(4,4,20,10));
        panel.add(numberButton[1]);
        panel.add(numberButton[2]);
        panel.add(numberButton[3]);
        panel.add(addButton);
        panel.add(numberButton[4]);
        panel.add(numberButton[5]);
        panel.add(numberButton[6]);
        panel.add(subButton);
        panel.add(numberButton[7]);
        panel.add(numberButton[8]);
        panel.add(numberButton[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButton[0]);
        panel.add(equaButton);
        panel.add(divButton);

        panel.setBackground(Color.BLUE);
        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(txtfield);
        frame.setVisible(true);
    }
    public static void main(String[] args){
            caculator calc= new caculator();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        for(int i=0;i<10;i++){
  
            if(e.getSource()== numberButton[i]){
                txtfield.setText(txtfield.getText().concat(String.valueOf(i)));
             }
        
        }if(e.getSource()==decButton){
            txtfield.setText(txtfield.getText().concat("."));
        }if(e.getSource()==addButton){
            num1=Double.parseDouble(txtfield.getText());
            operatior='+';
            txtfield.setText("");
        }if(e.getSource()==subButton){
            num1=Double.parseDouble(txtfield.getText());
            operatior='-';
            txtfield.setText("");
        }if(e.getSource()==mulButton){
            num1=Double.parseDouble(txtfield.getText());
            operatior='*';
            txtfield.setText("");
        }if(e.getSource()==divButton){
            num1=Double.parseDouble(txtfield.getText());
            operatior='/';
            txtfield.setText("");
        }
        if(e.getSource()==equaButton){
            num2=Double.parseDouble(txtfield.getText());
            switch(operatior){
                case'+':
                result=num1+num2;
                break;
                case '-':
                result=num1-num2;
                break;
                case '*':
                result= num1*num2;
                break;
                case '/':
                result=num1/num2;
            break;
            }
            txtfield.setText(String.valueOf(result));
            num1=result;
        }
        if(e.getSource()==clrButton){
            txtfield.setText("");
        }
        if(e.getSource()==delButton){
            String string=txtfield.getText();
            txtfield.setText("");
            for(int i=0;i<string.length()-1;i++){
                txtfield.setText(txtfield.getText()+string.charAt(i));
            }
        }
        if(e.getSource()==negButton){
            double temp= Double.parseDouble(txtfield.getText());
            temp*=-1;
            txtfield.setText(String.valueOf(temp));
        }
    }
}
