public class TestManSuper extends TestMan {
    @Override
    void speak() {
        super.speak();
        System.out.println("say mama say mama");
    }

    @Override
    void run() {
        System.out.println("run fast");
    }
}
