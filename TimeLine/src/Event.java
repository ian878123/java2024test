import java.awt.Color;
import java.awt.Graphics;
public class Event {
    private Color color;
    private final Label insidePeople;
    private final Label insideRes;
    private String name;
    Event(String n, Color c){
        name=n;
        insidePeople=new Label(name);
        insideRes=new Label(name);
        color=c;
    }

    public void addNewMember(People p){
        insidePeople.newMember(p);
    }

    public void addNewMember(Res r){
        insideRes.newMember(r);
    }

    public Label getInsidePeople(){
        return insidePeople;
    }

    public Label getInsideRes(){
        return insideRes;
    }

    public String getName(){return name;}

    public void setDrawingColor(Color c) {
        color=c;
    }

    public Color getColor(){ return color;}
}
