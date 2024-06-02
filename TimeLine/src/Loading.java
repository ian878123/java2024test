import java.io.*;
import java.util.ArrayList;

public class Loading {
    public static ArrayList<Event> LoadEvent(File file){
        file = new File(file+"/Events");
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
    public static ArrayList<Label> LoadLabel(File file){
        file = new File(file+"/Labels");
        File[] files = file.listFiles();
        ArrayList<Label> labels=new ArrayList<>();
        for(File f:files){
            if(f.getName().endsWith(".ser")){
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(f));
                    Label newlabel = (Label) objectInputStream.readObject();

                    labels.add(newlabel);
                    System.out.println("Loaded "+newlabel.getName()+".ser");
                }catch (Exception e){
                    e.printStackTrace();
                    System.err.println("Error loading file");
                }
            }
        }
        System.out.println("Loaded "+labels.size()+" files");
        return labels;
    }
    public static ArrayList<Ob> LoadOb(File file){
        file = new File(file+"/Obs");
        File[] files = file.listFiles();
        ArrayList<Ob> obs=new ArrayList<>();
        for(File f:files){
            if(f.getName().endsWith(".ser")){
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(f));
                    Ob newob = (Ob) objectInputStream.readObject();

                    obs.add(newob);
                    System.out.println("Loaded "+newob.getName()+".ser");
                }catch (Exception e){
                    e.printStackTrace();
                    System.err.println("Error loading file");
                }
            }
        }
        System.out.println("Loaded "+obs.size()+" files");
        return obs;
    }
}
