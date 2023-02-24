import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TodoList {
    private ArrayList<String> items;
    private Scanner scanner;

    private static final int ADD_ITEM_OPTION = 1;
    private static final int REMOVE_ITEM_OPTION = 2;
    private static final int DISPLAY_ITEMS_OPTION = 3;
    private static final int EXIT_OPTION = 4;

    public TodoList() {
        items = new ArrayList<String>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome to your To Do List! Let's be Productive!");

        while (true) {
            displayMenu();

            int choice = readIntInput();

            switch (choice) {
                case ADD_ITEM_OPTION:
                    addItem();
                    break;

                case REMOVE_ITEM_OPTION:
                    removeItem();
                    break;

                case DISPLAY_ITEMS_OPTION:
                    displayItems();
                    break;

                case EXIT_OPTION:
                    System.out.println("Goodbye, see you next time!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println(ADD_ITEM_OPTION + ". Add item");
        System.out.println(REMOVE_ITEM_OPTION + ". Remove item");
        System.out.println(DISPLAY_ITEMS_OPTION + ". Display items");
        System.out.println(EXIT_OPTION + ". Exit");
    }

    private void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Your to-do list is empty.");
        } else {
            System.out.println("Your to-do list:");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i));
            }
        }
    }
    

    private void addItem() {
        System.out.println("Enter the item you want to add:");
        String itemDescription = scanner.nextLine().trim();
        if (itemDescription.isEmpty()) {
            System.out.println("Error: Item description cannot be empty.");
            return;
        }
    
        String priorityLevel = "";
        while (!priorityLevel.equals("low") && !priorityLevel.equals("medium") && !priorityLevel.equals("high")) {
            System.out.println("Enter the priority level of the item (low, medium, or high):");
            priorityLevel = scanner.nextLine().trim();
            if (!priorityLevel.equals("low") && !priorityLevel.equals("medium") && !priorityLevel.equals("high")) {
                System.out.println("Error: Invalid priority level.");
            }
        }
    
        String dueDate = "";
        while (!dueDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Enter the due date of the item (YYYY-MM-DD format):");
            dueDate = scanner.nextLine().trim();
            if (!dueDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("Error: Invalid due date format.");
            }
        }
    
        String item = dueDate + " - " + priorityLevel + ": " + itemDescription;
        items.add(item);
        System.out.println("Item added successfully.");
    }
    
    private int readIntInput() {
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number:");
            scanner.nextLine(); // consume the invalid input
            choice = readIntInput();
        }
        return choice;
    }

    private void removeItem() {
        if (items == null || items.isEmpty()) {
            System.out.println("Your to-do list is empty.");
            return;
        }
    
        System.out.println("Enter the index of the item you want to remove:");
        int index;
        try {
            index = scanner.nextInt();
            scanner.nextLine(); // consume the newline character
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid index:");
            scanner.nextLine(); // consume the invalid input
            return;
        }
    
        if (index < 1 || index > items.size()) {
            System.out.println("Invalid index. Please enter a valid index:");
            return;
        }
    
        String removedItem = items.remove(index - 1);
        System.out.println("Item '" + removedItem + "' removed successfully.");
    }

    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        todoList.run();
    }
}
