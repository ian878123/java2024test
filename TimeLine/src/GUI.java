import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class GUI extends JFrame implements ItemListener, ActionListener {
    Label labels=new Label("labels");
    public GUI(){
        super("Time Line");
        JPanel topPanel = new JPanel();

    }

    public void itemStateChanged(ItemEvent e) {
        //TODO
    } // end method itemStateChanged

    // handle button clicks
    public void actionPerformed(ActionEvent e) {
        //TODO
    } // end method actionPerformed
}
