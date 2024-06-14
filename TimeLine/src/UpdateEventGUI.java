import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateEventGUI extends JFrame {
    private final Color[] colors={Color.CYAN,Color.BLUE,Color.GRAY,Color.GREEN,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.YELLOW,Color.WHITE};
    private final String[] colorNames = { "Cyan", "Blue", "Gray", "Green", "Magenta", "Orange", "Pink", "Red", "Yellow", "White"};
    public UpdateEventGUI(Event event,ArrayList<Ob> obs,ArrayList<Label> tags,JPanel leftPanel){
        super("Update Event");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLayout(new GridLayout(7,1,10,10));

        String Sname = event.getName();

        JPanel namePanel = new JPanel(new BorderLayout(10,0));
        JLabel nameLabel = new JLabel("Name:");
        namePanel.add(nameLabel,BorderLayout.WEST);
        JPanel describePanel = new JPanel(new BorderLayout(10,0));
        JLabel describeLabel = new JLabel("Describe:");
        describePanel.add(describeLabel,BorderLayout.WEST);
        JTextField name = new JTextField(event.getName());
        JTextField describe = new JTextField(event.getDescribe());
        namePanel.add(name,BorderLayout.CENTER);
        describePanel.add(describe,BorderLayout.CENTER);
        add(namePanel);
        add(describePanel);

        JPanel colorPanel = new JPanel(new GridLayout(1,2,10,10));
        JLabel colorLabel = new JLabel("Color:");
        JComboBox<String> colorList = new JComboBox<>();
        for(String color : colorNames){
            colorList.addItem(color);
        }
        colorPanel.add(colorLabel);
        colorPanel.add(colorList);
        add(colorPanel);


        JPanel peoplePanel=new JPanel(new FlowLayout());
        JPanel resPanel=new JPanel(new FlowLayout());
        JButton tmp;

        ArrayList<People> peoples=event.getPeoples();
        ArrayList<Res> res=event.getRes();

        JLabel peopleLabel=new JLabel("People:");
        peoplePanel.add(peopleLabel);
        for (Ob ob : peoples) {
            if (ob instanceof People) {
                tmp = new SearchButton(ob);
                tmp.addActionListener(e -> {
                    new UpdateObGUI(tags, ob);
                });
                peoplePanel.add(tmp);
            }
        }

        JLabel resLabel=new JLabel("Res:");
        resPanel.add(resLabel);
        for (Ob ob : res) {
            if (ob instanceof Res) {
                tmp = new SearchButton(ob);
                tmp.addActionListener(e -> {
                    new UpdateObGUI(tags, ob);
                });
                resPanel.add(tmp);
            }
        }

        add(peoplePanel);
        add(resPanel);

        JPanel insertPanel = new JPanel(new GridLayout(1,2,10,10));
        JComboBox<String> insertList = new JComboBox<>();
        for(Ob ob : obs){
            insertList.addItem(ob.getName());
        }
        JLabel insertLabel = new JLabel("Insert Object:");
        insertPanel.add(insertLabel);
        insertPanel.add(insertList);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            event.setName(name.getText());
            event.setDescribe(describe.getText());
            event.setDrawingColor(colors[colorList.getSelectedIndex()]);
            for(Ob ob : obs){
                if(ob.getName().equals(insertList.getSelectedItem())){
                    if(ob instanceof People){
                        event.addNewMember((People) ob);
                        ((People) ob).addEvent(event);
                    }
                    else if(ob instanceof Res){
                        event.addNewMember((Res) ob);
                        ((Res) ob).addEvent(event);
                    }
                }
            }
            Component[] components = leftPanel.getComponents();
            for (Component component : components) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    if (button.getText().equals(Sname)) {
                        button.setText(name.getText());
                        button.setBackground(event.getColor());
                        break;
                    }
                }
            }

            leftPanel.repaint();
            leftPanel.revalidate();
            dispose();
        });

        add(insertPanel);
        add(confirmButton);

    }
}
