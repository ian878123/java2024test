import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Objects;
import java.util.HashSet;

public class GUI extends JFrame {
    //base
    private ArrayList<Label> tags=new ArrayList<>();
    private Label noAffiliation=new Label("Default");
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
    private JButton newEventButton;
    private JButton confirmButton;
    private JTextField eventName;
    private JTextField eventText;
    private JFrame newFrame;
    //添加物件
    private JTextField objectInput;
    private JRadioButton peopleButton;
    private JRadioButton resButton;
    private JComboBox<String> objectTagChoices;
    private ArrayList<String> objectTagNames;
    private DefaultComboBoxModel<String> objectTagModel;
    private JButton objectButton;
    private int PorR=1;
    private JButton newObjectButton;
    private JTextField objectName;
    private JTextField objectText;

    //標籤查詢
    private JComboBox<String> tagChoices;
    private ArrayList<String> eventNames;
    private DefaultComboBoxModel<String> eventModel;
    private JComboBox<String> objectChoices;
    private ArrayList<String> objectNames;
    private DefaultComboBoxModel<String> objectModel;
    JButton labelButton;
    JButton insertButton;
    JButton insertTagButton;
    private JButton newTagButton;
    private JButton searchButton;

    //選取事件
    private Event nowSelected=null;
    private JTextField choicesTextField;
    JTextField renamedTextField;
    JComboBox<String>  recolorChoices;
    JButton updateButton;
    JButton checkButton;

    //刪除
    private JComboBox<String> deleteChoices;
    private JButton deleteButton;
    private JRadioButton eventR;
    private JRadioButton objectR;
    private JRadioButton tagR;

    //新建標籤 與 讀黨存檔
    private JTextField tagTextField;
    private JButton loadButton;
    private JButton newButton;
    private JButton saveButton;
    //拖曳需要所以獨立出來
    private JPanel leftPanel;
    private ArrayList<JButton> selectedButtons = new ArrayList<>(); // To store selected buttons
    private HashSet<Line> lines = new HashSet<>(); // To store lines

    private void Layer1(){

    }

    //GUI constructor
    public GUI(){
        //GUI建立
        super("Time Line");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width,screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tags.add(noAffiliation);
        //左側GUI
        leftPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                for (Line line : lines) {
                    Point p1 = line.getStartPoint();
                    Point p2 = line.getEndPoint();
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        };
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.add(new JLabel("Time Line"));
        leftPanel.setLayout(null);

        //右側GUI
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(6,1,0,10));
        rightPanel.setBackground(Color.darkGray);

        newEventButton = new JButton("New Event");
        newObjectButton = new JButton("New Object");
        newTagButton = new JButton("New Tag");
        searchButton = new JButton("Object Search and Update");
        deleteButton = new JButton("Delete");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        newEventButton.setFocusable(false);
        newObjectButton.setFocusable(false);
        newTagButton.setFocusable(false);
        saveButton.setFocusable(false);
        loadButton.setFocusable(false);
        searchButton.setFocusable(false);
        deleteButton.setFocusable(false);

        newEventButton.setFont(new Font("Arial",Font.BOLD,25));
        newObjectButton.setFont(new Font("Arial",Font.BOLD,25));
        newTagButton.setFont(new Font("Arial",Font.BOLD,25));
        saveButton.setFont(new Font("Arial",Font.BOLD,25));
        loadButton.setFont(new Font("Arial",Font.BOLD,25));
        searchButton.setFont(new Font("Arial",Font.BOLD,25));
        deleteButton.setFont(new Font("Arial",Font.BOLD,25));

        newEventButton.setBackground(Color.white);
        newEventButton.setForeground(Color.black);
        newObjectButton.setBackground(Color.white);
        newObjectButton.setForeground(Color.black);
        newTagButton.setBackground(Color.white);
        newTagButton.setForeground(Color.black);
        saveButton.setBackground(Color.white);
        saveButton.setForeground(Color.black);
        loadButton.setBackground(Color.white);
        loadButton.setForeground(Color.black);
        searchButton.setBackground(Color.white);
        searchButton.setForeground(Color.black);
        deleteButton.setBackground(Color.white);
        deleteButton.setForeground(Color.black);

        JPanel deletePanel = new JPanel();
        JPanel deletePanel2 = new JPanel();
        eventR = new JRadioButton("Event");
        objectR = new JRadioButton("Object");
        tagR = new JRadioButton("Tag");
        eventR.setFont(new Font("Arial",Font.PLAIN,20));
        objectR.setFont(new Font("Arial",Font.PLAIN,20));
        tagR.setFont(new Font("Arial",Font.PLAIN,20));
        ButtonGroup deleteGroup = new ButtonGroup();
        deleteGroup.add(eventR);
        deleteGroup.add(objectR);
        deleteGroup.add(tagR);
        deletePanel.setLayout(new GridLayout(1,3));
        deletePanel.add(eventR);
        deletePanel.add(objectR);
        deletePanel.add(tagR);
        deletePanel2.setLayout(new BorderLayout());
        deletePanel2.add(deletePanel,BorderLayout.NORTH);
        deletePanel2.add(deleteButton,BorderLayout.CENTER);

        JLabel saveLabel = new JLabel();
        saveLabel.setLayout(new GridLayout(1,2));
        saveLabel.add(saveButton);
        saveLabel.add(loadButton);

        newEventButton.addActionListener(new MyEventListener());
        newObjectButton.addActionListener(new MyObjectListener());
        newTagButton.addActionListener(new MySaveListener());
        saveButton.addActionListener(new MySaveListener());
        loadButton.addActionListener(new MySaveListener());
        searchButton.addActionListener(new MyTagListener());
        deleteButton.addActionListener(new MyChoicesListener());


        rightPanel.add(newEventButton);
        rightPanel.add(newObjectButton);
        rightPanel.add(newTagButton);
        rightPanel.add(searchButton);
        rightPanel.add(deletePanel2);
        rightPanel.add(saveLabel);


        objectTagNames=new ArrayList<>();
        objectTagNames.add("Default");
        objectTagModel=new DefaultComboBoxModel<>(objectTagNames.toArray(new String[0]));

        objectNames=new ArrayList<>();
        objectNames.add("請選擇物件");
        objectModel=new DefaultComboBoxModel<>(objectNames.toArray(new String[0]));
        objectChoices=new JComboBox<>(objectModel);

        eventNames=new ArrayList<>();
        eventNames.add("Default");

        eventModel=new DefaultComboBoxModel<>(objectTagNames.toArray(new String[0]));
        tagChoices=new JComboBox<>(eventModel);
        //右:新事件
        /*
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
        eventInput.addMouseListener(new MouseAdapter(){//點擊文字框時清空文字
            @Override
            public void mouseClicked(MouseEvent e){
                if(eventInput.getText().equals("Enter name here"))
                    eventInput.setText("");
            }
        });
        eventPanel.add(eventInput);
        eventColorChoices=new JComboBox<String>(colorNames);
        eventPanel.add(eventColorChoices);
        eventButton=new JButton("Create");
        //建立事件N的按鈕
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
        objectInput.addMouseListener(new MouseAdapter(){    //點擊文字框時清空文字
            @Override
            public void mouseClicked(MouseEvent e){
                if(objectInput.getText().equals("Enter name here"))
                    objectInput.setText("");
            }
        });
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
        objectTagNames.add("Default");
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
        labelPanel=new JPanel(new GridLayout(5,2));
        labelPanel.setBackground(Color.PINK);
        eventNames=new ArrayList<>();
        eventNames.add("Default");
        eventModel=new DefaultComboBoxModel<>(objectTagNames.toArray(new String[0]));
        tagChoices=new JComboBox<>(eventModel);
        labelPanel.add(tagChoices);
        labelButton=new JButton("Search tag");
        labelButton.addActionListener(new MyTagListener());
        labelPanel.add(labelButton);
        objectNames=new ArrayList<>();
        objectNames.add("請選擇物件");
        objectModel=new DefaultComboBoxModel<>(objectNames.toArray(new String[0]));
        objectChoices=new JComboBox<>(objectModel);
        labelPanel.add(objectChoices);
        insertButton=new JButton("Insert to event");
        insertButton.addActionListener(new MyTagListener());
        labelPanel.add(insertButton);
        JLabel jLabel=new JLabel("Add objects to tags:");
        jLabel.setFont(new Font("Arial",Font.PLAIN,15));
        labelPanel.add(jLabel);
        insertTagButton=new JButton("Insert to tag");
        insertTagButton.addActionListener(new MyTagListener());
        labelPanel.add(insertTagButton);
        labelPanel.add(Box.createVerticalStrut(0)); labelPanel.add(Box.createVerticalStrut(0));
        labelPanel.add(new CustomSeparator(Color.BLACK, 3));labelPanel.add(new CustomSeparator(Color.BLACK, 3));
        rightPanel.add(labelPanel);
        //右:選取事件(上)
        JPanel choicesPanel=new JPanel(new GridLayout(5,1));
        choicesPanel.setBackground(Color.PINK);
        JLabel choicesLabel=new JLabel("What you are selecting now is:");
        choicesLabel.setFont(new Font("Arial",Font.PLAIN,20));
        choicesPanel.add(choicesLabel);
        choicesTextField=new JTextField("no selecting");
        choicesTextField.setBackground(Color.LIGHT_GRAY);
        choicesTextField.setEditable(false);
        choicesPanel.add(choicesTextField);
        JPanel renamedPanel=new JPanel(new GridLayout(1,2));
        renamedPanel.setBackground(Color.PINK);
        JLabel renamedLabel=new JLabel("    Rename:");
        renamedLabel.setFont(new Font("Arial",Font.PLAIN,20));
        renamedPanel.add(renamedLabel);
        renamedTextField=new JTextField("Enter name here");
        renamedTextField.addActionListener(new MyChoicesListener());
        renamedTextField.addMouseListener(new MouseAdapter(){//點擊文字框時清空文字
            @Override
            public void mouseClicked(MouseEvent e){
                if(renamedTextField.getText().equals("Enter name here"))
                    renamedTextField.setText("");
            }
        });
        renamedPanel.add(renamedTextField);
        choicesPanel.add(renamedPanel);
        JPanel recolorPanel=new JPanel(new GridLayout(1,2));
        recolorPanel.setBackground(Color.PINK);
        JLabel recolorLabel=new JLabel("    Recolor:");
        recolorLabel.setFont(new Font("Arial",Font.PLAIN,20));
        recolorPanel.add(recolorLabel);
        recolorChoices=new JComboBox<String>(colorNames);
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
        tagTextField=new JTextField("Enter name here");
        tagTextField.addActionListener(new MySaveListener());
        tagTextField.addMouseListener(new MouseAdapter(){//點擊文字框時清空文字
            @Override
            public void mouseClicked(MouseEvent e){
                if(tagTextField.getText().equals("Enter name here"))
                    tagTextField.setText("");
            }
        });
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
        readButton =new JButton("Load");
        readButton.addActionListener(new MySaveListener());
        savePanel.add(readButton);
        rightPanel.add(savePanel);

         */
        //設定8:2畫面分割
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation((int) (screenSize.width * 0.8));
        splitPane.setEnabled(false);
        add(splitPane);
    }
    private class Line {
        JButton btn1;
        JButton btn2;
        Point start;
        Point end;

        Line(JButton btn1, JButton btn2) {
            this.btn1 = btn1;
            this.btn2 = btn2;
            this.start = new Point(btn1.getX() + btn1.getWidth() / 2, btn1.getY() + btn1.getHeight() / 2);
            this.end = new Point(btn2.getX() + btn2.getWidth() / 2, btn2.getY() + btn2.getHeight() / 2);
        }

        Point getStartPoint() {
            return new Point(btn1.getX() + btn1.getWidth() / 2, btn1.getY() + btn1.getHeight() / 2);
        }

        Point getEndPoint() {
            return new Point(btn2.getX() + btn2.getWidth() / 2, btn2.getY() + btn2.getHeight() / 2);
        }
    }

    private void createDraggableButton(Event event,int x,int y) {
        JButton newButton = new JButton(event.getName());
        newButton.setLocation(x,y);
        newButton.setSize(150, 30);
        newButton.setBackground(event.getColor());

        newButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    if (!selectedButtons.contains(newButton)) {
                        selectedButtons.add(newButton);
                        if (selectedButtons.size() % 2 == 0) {
                            // Connect the last two selected buttons
                            JButton btn1 = selectedButtons.get(selectedButtons.size() - 2);
                            JButton btn2 = selectedButtons.get(selectedButtons.size() - 1);
                            lines.add(new Line(btn1, btn2));
                            // Add connected event names to each event
                            Event event1 = getEventByName(btn1.getText());
                            Event event2 = getEventByName(btn2.getText());
                            if (event1 != null && event2 != null) {
                                event1.addConnectedEvent(event2.getName());
                                event2.addConnectedEvent(event1.getName());
                            }

                            selectedButtons.clear(); // Clear selection after drawing the line
                        }
                    } else {
                        // Remove button from selected and associated lines
                        selectedButtons.remove(newButton);
                        lines.removeIf(line -> line.btn1 == newButton || line.btn2 == newButton);
                    }
                    leftPanel.repaint();
                }
            }
        });
        // Add mouse listener for dragging
        newButton.addMouseMotionListener(new MouseMotionAdapter() {
            Point lastPoint = null;

            @Override
            public void mouseDragged(MouseEvent e) {
                if (lastPoint != null) {
                    Point newPoint = e.getLocationOnScreen();
                    newButton.setLocation(newButton.getX() + (newPoint.x - lastPoint.x),
                            newButton.getY() + (newPoint.y - lastPoint.y));
                    event.setX(newButton.getX());
                    event.setY(newButton.getY());
                }
                lastPoint = e.getLocationOnScreen();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                lastPoint = e.getLocationOnScreen();
            }

        });

        newButton.addMouseListener(new MouseAdapter() {
            Point initialClick = null;
            long initialTime;
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                initialTime = System.currentTimeMillis();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                long releaseTime = System.currentTimeMillis();
                if (releaseTime - initialTime < 200) { // Check if the time is within 200 ms
                    Point releasePoint = e.getPoint();
                    if (initialClick.distance(releasePoint) < 5) { // Check if the distance is within 5 pixels
                        nowSelected=event;
                        String name = nowSelected.getName();
                        new UpdateEventGUI(nowSelected,Obs,tags);
                        //choicesTextField.setText(nowSelected.getName());
                    }
                }
            }
        });

        leftPanel.add(newButton);
        leftPanel.repaint();
        leftPanel.revalidate();
    }
    private Event getEventByName(String name) {
        for (Event event : events) {
            if (event.getName().equals(name)) {
                return event;
            }
        }
        return null;
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

            if(e.getSource()== newEventButton){
                newFrame =new JFrame("Add Event");
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newFrame.setSize(400,400);
                newFrame.setLocationRelativeTo(GUI.this);
                newFrame.setVisible(true);
                newFrame.setLayout(new GridLayout(4,2,10,10));

                confirmButton=new JButton("Confirm");
                confirmButton.addActionListener(new MyEventListener());
                eventName =new JTextField();
                eventText =new JTextField();
                eventColorChoices=new JComboBox<>(colorNames);
                JLabel eventNameLabel=new JLabel("Event Name:");
                JLabel eventTextLabel=new JLabel("Event Text:");
                JLabel eventColorLabel=new JLabel("Event Color:");

                newFrame.add(eventNameLabel);
                newFrame.add(eventName);
                newFrame.add(eventTextLabel);
                newFrame.add(eventText);
                newFrame.add(eventColorLabel);
                newFrame.add(eventColorChoices);
                newFrame.add(confirmButton);
                newFrame.setVisible(true);

            }
            if(e.getSource()==confirmButton){
                boolean isNameExist = false;
                for (Event event : events) {
                    if (Objects.equals(event.getName(), eventName.getText())) {
                        isNameExist = true;
                        break;
                    }
                }
                if (!eventName.getText().isEmpty() && !isNameExist) {
                    Event newEvent = new Event(eventName.getText(), colors[eventColorChoices.getSelectedIndex()],0,0);
                    newEvent.setDescribe(eventText.getText());
                    events.add(newEvent);
                    createDraggableButton(newEvent,0,0);
                    newFrame.dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"事件已存在");
                }
            }
            /*
            if(e.getSource()==eventButton){
                String eventName = eventInput.getText();
                Color eventColor = colors[eventColorChoices.getSelectedIndex()];
                boolean isNameExist = false;
                for (Event event : events) {
                    if (Objects.equals(event.getName(), eventName)) {
                        isNameExist = true;
                        break;
                    }
                }
                if (!eventName.isEmpty() && !isNameExist) {
                    Event newEvent = new Event(eventName, eventColor,0,0);
                    JButton tmpButton;
                    events.add(newEvent);
                    createDraggableButton(newEvent,0,0);
                    describeGUI frame=new describeGUI(newEvent);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setLocationRelativeTo(GUI.this);
                    frame.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"事件已存在");
                }
                eventInput.setText("");
            }

             */
        }
    }
    private class MyObjectListener implements ActionListener{//所有關於添加新物件的事件監聽器
        final int P=1;
        final int R=0;
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==newObjectButton){
                newFrame =new JFrame("Add Object");
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newFrame.setSize(400,400);
                newFrame.setLocationRelativeTo(GUI.this);
                newFrame.setVisible(true);
                newFrame.setLayout(new GridLayout(5,2,10,10));

                confirmButton=new JButton("Confirm");
                confirmButton.addActionListener(new MyObjectListener());
                objectName =new JTextField();
                objectText =new JTextField();
                peopleButton =new JRadioButton("People",true);
                resButton =new JRadioButton("Res",false);
                ButtonGroup group=new ButtonGroup();
                group.add(peopleButton);
                group.add(resButton);
                peopleButton.addActionListener(new MyObjectListener());
                resButton.addActionListener(new MyObjectListener());
                objectTagChoices=new JComboBox<>(objectTagNames.toArray(new String[0]));


                JLabel objectNameLabel=new JLabel("Object Name:");
                JLabel objectTextLabel=new JLabel("Object Text:");
                JLabel objectTagLabel=new JLabel("Object Tag:");

                newFrame.add(objectNameLabel);
                newFrame.add(objectName);
                newFrame.add(objectTextLabel);
                newFrame.add(objectText);
                newFrame.add(peopleButton);
                newFrame.add(resButton);
                newFrame.add(objectTagLabel);
                newFrame.add(objectTagChoices);
                newFrame.add(confirmButton);

                newFrame.setVisible(true);
            }
            if(e.getSource()==confirmButton){
                boolean isNameExist = false;
                for (Ob ob : Obs) {
                    if (Objects.equals(ob.getName(), objectName.getText())) {
                        isNameExist = true;
                        break;
                    }
                }
                if (!objectName.getText().isEmpty() && !isNameExist) {
                    if(peopleButton.isSelected()){
                        People newPeople=new People(objectName.getText());
                        newPeople.setDescribe(objectText.getText());
                        newPeople.addTag(tags.get(objectTagChoices.getSelectedIndex()));

                        Obs.add(newPeople);
                        objectNames.add(newPeople.getName());
                        objectModel.addElement("P:"+newPeople.getName());
                        tags.get(objectTagChoices.getSelectedIndex()).newMember(newPeople);
                        newFrame.dispose();
                    }else{
                        Res newRes=new Res(objectName.getText());
                        newRes.setDescribe(objectText.getText());
                        newRes.addTag(tags.get(objectTagChoices.getSelectedIndex()));

                        Obs.add(newRes);
                        objectNames.add(newRes.getName());
                        objectModel.addElement("R:"+newRes.getName());
                        tags.get(objectTagChoices.getSelectedIndex()).newMember(newRes);
                        newFrame.dispose();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"物件已存在");
                }

            }
            /*
            if(e.getSource()==peopleButton){
                PorR=P;
            }
            else if(e.getSource()==resButton){
                PorR=R;
            }
            else if (e.getSource()==objectButton) {
                boolean isNameExist = false;
                for (Ob ob : Obs) {
                    if (Objects.equals(ob.getName(), objectInput.getText())) {
                        isNameExist = true;
                        break;
                    }
                }
                if(PorR==P && !isNameExist){
                    People newPeople=new People(objectInput.getText());
                    describeGUI frame=new describeGUI(newPeople);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setLocationRelativeTo(GUI.this);
                    frame.setVisible(true);
                    newPeople.addTag(tags.get(objectTagChoices.getSelectedIndex()));
                    Obs.add(newPeople);

                    objectNames.add(objectInput.getText());
                    objectModel.addElement("P:"+newPeople.getName());
                    tags.get(objectTagChoices.getSelectedIndex()).newMember(newPeople);
                }
                else if(PorR==R && !isNameExist){
                    Res newRes=new Res(objectInput.getText());
                    describeGUI frame=new describeGUI(newRes);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setLocationRelativeTo(GUI.this);
                    frame.setVisible(true);
                    newRes.addTag(tags.get(objectTagChoices.getSelectedIndex()));
                    Obs.add(newRes);

                    objectNames.add(objectInput.getText());
                    objectModel.addElement("R:"+newRes.getName());
                    tags.get(objectTagChoices.getSelectedIndex()).newMember(newRes);
                }else{
                    JOptionPane.showMessageDialog(null,"物件已存在");
                }
                objectInput.setText("");
            }

             */
        }
    }
    private class MyTagListener implements ActionListener{//所有關於查詢標籤的事件監聽器
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==searchButton){
                Search newSearchGUI=new Search(tags,Obs);
                newSearchGUI.setLocationRelativeTo(GUI.this);

            }
            if(e.getSource()==labelButton){
                searchGUI newSearchGUI=new searchGUI(tags.get(tagChoices.getSelectedIndex()));
                newSearchGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newSearchGUI.setSize(350,300);
                newSearchGUI.setLocationRelativeTo(GUI.this);
                newSearchGUI.setVisible(true);
            }
            else if (e.getSource()==insertButton) {
                if(nowSelected==null){
                    JOptionPane.showMessageDialog(null,"請選擇事件");
                }
                else{
                    Ob tmp;
                    tmp=Obs.get(objectChoices.getSelectedIndex()-1);
                    if(objectChoices.getSelectedIndex()>0){//確保選到"請選擇物件"時不會報錯
                        if(tmp instanceof People){
                            nowSelected.addNewMember((People) tmp);
                            ((People) tmp).addEvent(nowSelected);
                        }
                        else if(tmp instanceof Res){
                            nowSelected.addNewMember((Res) tmp);
                            ((Res) tmp).addEvent(nowSelected);
                        }
                    }
                }
            }
            else if(e.getSource()==insertTagButton){
                Ob tep;
                tep=Obs.get(objectChoices.getSelectedIndex()-1);
                if(objectChoices.getSelectedIndex()>0){
                    tags.get(tagChoices.getSelectedIndex()).newMember(tep);
                    if(tep instanceof People){
                        ((People) tep).addTag(tags.get(tagChoices.getSelectedIndex()));
                    }
                    else if(tep instanceof Res){
                        ((Res) tep).addTag(tags.get(tagChoices.getSelectedIndex()));
                    }
                }
            }
        }
    }
    private class MyChoicesListener implements ActionListener{//所有關於選取事件的事件監聽器
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==updateButton){
                if(nowSelected!=null){
                   // nowSelected.setDrawingColor();
                   // nowSelected.setName();
                    Event newEvent=new Event(renamedTextField.getText(),colors[recolorChoices.getSelectedIndex()],nowSelected.getX(),nowSelected.getY());
                    events.add(newEvent);
                    createDraggableButton(newEvent,newEvent.getX(),newEvent.getY());
                    deleteEvent(nowSelected);
                    nowSelected=null;
                    choicesTextField.setText("no selecting");
                }
                else{
                    JOptionPane.showMessageDialog(null,"沒有選到事件");
                }
                renamedTextField.setText("");
            }
            else if(e.getSource()==checkButton){
                searchGUI newSearchGUI=new searchGUI(nowSelected);
                newSearchGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newSearchGUI.setSize(350,300);
                newSearchGUI.setLocationRelativeTo(GUI.this);
                newSearchGUI.setVisible(true);
            }
            else if(e.getSource()==deleteButton){
                if(eventR.isSelected()){
                    deleteGUI newDeleteGUI=new deleteGUI(events,GUI.this);
                    newDeleteGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    newDeleteGUI.setSize(350,300);
                    newDeleteGUI.setLocationRelativeTo(GUI.this);
                    newDeleteGUI.setVisible(true);
                }
                else if(objectR.isSelected()){
                    deleteGUI newDeleteGUI=new deleteGUI(Obs,1,GUI.this);
                    newDeleteGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    newDeleteGUI.setSize(350,300);
                    newDeleteGUI.setLocationRelativeTo(GUI.this);
                    newDeleteGUI.setVisible(true);
                }
                else{
                    deleteGUI newDeleteGUI=new deleteGUI(tags,"1",GUI.this);
                    newDeleteGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    newDeleteGUI.setSize(350,300);
                    newDeleteGUI.setLocationRelativeTo(GUI.this);
                    newDeleteGUI.setVisible(true);}
            }
        }
    }
    private class MySaveListener implements ActionListener{//所有關於存檔讀檔和新建標籤的事件監聽器
        String tmp="";
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==newTagButton){
                newFrame =new JFrame("Add Tag");
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newFrame.setSize(400,400);
                newFrame.setLocationRelativeTo(GUI.this);
                newFrame.setVisible(true);
                newFrame.setLayout(new GridLayout(2,2,10,10));

                confirmButton=new JButton("Confirm");
                confirmButton.addActionListener(new MySaveListener());
                tagTextField =new JTextField();
                JLabel tagLabel=new JLabel("Tag Name:");

                newFrame.add(tagLabel);
                newFrame.add(tagTextField);
                newFrame.add(confirmButton);
                newFrame.setVisible(true);
            }
            if(e.getSource()==confirmButton) {
                boolean isNameExist = false;

                for (Label tag : tags) {
                    if (Objects.equals(tag.getName(), tagTextField.getText())) {
                        isNameExist = true;
                        break;
                    }
                }

                if (!tagTextField.getText().isEmpty() && !isNameExist) {
                    Label newLabel = new Label(tagTextField.getText());
                    objectTagNames.add(newLabel.getName());
                    eventNames.add(newLabel.getName());
                    objectTagModel.addElement(newLabel.getName());
                    eventModel.addElement(newLabel.getName());
                    tags.add(newLabel);
                    newFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "標籤已存在");
                }
            }
            /*
            if(e.getSource()==newButton){
                boolean isNameExist = false;
                for (Label tag : tags) {
                    if (Objects.equals(tag.getName(), tagTextField.getText())) {
                        isNameExist = true;
                        break;
                    }
                }
                if (!isNameExist) {
                    Label newLabel=new Label(tagTextField.getText());
                    objectTagNames.add(newLabel.getName());
                    eventNames.add(newLabel.getName());
                    objectTagModel.addElement(newLabel.getName());
                    eventModel.addElement(newLabel.getName());
                    tags.add(newLabel);
                }else {
                    JOptionPane.showMessageDialog(null,"標籤已存在");
                }
                tagTextField.setText("");
            }

             */
            if(e.getSource()==saveButton){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("儲存成專案檔案夾");
                fileChooser.setCurrentDirectory(new java.io.File("."));//設定初始路徑
                int result = fileChooser.showSaveDialog(GUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    System.out.println("getSelectedFile(): " + fileChooser.getSelectedFile());
                    Saving.SaveEvent(events, fileChooser.getSelectedFile());
                    Saving.SaveLable(tags, fileChooser.getSelectedFile());
                    Saving.SaveOb(Obs, fileChooser.getSelectedFile());
                    /*
                    File selectedFile = fileChooser.getSelectedFile();
                    if (!selectedFile.getAbsolutePath().toLowerCase().endsWith(".txt")) {
                        selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
                    }
                    writeFile(selectedFile);
                    */
                }
            }
            else if(e.getSource()== loadButton){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("選擇專案檔案夾");
                fileChooser.setCurrentDirectory(new java.io.File("."));//設定初始路徑
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //設定只能選擇資料夾
                int result = fileChooser.showOpenDialog(GUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    System.out.println("getSelectedFile(): " + fileChooser.getSelectedFile());
                    events=Loading.LoadEvent(fileChooser.getSelectedFile());
                    Obs=Loading.LoadOb(fileChooser.getSelectedFile());
                    tags=Loading.LoadLabel(fileChooser.getSelectedFile());
                    /*
                    File selectedFile = fileChooser.getSelectedFile();
                    readFile(selectedFile);
                    */
                    leftPanel.removeAll();
                    leftPanel.revalidate();
                    leftPanel.repaint();

                    selectedButtons.clear();
                    lines.clear();

                    for(Event ee:events){
                        createDraggableButton(ee,ee.getX(),ee.getY());
                    }

                    for (Event event : events) {
                        for (String connectedEventName : event.getConnectedEventNames()) {
                            Event connectedEvent = getEventByName(connectedEventName);
                            if (connectedEvent != null) {
                                JButton btn1 = getButtonByName(event.getName());
                                JButton btn2 = getButtonByName(connectedEvent.getName());
                                if (btn1 != null && btn2 != null) {
                                    lines.add(new Line(btn1, btn2));
                                }
                            }
                        }
                    }
                    leftPanel.repaint();
                    objectTagModel.removeElementAt(0);
                    eventModel.removeElementAt(0);
                    for(Label tag:tags){
                        objectTagModel.addElement(tag.getName());
                        eventModel.addElement(tag.getName());
                    }
                }
            }
        }
    }
    /*
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
    */

    public void deleteEvent(Event event) {
        if (events.contains(event)) {
            events.remove(event);

            // Remove the corresponding button from the left panel
            Component[] components = leftPanel.getComponents();
            for (Component component : components) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    if (button.getText().equals(event.getName())) {
                        lines.removeIf(line -> line.btn1 == button || line.btn2 == button);
                        leftPanel.remove(button);
                        break;
                    }
                }
            }

            leftPanel.repaint();
            leftPanel.revalidate();
        }
    }
    public void deleteObs(Ob ob) {
        if(ob instanceof People){
            String tmp=ob.getName();
            for(Label t:((People) ob).getTagsIn()){
                t.deleteMember(ob);
            }
            for(Event e:((People) ob).getEventsIn()){
                e.deleteMember((People)ob);
            }
            Obs.remove(ob);
            objectNames.remove(tmp);
            objectModel.removeElement("P:"+tmp);
        }
        else if(ob instanceof Res){
            String tmp=ob.getName();
            for(Label t:((Res) ob).getTagsIn()){
                t.deleteMember(ob);
            }
            for(Event e:((Res) ob).getEventsIn()){
                e.deleteMember((Res)ob);
            }
            Obs.remove(ob);
            objectNames.remove(tmp);
            objectModel.removeElement("R:"+tmp);
        }
    }
    public void deleteTag(Label tag) {
        String tmp=tag.getName();
        objectTagNames.remove(tmp);
        eventNames.remove(tmp);
        objectTagModel.removeElement(tmp);
        eventModel.removeElement(tmp);
        tags.remove(tag);
    }
    private JButton getButtonByName(String name) {
        for (Component component : leftPanel.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals(name)) {
                    return button;
                }
            }
        }
        return null;
    }
}
