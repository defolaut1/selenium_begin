public class Main {
    public static void main(String[] args) {
        System.out.println(fun(1, 2, 0));
    }

    public static int fun(int a, int b, int c) {
        try {
            return (a + b) / c;
        } finally {
            return 1;
        }
    }
}
