import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class deleteGUI extends JFrame{
    public Button tmp;
    public ArrayList<Event> EVENT;
    public ArrayList<Label> TAG;
    public ArrayList<Ob> OB;
    public deleteGUI(ArrayList<Event> events,GUI gui){
        super("Delete event");
        EVENT=events;
        setLayout(new FlowLayout());
        JLabel deleteLabel =new JLabel("選擇其中一個事件刪除:");
        add(deleteLabel);
        for(int i=0;i<events.size();i++){
            tmp=new Button(i,events.get(i).getName(),gui);
            tmp.addActionListener(new EventListener());
            add(tmp);
        }
    }
    public deleteGUI(ArrayList<Ob> obs, int index,GUI gui){
        super("Delete object");
        setUndecorated(true);
        OB=obs;
        setLayout(new FlowLayout());
        JLabel deleteLabel =new JLabel("選擇其中一個物件刪除:");
        add(deleteLabel);
        for(int i=0;i<obs.size();i++){
            tmp=new Button(i,obs.get(i).getName(),gui);
            tmp.addActionListener(new ObsListener());
            add(tmp);
        }
    }
    public deleteGUI(ArrayList<Label> tags, String s,GUI gui){
        super("Delete tag");
        setUndecorated(true);
        TAG=tags;
        setLayout(new FlowLayout());
        JLabel deleteLabel =new JLabel("選擇其中一個標籤刪除:");
        add(deleteLabel);
        for(int i=0;i<tags.size();i++){
            tmp=new Button(i,tags.get(i).getName(),gui);
            tmp.addActionListener(new TagListener());
            add(tmp);
        }
    }
    private class EventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            Button clickedButton = (Button) e.getSource();
            int eventIndex = clickedButton.getNum();
            clickedButton.getGUI().deleteEvent(EVENT.get(eventIndex));
            dispose();
        }
    }

    private class ObsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            Button clickedButton = (Button) e.getSource();
            int eventIndex = clickedButton.getNum();
            clickedButton.getGUI().deleteObs(OB.get(eventIndex));
            dispose();
        }
    }
    private class TagListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            Button clickedButton = (Button) e.getSource();
            int eventIndex = clickedButton.getNum();
            clickedButton.getGUI().deleteTag(TAG.get(eventIndex));
            dispose();
        }
    }
}
class Button extends JButton{
    private final int num;
    private final GUI gui;
    Button(int i,String n, GUI g){
        super(n);
        num=i;
        gui=g;
    }
    public int getNum(){
        return num;
    }
    public GUI getGUI(){
        return gui;
    }
}