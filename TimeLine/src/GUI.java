import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

public class GUI extends JFrame implements ItemListener, ActionListener {
    Label labels=new Label("labels");
    public GUI(){
        super("Time Line");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width,screenSize.height);
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.add(new JLabel("Time Line"));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.PINK);
        rightPanel.add(new JLabel("Control Panel"));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation((int) (screenSize.width * 0.8));
        splitPane.setEnabled(false);
        add(splitPane);
    }

    public void itemStateChanged(ItemEvent e) {
        //TODO
    } // end method itemStateChanged

    // handle button clicks
    public void actionPerformed(ActionEvent e) {
        //TODO
    } // end method actionPerformed
}
