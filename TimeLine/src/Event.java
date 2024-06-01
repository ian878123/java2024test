import java.awt.Color;
import java.util.ArrayList;

public class Event implements java.io.Serializable{
    private Color color;
    private ArrayList<People> peoples=new ArrayList<>();
    private ArrayList<Res> res=new ArrayList<>();
    private String name;
    Event(String n, Color c){
        name=n;
        color=c;
    }

    public void addNewMember(People p){
        peoples.add(p);
    }

    public void addNewMember(Res r){
        res.add(r);
    }

    public ArrayList<Res> getRes(){return res;}

    public ArrayList<People> getPeoples(){return peoples;}

    public String getName(){return name;}

    public void setDrawingColor(Color c) {
        color=c;
    }

    public Color getColor(){ return color;}
}
