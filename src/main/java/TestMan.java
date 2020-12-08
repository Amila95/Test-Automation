public class TestMan {
    String name;
    Boolean gender;
    String skinColor;
    int weight;
    TestMan mother;
    TestMan Father;
    School school;

    TestMan(){
        System.out.println("God make man");
    }

    TestMan(String name){
        this.name = name;
        System.out.println("God make man. His/Her name is"+ this.name);
    }

//    createId(int age, String Address, String birthday){
//        String idno = this.name+birthday;
//    }

//    TestMan(String name){
////        this.name = name;
////        System.out.println("God make man. His/Her name is"+ this.name);
//    }

    TestMan(String name, boolean gender){
        this.name = name;
        this.gender = gender;
        if(gender) {
            System.out.println("God make man. His name is" + this.name);
        }
        else{
            System.out.println("God make woman. Her name is" + this.name);
        }
    }

    TestMan(String name, boolean gender, String skinColor, int weight){
        this.name = name;
        this.gender = gender;
        this.skinColor = skinColor;
        this.weight = weight;
        //Conditional Operator
        System.out.println("God make man."+ (this.gender? " His": " Her")+"name is " + this.name);

    }

    TestMan(String name, boolean gender, TestMan father, TestMan mother){
        this.name = name;
        this.gender = gender;
        this.Father = father;
        this.mother = mother;
        //Conditional Operator
        System.out.println("God make man."+ (this.gender? " His": " Her")+"name is" + this.name);
        System.out.println("Father name is "+ this.Father.gender);
        System.out.println("Mother name is "+ this.mother.name);

    }

    TestMan(String name, School school){
        this.name = name;
        this.school= school;
        //System.out.println("Mother name is "+ this.school.provence);

    }

    void  chnageName(String name){
        this.name = name;
    }

    void speak(){
        System.out.println("cry cry");
    }
    void speak(String language){
        System.out.println("speaking in "+ language);
    }

    void run(){
        System.out.println("run run");
    }
//    void run(int speed){
//
//    }

    boolean getGender(){
        return this.gender;
    }

    String getSkinColor(){
        return this.skinColor;
    }

    void getFeedback(){
        if(getGender() && getSkinColor() =="handsome"){
            System.out.println(this.name + " is handsome");
        }
        if(!getGender() && getSkinColor() =="beautiful"){
            System.out.println(this.name + " is beautiful");
        }
        if(!getGender() || getSkinColor() =="beautiful"){
            System.out.println(this.name + " is beautiful in or ");
        }

        switch (this.weight){
            case 20:
                System.out.println("loww");
                break;
            case 35:
                System.out.println("midum");
                break;

            default:
                System.out.println("ok ");
                break;

        }
    }



}

