package Project;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        // Stock App Menu.
        int inputOption = 0;
        while (inputOption != 5) {

            System.out.println("*****Menu Options**** ");
            System.out.println("=====================");
            System.out.println("*********************");
            System.out.println("1. Add new product");
            System.out.println("2. Update a product");
            System.out.println("3. Show all products");
            System.out.println("4. Delete a product");
            System.out.println("5. Exit the program");
            System.out.println("*********************");
            Scanner input = new Scanner(System.in);
            System.out.print("Enter your option: ");

            inputOption = input.nextInt();
            System.out.println();

            switch (inputOption) {
                case 1:
                    System.out.println(ProjectStockApp.createProduct());
                    break;
                case 2:
                    System.out.println(ProjectStockApp.updateProduct());
                    break;
                case 3:
                    ProjectStockApp.readProducts();
                    break;
                case 4:
                    System.out.println(ProjectStockApp.deleteProduct());
                case 5:
                    break;
                default:
                    System.out.println("There no match.");
            }
        }
    }
}