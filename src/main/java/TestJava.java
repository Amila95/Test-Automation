import org.junit.Test;

public class TestJava {
    @Test
    public void checkBoxTest3() {
//        School mySchool = new School("Nalanda", "Colombo");
//        System.out.println("Name "+mySchool.getName());
//        System.out.println("pro "+ mySchool);
        TestMan man = new TestMan();
        man.speak();
        man.run();
        TestManSuper manSuper = new TestManSuper();
        manSuper.speak();
        manSuper.run();


//       TestMan Kamala  = new TestMan("Buddi",false);
//       TestMan  Kapila= new TestMan("Sudath",true,"fair",30);
//       TestMan Nimal = new TestMan("Nimal",true,Kapila,Kamala);

    }

    void test(){
//        System.out.println("hello");
//        TestMan buddi = new TestMan();
//        TestMan uthpali = new TestMan("Uthpali");
//        TestMan Nuwan = new TestMan("Nuwan",true,"fair",3);
//        TestMan NuwanWife  = new TestMan("Supuni",false);
//        TestMan NuwanBaby = new TestMan("Podi Nuwan", true, Nuwan, NuwanWife);
//        NuwanBaby.speak();
//
//        TestManSuper NuwanBabyPro = new TestManSuper();
//        NuwanBabyPro.speak();
//        NuwanBabyPro.run();
        //TestManSuper BuddhiBabyPro =

        TestMan dilum = new TestMan("dilum",true,"handsome",35);
        dilum.getFeedback();

        TestMan uthpali = new TestMan("uthpali",false,"perty",35);
        uthpali.getFeedback();
    }

    void test11(String name){
        System.out.println(name);
    }

    void test11(TestMan name){
        System.out.println(name);
    }



}


