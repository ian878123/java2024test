import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class describeGUI extends JFrame {
    private Ob nowOb;
    private Event nowEvent;
    private final JTextField describe;
    private int ObOrEvent;
    private int OB=0;
    private int EVENT=1;
    describeGUI(Ob ob){
        super(ob.getName());
        ObOrEvent=OB;
        nowOb=ob;
        setSize(310, 460);
        setUndecorated(true);
        setUndecorated(true);
        setLayout(new FlowLayout());

        getContentPane().setBackground(Color.PINK);
        JLabel jLabel=new JLabel("敘述:");
        jLabel.setFont(new Font("標楷體",Font.PLAIN,30));
        add(jLabel);

        describe=new JTextField(ob.getDescribe());
        describe.setPreferredSize(new Dimension(250,300));
        add(describe);

        JButton jButton=new JButton("確認");
        jButton.addActionListener(new buttonListener());
        add(jButton);
    }
    describeGUI(Event event){
        super(event.getName());
        ObOrEvent=EVENT;
        nowEvent=event;
        setSize(310, 460);
        setUndecorated(true);
        setUndecorated(true);
        setLayout(new FlowLayout());

        getContentPane().setBackground(Color.PINK);
        JLabel jLabel=new JLabel("敘述:");
        jLabel.setFont(new Font("標楷體",Font.PLAIN,30));
        add(jLabel);

        describe=new JTextField(event.getDescribe());
        describe.setPreferredSize(new Dimension(250,300));
        add(describe);

        JButton jButton=new JButton("確認");
        jButton.addActionListener(new buttonListener());
        add(jButton);
    }
    private class buttonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            if(ObOrEvent==OB){
                nowOb.setDescribe(describe.getText());
                dispose();
            }
            else if(ObOrEvent==EVENT){
                nowEvent.setDescribe(describe.getText());
                dispose();
            }
        }
    }
}
