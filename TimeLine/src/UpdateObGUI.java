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
        tagpanel.setLayout(new GridLayout(6, 1, 0, 0));
        JLabel taglabel = new JLabel("Choose a tag to insert:");
        JLabel oblabel = new JLabel("Description:");
        JLabel tagslabel = new JLabel("Tags:");
        JComboBox<String> tagList = new JComboBox<>();
        for(Label tag : tags){
            tagList.addItem(tag.getName());
        }

        JPanel tagPanel = new JPanel();
        tagPanel.setLayout(new FlowLayout());
        if(ob instanceof People){
            for(Label tag : ((People) ob).getTagsIn()){
                tagPanel.add(new JLabel(tag.getName()));
            }
        } else if(ob instanceof Res){
            for(Label tag : ((Res) ob).getTagsIn()){
                tagPanel.add(new JLabel(tag.getName()));
            }
        }

        tagpanel.add(taglabel);
        tagpanel.add(tagList);
        tagpanel.add(tagslabel);
        tagpanel.add(tagPanel);
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
