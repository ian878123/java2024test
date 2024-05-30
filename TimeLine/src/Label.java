import java.util.ArrayList;

public class Label {
    private String name;
    private ArrayList<Ob> members=new ArrayList<>();
    private int num=0;
    Label(String name){
        this.name=name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void newMember(Ob member){
        members.add(member);
        num++;
    }

    public ArrayList<Ob> getMembers(){
        return members;
    }
}
