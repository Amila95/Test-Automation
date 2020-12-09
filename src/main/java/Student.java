public class Student {
    private int id;
    private String name;
    private int age;
    private int weight;
    private boolean gender;
    private int marks;
    private int height;

    Student(String name){
        this.name = name;
    }

    Student(String name, int age, int weight, boolean gender, int marks, int height){
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.marks = marks;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

