import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class searchGUI  extends JFrame {
    private People p;
    private Res r;
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
                tmp = new SearchButton(ob);
                tmp.addActionListener(new buttonListener());
                peoplePanel.add(tmp);
            }
        }

        JLabel resLabel=new JLabel("Res:");
        resPanel.add(resLabel);
        for (Ob ob : obs) {
            if (ob instanceof Res) {
                tmp = new SearchButton(ob);
                tmp.addActionListener(new buttonListener());
                resPanel.add(tmp);
            }
        }

        add(peoplePanel);
        add(resPanel);
    }
    private class buttonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            SearchButton clickedButton = (SearchButton) e.getSource();
            Ob clickedOb=clickedButton.getOb();
            describeGUI frame=new describeGUI(clickedOb);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(searchGUI.this);
            frame.setVisible(true);
        }
    }
}
class SearchButton extends JButton{
    private final Ob Myob;
    SearchButton(Ob ob){
        super(ob.getName());
        Myob=ob;
    }
    public Ob getOb(){
        return Myob;
    }
}