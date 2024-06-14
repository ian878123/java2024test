import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UpdateObGUI extends JFrame {
    UpdateObGUI(ArrayList<Label> tags, Ob ob){
        super("Update Object");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel tagpanel = new JPanel();
        tagpanel.setLayout(new GridLayout(3, 1, 0, 0));
        JLabel taglabel = new JLabel("Choose a tag to insert:");
        JLabel oblabel = new JLabel("Description:");
        JComboBox<String> tagList = new JComboBox<>();
        for(Label tag : tags){
            tagList.addItem(tag.getName());
        }
        tagpanel.add(taglabel);
        tagpanel.add(tagList);
        tagpanel.add(oblabel);
        add(tagpanel, BorderLayout.NORTH);

        JTextField obDes = new JTextField();
        obDes.setText(ob.getDescribe());
        add(obDes, BorderLayout.CENTER);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            String selectedTag = (String) tagList.getSelectedItem();
            for(Label tag : tags){
                if(tag.getName().equals(selectedTag)) {
                    if (ob instanceof People) {
                        ((People) ob).addTag(tag);
                        tag.newMember(ob);
                    } else if (ob instanceof Res) {
                        ((Res) ob).addTag(tag);
                        tag.newMember(ob);
                    }
                }
            }
            ob.setDescribe(obDes.getText());
            dispose();
        });
        confirmButton.setPreferredSize(new Dimension(300, 50));
        add(confirmButton, BorderLayout.SOUTH);
        setVisible(true);
    }
}
