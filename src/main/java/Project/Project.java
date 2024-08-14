package Project;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Project {

    public static File file = new File("src\\stockProducts.json");
    public static ObjectMapper objectMapper = new ObjectMapper();
    public static JsonNode rootNode() throws IOException{

        // This function creates the objectMapper.
        return objectMapper.readTree(file);
    }
    public static void saveChanges(JsonNode rootNode) throws IOException{

        // This function saves alterations made in the stockProducts.json.
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, rootNode);
    }
    public static ArrayList<String> allProducts(JsonNode rootNode){

        // This function returns all the products present in the stockProducts.json.
        ArrayList<String> allProducts = new ArrayList<>();
        Iterator<String> iterator = rootNode.fieldNames();
        iterator.forEachRemaining(allProducts::add);
        return allProducts;
    }
    public static String createProduct() throws IOException {

        // This function creates a product into the stockProducts.json based on the user input.
        JsonNode rootNode = rootNode();
        Scanner userInput = new Scanner(System.in);
        System.out.print("Please, type in the name, price and quantity to add a new product to the stock: \n");
        String nameProduct = StringUtils.capitalize(userInput.next().toLowerCase());

        if (allProducts(rootNode).contains(nameProduct)) {
            return "Error! " + nameProduct + " is already present in the stock.";
        } else {
            Double priceProduct = Double.parseDouble(userInput.next());
            int quantityProduct = userInput.nextInt();
            ArrayNode priceQuantity = objectMapper.createArrayNode();
            priceQuantity.add(priceProduct);
            priceQuantity.add(quantityProduct);
            ((ObjectNode) rootNode).set(nameProduct, priceQuantity);
            saveChanges(rootNode);
            return "Success! " + nameProduct + " has been added to the stock.";
        }
    }
    public static void readProducts() throws IOException {

        // This function reads and displays all the products present in stockProducts.json.
        JsonNode rootNode = rootNode();
        System.out.println("Product" + "\t" + "Price" + "\t" + "Quantity");
        Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            String key = field.getKey();
            JsonNode item = field.getValue();
            System.out.println(key + "\t" + item.get(0).asText() + "\t" + item.get(1).asText());
        }
    }
    public static String updateProduct() throws IOException {

        // This function updates product present in the stockProducts file based on the user input.
        JsonNode rootNode = rootNode();
        Scanner userInput = new Scanner(System.in);
        System.out.print("Please, type in the name, price and quantity to update a product: \n");
        String nameProduct = StringUtils.capitalize(userInput.next().toLowerCase());

        if (allProducts(rootNode).contains(nameProduct)) {
            Double priceProduct = Double.parseDouble(userInput.next());
            int quantityProduct = userInput.nextInt();
            ArrayNode priceQuantity = objectMapper.createArrayNode();
            priceQuantity.add(priceProduct);
            priceQuantity.add(quantityProduct);
            ((ObjectNode) rootNode).set(nameProduct, priceQuantity);
            saveChanges(rootNode);
            return "Success! " + nameProduct + " has been updated.";
        } else {
            return "Error!" + nameProduct + " is not present in the stock.";
        }
    }
    public static String deleteProduct() throws IOException{

        // This function deletes a product present in the stockProducts based on the user input.
        JsonNode rootNode = rootNode();
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter the product you would like to delete: \n");
        String deleteProduct = StringUtils.capitalize(userInput.next().toLowerCase());
        if(allProducts(rootNode).contains(deleteProduct)) {
            ((ObjectNode) rootNode).remove(deleteProduct);
            saveChanges(rootNode);
            return "Success! " + deleteProduct + " has been deleted.";
        } else {
            return "Error! " + deleteProduct + " is not present in the stock.";
        }
    }
    public static void main(String[] args) throws IOException {

        System.out.println(createProduct());
        readProducts();
        System.out.println(updateProduct());
        System.out.println(deleteProduct());
    }
}