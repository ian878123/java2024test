import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class GUI extends JFrame {
    //base
    private ArrayList<Label> tags=new ArrayList<>();
    private Label noAffiliation=new Label("無隸屬");
    private JPanel labelPanel;
    private ArrayList<String> tagNames;
    private final String[] colorNames = { "Blue", "Cyan", "Gray", "Green", "Magenta", "Orange", "Pink", "Red", "Yellow", "White", "Black" };
    private final Color[] colors={Color.BLUE,Color.CYAN,Color.GRAY,Color.GREEN,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.YELLOW,Color.WHITE,Color.BLACK};
    private ArrayList<Event> events=new ArrayList<>();
    private ArrayList<Ob> Obs=new ArrayList<>();
    private int eventCount=0;
    //新事件
    private JTextField eventInput;
    private JComboBox<String> eventColorChoices;
    private JButton eventButton;
    //添加物件
    private JTextField objectInput;
    private JRadioButton peopleButton;
    private JRadioButton resButton;
    private JComboBox<String> objectTagChoices;
    private ArrayList<String> objectTagNames;
    private DefaultComboBoxModel<String> objectTagModel;
    private JButton objectButton;
    private int PorR=1;
    //標籤查詢
    private JComboBox<String> tagChoices;
    private ArrayList<String> eventNames;
    private DefaultComboBoxModel<String> eventModel;
    private JComboBox<String> objectChoices;
    private ArrayList<String> objectNames;
    private DefaultComboBoxModel<String> objectModel;
    //選取事件
    private JTextField choicesTextField;
    //新建標籤 與 讀黨存檔
    private JTextField tagTextField;
    private JButton readButton;
    private JButton newButton;
    private JButton saveButton;
    //GUI constructor
    public GUI(){
        //GUI建立
        super("Time Line");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width,screenSize.height);
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tags.add(noAffiliation);
        //左側GUI
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.add(new JLabel("Time Line"));
        //右側GUI
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(6,1));
        rightPanel.setBackground(Color.PINK);
        //右:新事件
        JPanel eventPanel=new JPanel(new GridLayout(5,2));
        eventPanel.add(Box.createVerticalStrut(0)); eventPanel.add(Box.createVerticalStrut(0));
        eventPanel.setBackground(Color.PINK);
        JLabel eventLabel=new JLabel("new event:");
        eventLabel.setFont(new Font("Arial",Font.PLAIN,25));
        eventLabel.setOpaque(true);
        eventLabel.setBackground(Color.PINK);
        eventPanel.add(eventLabel);
        eventInput=new JTextField("Enter name here",5);
        eventInput.addActionListener(new MyEventListener());
        eventPanel.add(eventInput);
        eventColorChoices=new JComboBox<String>(colorNames);
        eventColorChoices.addActionListener(new MyEventListener());
        eventPanel.add(eventColorChoices);
        eventButton=new JButton("Create");
        eventButton.addActionListener(new MyEventListener());
        eventPanel.add(eventButton);
        eventPanel.add(Box.createVerticalStrut(0));eventPanel.add(Box.createVerticalStrut(0));
        eventPanel.add(new CustomSeparator(Color.BLACK, 2));eventPanel.add(new CustomSeparator(Color.BLACK, 2));
        rightPanel.add(eventPanel);
        //右:添加物件
        JPanel objectPanel=new JPanel(new GridLayout(5,2));
        objectPanel.setBackground(Color.PINK);
        JLabel objectLabel=new JLabel("new object:");
        objectLabel.setFont(new Font("Arial",Font.PLAIN,25));
        objectLabel.setOpaque(true);
        objectLabel.setBackground(Color.PINK);
        objectPanel.add(objectLabel);
        objectInput=new JTextField("Enter name here",5);
        objectInput.addActionListener(new MyObjectListener());
        objectPanel.add(objectInput);
        ButtonGroup group=new ButtonGroup();
        peopleButton=new JRadioButton("People",true);
        resButton=new JRadioButton("Res",false);
        group.add(peopleButton);
        group.add(resButton);
        peopleButton.addActionListener(new MyObjectListener());
        peopleButton.setBackground(Color.PINK);
        resButton.addActionListener(new MyObjectListener());
        resButton.setBackground(Color.PINK);
        objectPanel.add(peopleButton);
        objectPanel.add(resButton);
        objectTagNames=new ArrayList<>();
        objectTagModel=new DefaultComboBoxModel<>(objectTagNames.toArray(new String[0]));
        objectTagChoices=new JComboBox<>(objectTagModel);
        objectTagModel.addElement("無隸屬");
        objectPanel.add(objectTagChoices);
        objectButton=new JButton("Create");
        objectButton.addActionListener(new MyObjectListener());
        objectPanel.add(objectButton);
        objectPanel.add(Box.createVerticalStrut(0)); objectPanel.add(Box.createVerticalStrut(0));
        objectPanel.add(new CustomSeparator(Color.BLACK, 3));objectPanel.add(new CustomSeparator(Color.BLACK, 3));
        rightPanel.add(objectPanel);
        //右:標籤查詢 與 插入物件
        labelPanel=new JPanel(new GridLayout(4,2));
        labelPanel.setBackground(Color.PINK);

        eventNames=new ArrayList<>();
        eventModel=new DefaultComboBoxModel<>(eventNames.toArray(new String[0]));
        tagChoices=new JComboBox<>(eventModel);
        labelPanel.add(tagChoices);
        JButton labelButton=new JButton("Search tag");
        labelButton.addActionListener(new MyLabelListener());
        labelPanel.add(labelButton);

        objectNames=new ArrayList<>();
        objectModel=new DefaultComboBoxModel<>(objectNames.toArray(new String[0]));
        objectChoices=new JComboBox<>(objectModel);
        labelPanel.add(objectChoices);
        JButton addButton=new JButton("Insert Object");
        addButton.addActionListener(new MyLabelListener());
        labelPanel.add(addButton);

        labelPanel.add(Box.createVerticalStrut(0)); labelPanel.add(Box.createVerticalStrut(0));
        labelPanel.add(new CustomSeparator(Color.BLACK, 3));labelPanel.add(new CustomSeparator(Color.BLACK, 3));
        rightPanel.add(labelPanel);
        //右:選取事件(上)
        JPanel choicesPanel=new JPanel(new GridLayout(5,1));
        choicesPanel.setBackground(Color.PINK);
        JLabel choicesLabel=new JLabel("What you are selecting now is:");
        choicesLabel.setFont(new Font("Arial",Font.PLAIN,20));
        choicesPanel.add(choicesLabel);
        choicesTextField=new JTextField("now selecting event");
        choicesTextField.setBackground(Color.LIGHT_GRAY);
        choicesTextField.setEditable(false);
        choicesPanel.add(choicesTextField);
        JPanel renamedPanel=new JPanel(new GridLayout(1,2));
        renamedPanel.setBackground(Color.PINK);
        JLabel renamedLabel=new JLabel("    renamed:");
        renamedLabel.setFont(new Font("Arial",Font.PLAIN,20));
        renamedPanel.add(renamedLabel);
        JTextField renamedTextField=new JTextField("enter name here");
        renamedTextField.addActionListener(new MyChoicesListener());
        renamedPanel.add(renamedTextField);
        choicesPanel.add(renamedPanel);
        JPanel recolorPanel=new JPanel(new GridLayout(1,2));
        recolorPanel.setBackground(Color.PINK);
        JLabel recolorLabel=new JLabel("    recolor:");
        recolorLabel.setFont(new Font("Arial",Font.PLAIN,20));
        recolorPanel.add(recolorLabel);
        JComboBox<String>  recolorChoices=new JComboBox<String>(colorNames);
        recolorChoices.addActionListener(new MyChoicesListener());
        recolorPanel.add(recolorChoices);
        choicesPanel.add(recolorPanel);
        rightPanel.add(choicesPanel);
        //右:選取事件(下)
        JPanel choicesBottomPanel=new JPanel(new GridLayout(5,1));
        choicesBottomPanel.setBackground(Color.PINK);
        JButton renewButton=new JButton("Update event");
        renewButton.addActionListener(new MyChoicesListener());
        choicesBottomPanel.add(renewButton);
        choicesBottomPanel.add(Box.createVerticalStrut(0));
        JButton checkButton=new JButton("Check event");
        checkButton.addActionListener(new MyChoicesListener());
        choicesBottomPanel.add(checkButton);
        choicesBottomPanel.add(Box.createVerticalStrut(0));
        choicesBottomPanel.add(new CustomSeparator(Color.BLACK, 3));
        rightPanel.add(choicesBottomPanel);
        //右:新建標籤 與 讀黨存檔
        JPanel savePanel=new JPanel(new GridLayout(4,2));
        savePanel.setBackground(Color.PINK);

        JLabel tagLabel=new JLabel("Tag's name:");
        tagLabel.setFont(new Font("Arial",Font.PLAIN,20));
        savePanel.add(tagLabel);
        tagTextField=new JTextField("enter name here");
        tagTextField.addActionListener(new MySaveListener());
        savePanel.add(tagTextField);
        JLabel newLabel=new JLabel("Create new tag:");
        newLabel.setFont(new Font("Arial",Font.PLAIN,17));
        savePanel.add(newLabel);
        newButton=new JButton("Add Tag");
        newButton.addActionListener(new MySaveListener());
        savePanel.add(newButton);
        savePanel.add(new CustomSeparator(Color.BLACK, 3));savePanel.add(new CustomSeparator(Color.BLACK, 3));
        saveButton =new JButton("Save");
        saveButton.addActionListener(new MySaveListener());
        savePanel.add(saveButton);
        readButton =new JButton("Read");
        readButton.addActionListener(new MySaveListener());
        savePanel.add(readButton);
        rightPanel.add(savePanel);
        //設定8:2畫面分割
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation((int) (screenSize.width * 0.8));
        splitPane.setEnabled(false);
        add(splitPane);
    }
    static class CustomSeparator extends JSeparator{//建立黑線
        private Color color;
        private int thickness;

        public CustomSeparator(Color color, int thickness) {
            super();
            this.color = color;
            this.thickness = thickness;
            setPreferredSize(new Dimension(1, thickness));
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(color);
            g.fillRect(0, 0, getWidth(), thickness);
        }
    }
    class MyEventListener implements ActionListener {//所有關於建立新事件的事件監聽器
        @Override
        public void actionPerformed(ActionEvent e){
           if(e.getSource()==eventButton){
                Event newEvent=new Event(eventInput.getText(),colors[eventColorChoices.getSelectedIndex()]);
                events.add(newEvent);
                eventNames.add(newEvent.getName());
                eventModel.addElement(newEvent.getName());

                choicesTextField.setText(events.get(eventCount).getName()+":"+events.get(eventCount).getColor());//最後要刪掉
                eventCount++;//最後要刪掉
            }
        }
    }
    private class MyObjectListener implements ActionListener{//所有關於添加新物件的事件監聽器
        final int P=1;
        final int R=0;
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==peopleButton){
                PorR=P;
            }
            else if(e.getSource()==resButton){
                PorR=R;
            }
            else if (e.getSource()==objectButton) {
                if(PorR==P){
                    People newPeople=new People(objectInput.getText());
                    Obs.add(newPeople);
                    objectNames.add(objectInput.getText());
                    objectModel.addElement("P:"+newPeople.getName());
                }
                else{
                    Res newRes=new Res(objectInput.getText());
                    Obs.add(newRes);
                    objectNames.add(objectInput.getText());
                    objectModel.addElement("R:"+newRes.getName());
                }
            }
        }
    }
    private class MyLabelListener implements ActionListener{//所有關於查詢標籤的事件監聽器
        @Override
        public void actionPerformed(ActionEvent e){

        }
    }
    private class MyChoicesListener implements ActionListener{//所有關於選取事件的事件監聽器
        @Override
        public void actionPerformed(ActionEvent e){

        }
    }
    private class MySaveListener implements ActionListener{//所有關於存檔讀檔的事件監聽器
        String tmp="";
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==newButton){
                objectTagModel.addElement(tagTextField.getText());
            }
            else if(e.getSource()==saveButton){
                tmp="save!";
                for(int i=0;i<eventCount;i++){
                    tmp+=events.get(i).getName()+":"+events.get(i).getColor()+"\n";
                }
                JOptionPane.showMessageDialog(null,tmp);
            }
            else if(e.getSource()==readButton){
                tmp="read!";
                for(int i=0;i<eventCount;i++){
                    tmp+=events.get(i).getName()+":"+events.get(i).getColor()+"\n";
                }
                JOptionPane.showMessageDialog(null,tmp);
            }
        }
    }
}
