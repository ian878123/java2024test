import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Search extends JFrame implements ActionListener {
    JComboBox<String> tagList;
    ArrayList<Label> tags;
    JPanel obPanel;
    JButton confirmButton;

    public Search(ArrayList<Label> tags, ArrayList<Ob> obs){
        this.tags = tags;
        setTitle("Search");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] tagNames = new String[tags.size()];
        for(int i = 0; i < tags.size(); i++){
            tagNames[i] = tags.get(i).getName();
        }
        tagList = new JComboBox<>(tagNames);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3, 0, 0));
        JLabel label = new JLabel("Select a Tag");
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);
        panel.add(label);
        panel.add(tagList);
        panel.add(confirmButton);

        obPanel = new JPanel();
        obPanel.setLayout(new FlowLayout());

        add(panel, BorderLayout.NORTH);
        add(obPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == confirmButton){
            obPanel.removeAll(); // 先清空 obPanel 內容
            ArrayList<Ob> obs = new ArrayList<>();
            for(Label tag : tags){
                if(tag.getName().equals(tagList.getSelectedItem())){
                    obs = tag.getMembers();
                    break;
                }
            }
            for(Ob ob : obs){
                JButton button = new JButton(ob.getName());
                button.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        new UpdateObGUI(tags, ob);
                    }
                });
                obPanel.add(button);
            }
            obPanel.revalidate(); // 重新佈局 obPanel
            obPanel.repaint();    // 重新繪製 obPanel
        }
    }
}
