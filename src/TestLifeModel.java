import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestLifeModel {
    public static void main(String[] args) throws FileNotFoundException {
        LifeModel model = new LifeModel("life.txt");
        System.out.println(model.toModelString());
        GUIView view = new GUIView();
        Scanner console = new Scanner(System.in);
        int choice = 0;
        while (choice != 4) {
            System.out.println("Pick an Option below");
            System.out.println("1) Load a new file");
            System.out.println("2) Next Generation");
            System.out.println("3) Ten Generations");
            System.out.println("4) Quit");
            choice = console.nextInt();
            console.nextLine();
            if (choice == 1) {
                System.out.println("Enter the file name: ");
                LifeModel model2 = new LifeModel(console.nextLine());
                System.out.println(model2.toModelString());
                model = model2;
            }
            if (choice == 2) {
                model.nextLife();
                System.out.println(model.toModelString());
            }
            if (choice == 3) {
                for (int i = 0; i < 10; i++) {
                    model.nextLife();
                }
                System.out.println(model.toModelString());
            }
        }
    }
}
