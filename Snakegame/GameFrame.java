

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameFrame extends JFrame{
    GameFrame(){
        GamePanel panel= new GamePanel();

        this.add(panel);
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
}
