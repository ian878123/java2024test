import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

public class GUI extends JFrame {
    Label labels=new Label("labels");
    private final String[] colorNames = { "Blue", "Cyan", "Gray", "Green", "Magenta", "Orange", "Pink", "Red", "Yellow", "White", "Black" };
    public GUI(){
        super("Time Line");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width,screenSize.height);
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.add(new JLabel("Time Line"));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(5,1));
        rightPanel.setBackground(Color.PINK);

        JPanel eventPanel=new JPanel(new GridLayout(4,2));
        eventPanel.setBackground(Color.PINK);
        JLabel eventLabel=new JLabel("new event:");
        eventLabel.setFont(new Font("Arial",Font.PLAIN,25));
        eventLabel.setOpaque(true);
        eventLabel.setBackground(Color.PINK);
        eventPanel.add(eventLabel);
        JTextField eventInput=new JTextField("Enter name here",5);
        eventInput.addActionListener(new MyEventListner());
        eventPanel.add(eventInput);
        JComboBox<String> eventColorChoices=new JComboBox<String>(colorNames);
        eventColorChoices.addActionListener(new MyEventListner());
        eventPanel.add(eventColorChoices);
        JButton eventButton=new JButton("Create");
        eventButton.addActionListener(new MyEventListner());
        eventPanel.add(eventButton);
        rightPanel.add(eventPanel);
        eventPanel.add(Box.createVerticalStrut(0));eventPanel.add(Box.createVerticalStrut(0));
        eventPanel.add(new CustomSeparator(Color.BLACK, 2));eventPanel.add(new CustomSeparator(Color.BLACK, 2));

        JPanel objectPanel=new JPanel(new GridLayout(4,2));
        objectPanel.setBackground(Color.PINK);
        JLabel objectLabel=new JLabel("new object:");
        objectLabel.setFont(new Font("Arial",Font.PLAIN,25));
        objectLabel.setOpaque(true);
        objectLabel.setBackground(Color.PINK);
        objectPanel.add(objectLabel);
        JTextField objectInput=new JTextField("Enter name here",5);
        objectInput.addActionListener(new MyObjectListner());
        objectPanel.add(objectInput);
        JComboBox<String> objectColorChoices=new JComboBox<String>(colorNames);
        objectColorChoices.addActionListener(new MyObjectListner());
        objectPanel.add(objectColorChoices);
        JButton objectButton=new JButton("Create");
        objectButton.addActionListener(new MyObjectListner());
        objectPanel.add(objectButton);
        rightPanel.add(objectPanel);
        objectPanel.add(Box.createVerticalStrut(0)); objectPanel.add(Box.createVerticalStrut(0));
        objectPanel.add(new CustomSeparator(Color.BLACK, 3));objectPanel.add(new CustomSeparator(Color.BLACK, 3));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation((int) (screenSize.width * 0.8));
        splitPane.setEnabled(false);
        add(splitPane);
    }
    class CustomSeparator extends JSeparator{
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
    private class MyEventListner implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

        }
    }
    private class MyObjectListner implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

        }
    }
    private class MyLabelListner implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

        }
    }
    private class MyChoicesListner implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

        }
    }
    private class MySaveListner implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

        }
    }
}
