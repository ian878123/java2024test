public abstract class Ob implements java.io.Serializable{
    String describe="";
    public abstract String getName() ;
    public void setDescribe(String s){
        describe=s;
    }
    public String getDescribe(){return describe;}
}
