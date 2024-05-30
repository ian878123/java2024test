import java.util.ArrayList;

public class Label {
    private final String name;
    private final ArrayList<Ob> members=new ArrayList<>();
    Label(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void newMember(Ob member){
        members.add(member);
    }

    public ArrayList<Ob> getMembers(){
        return members;
    }
}
