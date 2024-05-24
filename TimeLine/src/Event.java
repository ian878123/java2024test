import java.awt.Color;
import java.awt.Graphics;
public class Event {
    private Color color;
    private final Label insidePeople;
    private final Label insideRes;
    Event(String name){
        insidePeople =new Label(name);
        insideRes=new Label(name);
    }

    void addNewMember(People p){
        insidePeople.newMember(p);
    }

    void addNewMember(Res r){
        insideRes.newMember(r);
    }

    Label getInsidePeople(){
        return insidePeople;
    }

    Label getInsideRes(){
        return insideRes;
    }
}
