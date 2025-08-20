import java.util.Scanner;

public class Jobe {
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("Hello! I'm Jobe.");
        System.out.println("What can I do for you?");
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            System.out.println(input);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Jobe jobe = new Jobe();
        jobe.run();
    }
}
