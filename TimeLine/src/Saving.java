import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Saving {
    public static void save(ArrayList<Event> events, File file){
        file.mkdir();
        for(Event e:events){
            try{
                FileOutputStream fileout = new FileOutputStream(file+"/"+e.getName()+".ser");
                ObjectOutputStream out = new ObjectOutputStream(fileout);

                out.writeObject(e);

                out.close();
                System.out.println("Saved "+e.getName()+".ser");
            }catch (IOException i){
                i.printStackTrace();
                System.err.println("Error saving file");
            }
        }
    }
}
