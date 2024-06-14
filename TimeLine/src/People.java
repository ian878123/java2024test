import java.util.ArrayList;

public class People extends Ob{
    private final String name;
    private ArrayList<Event> eventsIn=new ArrayList<>();
    private ArrayList<Label> tagsIn=new ArrayList<>();
    People(String name){
        this.name=name;
    }
    public void addEvent(Event e){
        for(Event ev:eventsIn){
            if(ev.getName().equals(e.getName())){
                return;
            }
        }
        eventsIn.add(e);
    }
    public void addTag(Label tag){
        for(Label t:tagsIn){
            if(t.getName().equals(tag.getName())){
                return;
            }
        }
        tagsIn.add(tag);
    }
    public void deleteRelationship(Event e){
        eventsIn.remove(e);
    }
    public void deleteRelationship(Label t){
        tagsIn.remove(t);
    }
    public ArrayList<Event> getEventsIn(){return eventsIn;}
    public ArrayList<Label> getTagsIn(){return tagsIn;}
    public String getName() {
        return name;
    }
}
