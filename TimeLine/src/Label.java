import java.util.ArrayList;

public class Label implements java.io.Serializable{
    private final String name;
    private final ArrayList<Ob> members=new ArrayList<>();
    Label(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void newMember(Ob member){
        for(Ob m:members){
            if(m.getName().equals(member.getName())){
                return;
            }
        }
        members.add(member);
    }

    public void deleteMember(Ob member){
        members.remove(member);
    }

    public ArrayList<Ob> getMembers(){
        return members;
    }
}
