import java.io.*;
import java.util.ArrayList;

public class Loading {
    public static ArrayList<Event> Load(File file){
        File[] files = file.listFiles();
        ArrayList<Event> events=new ArrayList<>();
        for(File f:files){
            if(f.getName().endsWith(".ser")){
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(f));
                    Event newevent = (Event) objectInputStream.readObject();

                    events.add(newevent);
                    System.out.println("Loaded "+newevent.getName()+".ser");
                }catch (Exception e){
                    e.printStackTrace();
                    System.err.println("Error loading file");
                }
            }
        }
        System.out.println("Loaded "+events.size()+" files");
        return events;
    }
}
