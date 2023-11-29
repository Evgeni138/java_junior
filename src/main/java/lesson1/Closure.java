package lesson1;

public class Closure {
    public static void main(String[] args) {
        Runnable runnable = xPrinter();

        runnable.run();
    }

    private static Runnable xPrinter() {
        final int x = 5;
        Runnable runnable = () -> {
            System.out.println(x);
        };
        return runnable;
    }
}
