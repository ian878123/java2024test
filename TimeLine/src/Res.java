public class Res extends Ob{
    private String name;
    Res(String name){
        this.name=name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
}
