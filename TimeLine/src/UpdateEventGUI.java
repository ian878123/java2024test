import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateEventGUI extends JFrame {
    public UpdateEventGUI(Event event,ArrayList<Ob> obs,ArrayList<Label> tags){
        super("Update Event");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLayout(new GridLayout(6,1,10,10));

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
            dispose();
        });

        add(insertPanel);
        add(confirmButton);

    }
}
