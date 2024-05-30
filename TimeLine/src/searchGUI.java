import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class searchGUI  extends JFrame {
    public searchGUI(Label tag){
        super(tag.getName());
        ArrayList<Ob> obs=tag.getMembers();
        setLayout(new GridLayout(2, 1));
        JPanel peoplePanel=new JPanel(new FlowLayout());
        JPanel resPanel=new JPanel(new FlowLayout());
        JButton tmp;

        JLabel peopleLabel=new JLabel("People:");
        peoplePanel.add(peopleLabel);
        for (Ob ob : obs) {
            if (ob instanceof People) {
                tmp = new JButton(ob.getName());
                peoplePanel.add(tmp);
            }
        }

        JLabel resLabel=new JLabel("Res:");
        resPanel.add(resLabel);
        for (Ob ob : obs) {
            if (ob instanceof Res) {
                tmp = new JButton(ob.getName());
                resPanel.add(tmp);
            }
        }

        add(peoplePanel);
        add(resPanel);
    }
}
