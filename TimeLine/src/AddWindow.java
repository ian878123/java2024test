import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddWindow extends JFrame{

    private final Color[] colors={Color.BLUE,Color.CYAN,Color.GRAY,Color.GREEN,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.YELLOW,Color.WHITE,Color.BLACK};
    private final String[] colorNames = { "Blue", "Cyan", "Gray", "Green", "Magenta", "Orange", "Pink", "Red", "Yellow", "White", "Black" };
    private Event newevent;
    private JButton eventButton;
    private JTextField eventName;
    private JTextField eventText;
    private JComboBox<String> eventColorChoices;



    AddWindow(Event event){
        super("Add Event");
        newevent=event;
        setLayout(new GridLayout(4,2,10,10));

        eventButton=new JButton("Confirm");
        eventButton.addActionListener(new buttonListener());
        eventName =new JTextField();
        eventText =new JTextField();
        eventColorChoices=new JComboBox<>(colorNames);
        JLabel eventNameLabel=new JLabel("Event Name:");
        JLabel eventTextLabel=new JLabel("Event Text:");
        JLabel eventColorLabel=new JLabel("Event Color:");

        add(eventNameLabel);
        add(eventName);
        add(eventTextLabel);
        add(eventText);
        add(eventColorLabel);
        add(eventColorChoices);
        add(eventButton);
        setVisible(true);
    }

    private class buttonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("buttonListener");
            if(e.getSource()==eventButton){
                newevent.setName(eventName.getText());
                newevent.setDescribe(eventText.getText());
                newevent.setDrawingColor(colors[eventColorChoices.getSelectedIndex()]);
                dispose();
            }
        }
    }
}
