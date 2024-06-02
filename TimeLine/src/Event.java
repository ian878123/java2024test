import java.awt.Color;
import java.util.ArrayList;

public class Event implements java.io.Serializable{
    private Color color;
    private ArrayList<People> peoples=new ArrayList<>();
    private ArrayList<Res> res=new ArrayList<>();
    private String name;
    private int x;
    private int y;
    private String describe="";
    Event(String n, Color c,int xx,int yy){
        name=n;
        color=c;
        x=xx;
        y=yy;
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

    public void setName(String s){name=s;}

    public void setDrawingColor(Color c) {
        color=c;
    }

    public Color getColor(){ return color;}

    public void setX(int xx){x=xx;}

    public void setY(int yy){y=yy;}

    public int getX(){return x;}

    public int getY(){return y;}

    public void setDescribe(String s){
        describe=s;
    }
    public String getDescribe(){return describe;}
}
