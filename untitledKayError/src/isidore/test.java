package isidore;

import java.util.ArrayList;
import java.util.Scanner;


//GETTERS AND SETTERS
class OrderItem {
    private String itemName;
    private double itemPrice;

    public OrderItem(String name, double price) {
        itemName = name;
        itemPrice = price;
    }

    public String getName() {
        return itemName;
    }

    public double getPrice() {
        return itemPrice;
    }
}


class Order {
    private static ArrayList<OrderItem> items = new ArrayList<>();
    public static  Scanner scanner = new Scanner(System.in);
    // METHODS
    public void addItem(OrderItem item) {
        items.add(item);
    }

    // METHODS
    public void deleteItem(int index) {
        if (index >= 0 && index < items.size()) {
            OrderItem removedItem = items.remove(index);
            System.out.println(removedItem.getName() + " has been removed from the order.");
        } else {
            System.out.println("Invalid order item index.");
        }
    }


    // METHODS
    public static void viewOrder1() {
        System.out.println("Order Items:");
        for (int i = 0; i < items.size(); i++) {
            OrderItem item = items.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - $" + item.getPrice());
        }
    }


    //METHODS
    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getPrice();
        }
        return total;
    }


    public void displayMenu() {

        System.out.println("Menu:");
        System.out.println("1. Add item to order");
        System.out.println("2. View order");
        System.out.println("3. Calculate total");
        System.out.println("4. Delete Order");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        processChoice(choice);
    }

    public void processChoice(int choice) {
        switch (choice) {
            case 1:
                addItemToOrder();
                break;
            case 2:
                viewOrder1();
                break;
            case 3:
                CalculateTotal();
                break;
            case 4:
                deleteItemFromOrder();
                break;

            case 5:
                exitSystem();
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void addItemToOrder() {
        Order order = new Order();
        System.out.print("Enter item name: ");
        Scanner scanner = new Scanner(System.in);
        String itemName = scanner.next();
        System.out.print("Enter item price: ");
        double itemPrice = scanner.nextDouble();
        OrderItem item = new OrderItem(itemName, itemPrice);
        order.addItem(item);
        System.out.println(itemName + " has been added to the order.");

        order.displayMenu();

    }


    private static void viewOrder() {
        Order order = new Order();
        order.viewOrder();

        order.displayMenu();
    }


    private static void deleteItemFromOrder() {
        Order order = new Order();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the index of the item to delete: ");
        int itemIndex = scanner.nextInt();
        order.deleteItem(itemIndex - 1); // Adjust for 0-based index

        order.displayMenu();
    }


    private static double CalculateTotal() {
        Order order = new Order();
        double total = order.CalculateTotal();
        System.out.println("Total Price: $" + total);
        return total;
    }


    private static void exitSystem() {
        System.out.println("Thank you for using the ordering system. Goodbye!");
        System.exit(0);
    }

    private static void login() {

    }
}
public class test {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "12345";




    public static void main(String[] args){
        Order order = new Order();
//            order.displayMenu();


            Scanner scanner = new Scanner(System.in);
            //boolean loggedIn = false;
                try {
                    System.out.print("Enter admin username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter admin password: ");
                    String password = scanner.nextLine();
                    if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                        System.out.println("Admin login successful. Welcome, " + ADMIN_USERNAME + "!");
                       // loggedIn = true;
                        order.displayMenu();
                    } else {
                        System.out.println("Invalid admin credentials. Please try again.");
                        // loggedIn=false;
                    }

                } catch (Exception e) {
                    System.out.println("Invalid Choice");
                }

    }
}
