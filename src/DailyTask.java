import java.util.Scanner;

// Task Undo Stack - Undo Mechanism
class TaskStack {
    private String[] stack;
    private int top;
    private int capacity;

    public TaskStack(int size) {
        this.capacity = size;
        this.stack = new String[size];
        this.top = -1;
    }

    public void push(String task) {
        // Check if the stack is full
        if (top == capacity - 1) {
            System.out.println("Stack Overflow! Cannot add more completed tasks.");
            return;
        }
        stack[++top] = task;
        System.out.println("Task '" + task + "' marked as completed.");
    }

    public String pop() {
        // Check if the stack is empty
        if (top == -1) {
            System.out.println("Stack Underflow! No tasks to undo.");
            return null;
        }
        return stack[top--];
    }

    public String peek() {
        // Check if the stack is empty
        if (top == -1) {
            return "No completed tasks.";
        }
        return stack[top];
}}

// Dyamic Task with Linked List - Node 
class TaskNode {
    String task;
    TaskNode next;

    // Constructor
    public TaskNode(String task) {
        this.task = task;
        this.next = null;
}}

// Dynamic Task with Linked List
class TaskLinkedList {
    private TaskNode head;
    // Insert Task
    public void insertTask(String task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Task '" + task + "' added to the linked list.");
    }   

    // Remove Task
    public void deleteTask(int position) {
        if (head == null) {
            System.out.println("No tasks to remove.");
            return;
        }
        if (position == 0) {
            System.out.println("Task '" + head.task + "' removed from the linked list.");
            head = head.next;
            return;
        }

        TaskNode current = head;
        TaskNode previous = null;
        int count = 0;

        while (current != null && count < position) {
            previous = current;
            current = current.next;
            count++;
        }

        if (current != null) {
            System.out.println("Task '" + current.task + "' removed from the linked list.");
            previous.next = current.next;
        } else {
            System.out.println("Invalid position. No task removed.");
        }
    }

    // Display Task
    public void displayTasks() {
        if (head == null) {
            System.out.println("No dynamic tasks in the linked list.");
            return;
        }

        System.out.println("Dynamic tasks in the linked list:");
        TaskNode current = head;
        while (current != null) {
            System.out.println("- " + current.task);
            current = current.next;
        }
    }
}

// Main
public class DailyTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        String RED = "\u001b[31;1m"; // Red bold text
        String RESET = "\u001b[0m"; // Reset color
        // Task Array
        String[] taskArray = {"Check email", "Code", "Attend lecture", "Iron clothes", };
        // Task Undo Stack
        TaskStack completedTasks = new TaskStack(10);
        // Dynamic Task with Linked List
        TaskLinkedList taskList = new TaskLinkedList();

        while (true) {
            System.out.println("---------------------------");
            System.out.println("---- Task Manager Menu ----");
            System.out.println("---------------------------");
            System.out.println("\n1. View Tasks");
            System.out.println("2. Update Task");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Undo Last Completion");
            System.out.println("5. Add New Dynamic Task");
            System.out.println("6. Remove Dynamic Task");
            System.out.println("7. View Dynamic Tasks");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
            case 1:
                // Task Array - Display predefined tasks
                System.out.println("\nCurrent tasks in array:");
                // Display the predefined tasks
                for (int i = 0; i < taskArray.length; i++) {
                    System.out.println((i + 1) + ". " + taskArray[i]);
                }
            break;

            case 2:
                // Task Array - Update task
                System.out.print("Enter the task index to update: ");
                int taskNumber = scanner.nextInt(); 
                scanner.nextLine(); // Consume newline
                // Check if the index is valid and updates the task
                if (taskNumber >= 0 && taskNumber < taskArray.length) {
                    System.out.print("Enter the new task: ");
                    taskArray[taskNumber] = scanner.nextLine(); // Update the task in the array using user input scanner.nextLine();
                    System.out.println("Task updated successfully.");
                } else {
                    System.out.println(RED + "Invalid task number." + RESET);
                }
            break;

            case 3:
                // Task Stack - Mark task as completed - Push to Stack
                System.out.print("Enter the task to mark as completed: ");
                int completeIndex = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                // Check if the index is valid and then verifies it
                if (completeIndex >= 0 && completeIndex < taskArray.length) { // Check if the index is valid
                    String completedTask = taskArray[completeIndex];
                    completedTasks.push(completedTask);
                    System.out.println("Task '" + completedTask + "' marked as completed.");
                } else {
                    System.out.println(RED + "Invalid task number."+ RESET);
                }
            break;

            case 4:
                // Task Stack - Undo last completion - Pop from Stack
                String undoneTask = completedTasks.pop();
                // Check if there is a task to undo
                if (undoneTask != null) {
                    System.out.println("Task '" + undoneTask + "' undone.");
                }
            break;

            case 5:
                // Dynamic Task with Linked List - Add new task
                System.out.print("Enter the task to add: ");
                String newTask = scanner.nextLine();
                // Add dynamic task into linked list
                taskList.insertTask(newTask);
            break;

            case 6:
                // Dynamic Task with Linked List - Remove task
                System.out.print("Enter the position of the task to remove: ");
                int removePosition = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                // Remove dynamic task from linked list
                taskList.deleteTask(removePosition);
            break;

            case 7:
                // View dynamic task(s) in linked list
                taskList.displayTasks();            
            break;

            case 8: 
                // Exit the program
                System.out.println("Exiting the program.");
                System.exit(0);
                scanner.close();
            break;

                default:
                System.out.println(RED + "Invalid choice. Please try again." + RESET);
        }
    }
}}
