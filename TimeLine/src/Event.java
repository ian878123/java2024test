import java.awt.Color;
import java.awt.Graphics;
public class Event {
    private String title;
    private Color color;
    private Label insidePeople;
    private Label insideRes;
    Event(String name){
        title=name;
        insidePeople =new Label(title);
        insideRes=new Label(title);
    }
    void addNewMember(People p){
        insidePeople.newMember(p);
    }
    void addNewMember(Res r){
        insideRes.newMember(r);
    }
}
