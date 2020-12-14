import org.junit.Test;

public class Loop {
    @Test
    public void TestLoop() {
       // System.out.println(checkreturn());
        //break; continue; return;
       // int i= 5;
//        for(int i= 10;i>0;i--){
//            System.out.println("number "+ i);
//            //cone
//        }
//        for(;;){
//            System.out.println("infinitive loop");
//        }
        //for(int )
////////////////
//        for(int row=1;row<=5;row++){
//            for(int col=1;col<=row;col++){
//
//                if(col== 3){
//                    break;
//                }
//                System.out.println("row"+ row+ "col"+ col);
//
//            }
//            System.out.println("row"+ row);
//            System.out.println();//new line
//
//        }
//
//        int term=6;
//        for(int row=1;row<=6;row++){
//            for(int col=6;col>=row;col--){
//                System.out.println("row "+ row+ "col "+ col);
//            }
//            System.out.println();//new line
//        }
//
//        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
//        for(int i=0;i<cars.length;i++){
//            System.out.println("i value "+ i);
//            System.out.println("cars" + cars[i]+" super");
//        }
//       // https://www.geeksforgeeks.org/for-each-loop-in-java/
//        System.out.println("In for each ");
//        for (String i : cars) {
//            System.out.println("i value "+ i);
//        }
////////////22222
//        for(int i= 0;i<10;i++){
//
//
//            if(i == 5){
//                //System.out.println("number bus");
//                return;
//            }
//            System.out.println("number "+ i);
//
//        }
//        System.out.println("after break");

//        System.out.println("break");
        //System.out.println("return value "+ this.checkreturn());

        //do while loop
//        int i = 0;
//        do {
//            System.out.println("i value "+i);
//            //i++;
//        }
//        while (i < 0);

        //while
        int j=0;
        while (j < 10){
            if(j==5){
                //j++;
                System.out.println("j value "+j);
                continue;
            }
            System.out.println("j value "+j);

            j++;
        }
        System.out.println("break");

//        while (i < 5){
//            if(i==3){
//                i++;
//                continue;
//            }
//            System.out.println(i);
//            i++;
//        }



    }

//    int checkreturn(){
//        for(int i= 0;i<10;i++){
//
//
//            if(i == 5){
//                break;
//            }
//            return i;
//            //System.out.println("number "+ i);
//
//        }
//        //System.out.println("break");
//        return 0;
//
//    }
}
