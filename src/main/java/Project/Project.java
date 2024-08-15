package Project;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Project {

    public static File file = new File("src\\stockProducts.json");
    public static ObjectMapper objectMapper = new ObjectMapper();
    public static JsonNode rootNode() throws IOException{

        // This function creates the objectMapper.
        return objectMapper.readTree(file);
    }
    public static void saveChanges(JsonNode rootNode) throws IOException{

        // This function saves alterations made in the stockProducts.json.
        objectMapper.writerWithDefaultPrettyPrinter(). writeValue(file, rootNode);
    }
    public static ArrayList<String> allProducts() throws IOException{

        // This function returns all the products present in the stockProducts.json.
        JsonNode rootNode = rootNode();
        ArrayList<String> allProducts = new ArrayList<>();
        Iterator<String> iterator = rootNode.fieldNames();
        iterator.forEachRemaining(allProducts::add);
        return allProducts;
    }
    public static String testUserInput(String productName){

        // This function tests if the user input is valid.
        // Price should be greater than $0 and quantity greater or equals to 0.
        try {
            JsonNode rootNode = rootNode();
            Scanner userInput = new Scanner(System.in);
            System.out.print("Please, type in the price and quantity, separated by space: \n");
            Double productPrice = Double.parseDouble(userInput.next());
            int productQuantity = userInput.nextInt();
            if (productName.matches("[a-zA-Z]+") && productPrice > 0 && productQuantity >= 0){
                ArrayNode priceQuantity = objectMapper.createArrayNode();
                priceQuantity.add(productPrice);
                priceQuantity.add(productQuantity);
                ((ObjectNode) rootNode).set(productName, priceQuantity);
                saveChanges(rootNode);
                return "Success! " + productName + " has been added or update in the stock.";
                } else {
                    return "Error! Invalid name, price or quantity.";
                }
            } catch (Exception e) {
                return "Error! Invalid input.";
            }
        }
    public static String createProduct() throws IOException {

        // This function adds a new product into the stockProducts.json based on the user input.
        Scanner userInput = new Scanner(System.in);
        System.out.print("Please, type in the name to add a new product to the stock: \n");
        String productName = StringUtils.capitalize(userInput.next().toLowerCase());
        if (allProducts().contains(productName)) {
            return "Error! " + productName + " is already present in the stock.";
        } else {
            return testUserInput(productName);
        }
    }
    public static void readProducts() throws IOException {

        // This function reads and displays all the products present in stockProducts.json.
        JsonNode rootNode = rootNode();
        System.out.format("%-15s%-10s%-10s%n", "Product", "Price", "Quantity");
        Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            String key = field.getKey();
            JsonNode item = field.getValue();
            System.out.format("%-15s%-10s%-10s%n", key, item.get(0).asText(), item.get(1).asText());
        }
    }
    public static String updateProduct() throws IOException {

        // This function updates product's price and quantity present in the stockProducts file based on the user input.
        Scanner userInput = new Scanner(System.in);
        System.out.print("Please, type in the name to update a product: \n");
        String productName = StringUtils.capitalize(userInput.next().toLowerCase());
        if (allProducts().contains(productName)) {
            return testUserInput(productName);
        } else {
            return "Error!" + productName + " is not present in the stock.";
        }
    }
    public static String deleteProduct() throws IOException{

        // This function deletes a product present in the stockProducts based on the user input.
        JsonNode rootNode = rootNode();
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter the product you would like to delete: \n");
        String deleteProduct = StringUtils.capitalize(userInput.next().toLowerCase());
        if(allProducts().contains(deleteProduct)) {
            ((ObjectNode) rootNode).remove(deleteProduct);
            saveChanges(rootNode);
            return "Success! " + deleteProduct + " has been deleted.";
        } else {
            return "Error! " + deleteProduct + " is not present in the stock.";
        }
    }
    public static void main(String[] args) throws IOException {

        System.out.println("Menu options:\n");
        System.out.println("1. Add new product");
        System.out.println("2. Update a product");
        System.out.println("3. Show all products");
        System.out.println("4. Delete a product");
        System.out.println("5. Exit the program\n");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your option: ");
        String inputOption = input.next();
        System.out.println();
        switch (inputOption) {
            case "1":
                System.out.println(createProduct());
                break;
            case "2":
                System.out.println(updateProduct());
                break;
            case "3":
                readProducts();
                break;
            case "4":
                System.out.println(deleteProduct());
            case "5":
                break;
            default:
                System.out.println("There no match.");
        }
    }
}