import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Saving {
    public static void SaveEvent(ArrayList<Event> events, File file){
        file = new File(file+"/Events");
        System.out.println(file);
        file.mkdirs();
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
    public static void SaveLable(ArrayList<Label> labels, File file){
        file = new File(file+"/Labels");
        System.out.println(file);
        file.mkdirs();
        for(Label e:labels){
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
    public static void SaveOb(ArrayList<Ob> obs, File file){
        file = new File(file+"/Obs");
        System.out.println(file);
        file.mkdirs();
        for(Ob e:obs){
            if(e instanceof People){
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
            else if(e instanceof Res){
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


}
