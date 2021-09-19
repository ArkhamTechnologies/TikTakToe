public class main {

    public static void main(String[] args) {

        int result = 0;

        Board b = new Board();

        System.out.println(b + " " + result);

        result =b.add(0, 0);

        System.out.println(b + " " + result);

        result =b.add(0, 1);

        System.out.println(b + " " + result);

        result =b.add(1, 0);

        System.out.println(b + " " + result);

        result =b.add(0, 2);

        System.out.println(b + " " + result);

        result = b.add(2, 0);

        System.out.println(b + " " + result);
    }
}
