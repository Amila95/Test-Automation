import org.junit.Test;

public class LoopClass {
    @Test
    public void TestLoop() {
//       // Student student1 = new Student("Kapila");
            Student student1 = new Student("Kapila",9,37,true,80,63);
//        Student student2 = new Student("Sunil",12,35,true,46,75);
            Student student3 = new Student("Kamala",14,20,false,50,65);
       Student student4 = new Student("Piyal",19,33,true,65,70);
        Student student5 = new Student("Sunila",17,25,false,71,77);
       // String t1 = "hello world"

        //checkgender(student3);
        //getGender(student1);
        getPassMark(student4);







    }
//    void test2(){
//        System.out.println("hello");
//    }
    void test4(String str,boolean bol, int q){
        if(bol) {
            System.out.println(str);
        }else{
            System.out.println(q);
        }
    }
//
//    void test3(String t1, int t2){
//        System.out.println(t1 +" "+t2);
//    }

//    void test1(String t1){
//
////        if(t1=="am"){
////            System.out.println("true");
////        }else{
////            System.out.println("false");
////        }
//    }

    void checkgender(Student student){
        if(student.isGender()){
            System.out.println(student.getName() +" is boy");
        }else{
            System.out.println(student.getName() + "is girl");
        }
    }

    void checkage(Student student){
        if(student.getAge() < 10) {
            System.out.println(student.getName() + "is baby");
            System.out.println(student.getName() + "is 10 or large");
        }

        else if(student.getAge() <15)
            System.out.println(student.getName() + "is 15 or large");

        else if(student.getAge() < 18){
            System.out.println(student.getName() + "is 18 or large");
        }
        else {
            System.out.println(student.getName() + "don't no");
        }

    }

    void getGender(Student student){
        if(student.isGender()){
            if(student.getAge()>18){
                System.out.println("Hi boy,you are qulified to license");
            }
        }else{
            if(student.getAge()>16){
                System.out.println("Hi girl,you are qulified to license");
            }else{
                System.out.println("Hi girl,you are not qulified to license");
            }
        }
        System.out.println("hdhhdhdd");

    }

    void checkWeightAndHeight(Student student){
        if(student.getWeight()>30){
            if(student.getHeight()>50){
                System.out.println("11111111");
            }
            else{
                System.out.println("22222222222");
            }
        }
        else if(student.getWeight()>35){
            if(student.getHeight()>60){
                System.out.println("333333333333");
            }
            else{
                System.out.println("444444444444444");
            }
        }else{
            if(student.getHeight()==50){
                System.out.println("555");
            }else{
                System.out.println("66666");
            }
        }

    }

    void checkGender(Student student){
        System.out.println(student.isGender()? student.getName()+" is boy" : student.getName()+" is girl");
    }

    void checkGenderAndMark(Student student){
        System.out.println(student.isGender()? student.getMarks()>50?student.getName()+" is good boy":student.getName()+" is bad boy":
                student.getMarks()>60? student.getName()+" is good girl": student.getName()+ " bad girl");
    }

    void getPassMark(Student student){
        switch (student.getMarks()){
            case 50:
                System.out.println("loww");

            case 65:
                System.out.println("midum");


            default:
                System.out.println("ok ");
                break;

        }
    }






}
