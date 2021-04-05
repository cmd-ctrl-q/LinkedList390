import java.util.Scanner;

public class Covid19 {

    // global var
    static LinkedList llist;

    // creates and populates list with file content
    static void populateList() {

        // run FileReader, read from file, and insert into llist. 
        FileReader fr = new FileReader("Names.txt");

        // store into llist
        llist = fr.insertNamesIntoList();
    }

    static void userConsole() {

        int option;
        boolean using = true;
        String name;
        char letter;
        Scanner scanner = new Scanner(System.in);

        while(using) {

            System.out.println("\n\nOptions:\n\t1. Add name\n\t2. Delete name.\n\t3. Get size (all)\n\t4. Get size (single).\n\t5. Print names (all).\n\t6. Print names (single).\n\t7. Print map.\n\t8. Finish");
            option = scanner.nextInt();

            switch(option) {
                case 1:
                    System.out.print("Add name to list: ");
                    name = scanner.next();
                    llist.insertInOrder(name);
                    break;

                case 2:
                    System.out.println("Delete name in list: ");
                    name = scanner.next();
                    llist.deleteAt(name);
                    break;

                case 3:
                    System.out.println("Found " + llist.getSize() + " names in the list.");
                    break;

                case 4: 
                    System.out.print("Find number of names with the letter: ");
                    letter = scanner.next().charAt(0);
                    System.out.println("Found " + llist.countNamesWithTheLetter(letter) + " names beginning with the letter " + letter);
                    break;

                case 5:  // get size of list
                    llist.printList();
                    break;

                case 6: // get size of specific letter
                    System.out.print("Print names beginning with the letter: ");
                    letter = scanner.next().charAt(0);
                    llist.printNamesWithTheLetter(letter);
                    break;

                case 7:
                    llist.printMap();
                    break;

                case 8:
                    using = false;
                    System.out.println("Good bye");
                    break;

                default:
                    System.out.println("404");
                    continue;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
 
        populateList();

        userConsole();

    }
}