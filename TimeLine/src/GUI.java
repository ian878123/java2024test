import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class GUI extends JFrame {
    //base
    private ArrayList<Label> tags=new ArrayList<>();
    private Label noAffiliation=new Label("無隸屬");
    private JPanel labelPanel;
    private ArrayList<String> tagNames;
    private final String[] colorNames = { "Blue", "Cyan", "Gray", "Green", "Magenta", "Orange", "Pink", "Red", "Yellow", "White", "Black" };
    private final String[] deleteNames={"Event","Object","Tag"};
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
    JButton labelButton;
    JButton insertButton;
    //選取事件
    private JTextField choicesTextField;
    JButton updateButton;
    JButton checkButton;
    private JComboBox<String> deleteChoices;
    private JButton deleteButton;
    //新建標籤 與 讀黨存檔
    private JTextField tagTextField;
    private JButton readButton;
    private JButton newButton;
    private JButton saveButton;
    //拖曳需要所以獨立出來
    private JPanel leftPanel;
    //GUI constructor
    public GUI(){
        //GUI建立
        super("Time Line");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width,screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tags.add(noAffiliation);
        //左側GUI
        leftPanel = new JPanel();
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
        //建立事件N的按鈕
        eventButton.addActionListener(new MyEventListener());
        eventButton.addActionListener(e -> {
            String buttonName = eventInput.getText();
            if (!buttonName.isEmpty()) {
                createDraggableButton(buttonName);
            }
        });
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
        objectTagNames.add("無隸屬");
        objectTagModel=new DefaultComboBoxModel<>(objectTagNames.toArray(new String[0]));
        objectTagChoices=new JComboBox<>(objectTagModel);
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
        eventNames.add("無隸屬");
        eventModel=new DefaultComboBoxModel<>(objectTagNames.toArray(new String[0]));
        tagChoices=new JComboBox<>(eventModel);
        labelPanel.add(tagChoices);
        labelButton=new JButton("Search tag");
        labelButton.addActionListener(new MyTagListener());
        labelPanel.add(labelButton);
        objectNames=new ArrayList<>();
        objectModel=new DefaultComboBoxModel<>(objectNames.toArray(new String[0]));
        objectChoices=new JComboBox<>(objectModel);
        labelPanel.add(objectChoices);
        insertButton=new JButton("Insert Object");
        insertButton.addActionListener(new MyTagListener());
        labelPanel.add(insertButton);

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
        JPanel UCPanel=new JPanel(new GridLayout(1,2));
        UCPanel.setBackground(Color.PINK);
        updateButton=new JButton("Update event");
        updateButton.addActionListener(new MyChoicesListener());
        UCPanel.add(updateButton);
        checkButton=new JButton("Check event");
        checkButton.addActionListener(new MyChoicesListener());
        UCPanel.add(checkButton);
        choicesBottomPanel.add(UCPanel);
        JPanel deletePanel =new JPanel(new GridLayout(1,2));
        deletePanel.setBackground(Color.PINK);
        JLabel deleteLabel =new JLabel("delete:");
        deleteLabel.setFont(new Font("Arial",Font.PLAIN,20));
        deletePanel.add(deleteLabel);
        deleteChoices=new JComboBox<String>(deleteNames);
        deleteChoices.addActionListener(new MyChoicesListener());
        deletePanel.add(deleteChoices);
        choicesBottomPanel.add(deletePanel);
        deleteButton=new JButton("delete");
        deleteButton.addActionListener(new MyChoicesListener());
        choicesBottomPanel.add(deleteButton);
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
                    tags.get(objectTagChoices.getSelectedIndex()).newMember(newPeople);
                }
                else{
                    Res newRes=new Res(objectInput.getText());
                    Obs.add(newRes);
                    objectNames.add(objectInput.getText());
                    objectModel.addElement("R:"+newRes.getName());
                    tags.get(objectTagChoices.getSelectedIndex()).newMember(newRes);
                }
            }
        }
    }
    private class MyTagListener implements ActionListener{//所有關於查詢標籤的事件監聽器
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==labelButton){
                searchGUI newSearchGUI=new searchGUI(tags.get(tagChoices.getSelectedIndex()));
                newSearchGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newSearchGUI.setSize(350,300);
                newSearchGUI.setLocationRelativeTo(GUI.this);
                newSearchGUI.setVisible(true);
            }
            else if (e.getSource()==insertButton) {

            }
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
                Label newLabel=new Label(tagTextField.getText());
                objectNames.add(newLabel.getName());
                eventNames.add(newLabel.getName());
                objectTagModel.addElement(newLabel.getName());
                eventModel.addElement(newLabel.getName());
                tags.add(newLabel);
            }
            else if(e.getSource()==saveButton){
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(GUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    if (!selectedFile.getAbsolutePath().toLowerCase().endsWith(".txt")) {
                        selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
                    }
                    writeFile(selectedFile);
                }
            }
            else if(e.getSource()==readButton){
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(GUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    readFile(selectedFile);
                }
            }
        }
    }
    private void readFile(File file) {
        StringBuilder tmp= new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            tmp = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                tmp.append(line).append("\n");
            }
            JOptionPane.showMessageDialog(null, tmp.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void writeFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("建立檔案！");
            JOptionPane.showMessageDialog(this, "File saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createDraggableButton(String buttonName) {
        JButton newButton = new JButton(buttonName);
        newButton.setSize(150, 30);
        // 獲取所選事件顏色的索引
        int selectedColorIndex = eventColorChoices.getSelectedIndex();
        if (selectedColorIndex >= 0 && selectedColorIndex < colors.length) {
            // 根據索引獲取顏色並設置按鈕的背景色
            newButton.setBackground(colors[selectedColorIndex]);
        }
        // Add mouse listener for dragging
        newButton.addMouseMotionListener(new MouseMotionAdapter() {
            Point lastPoint = null;

            @Override
            public void mouseDragged(MouseEvent e) {
                if (lastPoint != null) {
                    Point newPoint = e.getLocationOnScreen();
                    newButton.setLocation(newButton.getX() + (newPoint.x - lastPoint.x),
                            newButton.getY() + (newPoint.y - lastPoint.y));
                }
                lastPoint = e.getLocationOnScreen();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                lastPoint = e.getLocationOnScreen();
            }
        });


        leftPanel.add(newButton);
        leftPanel.repaint();
        leftPanel.revalidate();
    }
}
