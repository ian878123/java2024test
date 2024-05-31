import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class deleteGUI extends JFrame{
    public Button tmp;
    public GUI g;
    public deleteGUI(ArrayList<Event> events,GUI gui){
        super("Delete event");
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
        setLayout(new FlowLayout());
        JLabel deleteLabel =new JLabel("選擇其中一個事件刪除:");
        add(deleteLabel);
        for(int i=0;i<tags.size();i++){
            tmp=new Button(i,tags.get(i).getName(),gui);
            tmp.addActionListener(new TagListener());
            add(tmp);
        }
    }
    private class EventListener implements ActionListener {//所有關於存檔讀檔的事件監聽器
        @Override
        public void actionPerformed(ActionEvent e){
            Button clickedButton = (Button) e.getSource();
            int eventIndex = clickedButton.getNum();
            clickedButton.getGUI().deleteEvent(eventIndex);
        }
    }
    private class ObsListener implements ActionListener {//所有關於存檔讀檔的事件監聽器
        @Override
        public void actionPerformed(ActionEvent e){
            Button clickedButton = (Button) e.getSource();
            int eventIndex = clickedButton.getNum();
            clickedButton.getGUI().deleteObs(eventIndex);
        }
    }
    private class TagListener implements ActionListener {//所有關於存檔讀檔的事件監聽器
        @Override
        public void actionPerformed(ActionEvent e){
            Button clickedButton = (Button) e.getSource();
            int eventIndex = clickedButton.getNum();
            clickedButton.getGUI().deleteTag(eventIndex);
        }
    }
}
class Button extends JButton{
    private int num;
    private GUI gui;
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