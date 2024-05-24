import java.util.ArrayList;

public class label {
    private String name;
    private ArrayList<People> members;
    private int num=0;
    label(String name){
        this.name=name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void newMember(People member){
        members.add(member);
        num++;
    }
    public ArrayList<People> getMembers(){
        return members;
    }
}
