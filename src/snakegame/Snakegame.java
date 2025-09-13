package snakegame;
import javax.swing.*;

public class Snakegame extends JFrame {
    Snakegame() {
        setTitle("Snakegame");
        add(new Board());
        pack();
       
        setLocationRelativeTo(null);
        setResizable(false);
        
      
        setVisible(true);
        
    }
    public static void main(String[] args) {
        new Snakegame();
    }
}
