import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;

public class GUI extends JFrame implements ItemListener, ActionListener {
    Label labels=new Label("labels");
    public GUI(){
        super("Time Line");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width,screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void itemStateChanged(ItemEvent e) {
        //TODO
    } // end method itemStateChanged

    // handle button clicks
    public void actionPerformed(ActionEvent e) {
        //TODO
    } // end method actionPerformed
}
