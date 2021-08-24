

import javax.swing.*;

public class Main {
    public static void main(String args[]){
        JFrame f=new JFrame();
        MatchField match=new MatchField();
        f.add(match);
        f.setBounds(10,10,1500,800);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
