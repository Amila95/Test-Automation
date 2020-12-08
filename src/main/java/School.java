public class School {
    private  String  id;
    private String Name;
    private String provence;

    School(String name, String provence){
        this.Name = name;
        this.provence = provence;
    }

    public String getName(){
        return this.Name;
    }
}
