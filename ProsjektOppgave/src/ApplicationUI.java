import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Makes up the user interface (text based) of the application.
 * Responsible for all user interaction, like displaying the menu
 * and receiving input from the user.
 *
 * @author asty
 * @version 1.0
 */

public class ApplicationUI {

    private final boolean debugging = true;

    // The menu tha will be displayed. Please edit/alter the menu
    // to fit your application (i.e. replace "prodct" with "litterature"
    // etc.
    private String[] menuItems =
            {"1. List all products",
                    "2. Add new product",
                    "3. Search for product",
                    "4. Administrate books"
            };

    //A register of the kiosk's books
    RegisterOfBooks bookReg;


    /**
     * Creates an instance of the ApplicationUI User interface.
     */
    public ApplicationUI()
    {
        this.bookReg = new RegisterOfBooks();
    }

    /**
     * Starts the application by showing the menu and retrieving input from the
     * user.
     */
    public void start()
    {
        this.init();

        boolean quit = false;

        while (!quit)
        {
            try
            {
                int menuSelection = this.showMenu();
                switch (menuSelection)
                {
                    case 1:
                        this.listAllProducts();
                        break;

                    case 2:
                        this.addNewProduct();
                        break;

                    case 3:
                        this.search();
                        break;
                    case 4:
                        this.administrateBooks();
                        break;

                    case 5:
                        System.out.println("\nThank you for using Application v0.1. Bye!\n");
                        quit = true;
                        break;

                    default:
                }
            }
            catch (InputMismatchException ime)
            {
                System.out.println("\nERROR: Please provide a number between 1 and " + this.menuItems.length + "..\n");
            }
        }

    }

    /**
     * Displays the menu to the user, and waits for the users input. The user is
     * expected to input an integer between 1 and the max number of menu items.
     * If the user inputs anything else, an InputMismatchException is thrown.
     * The method returns the valid input from the user.
     *
     * @return the menu number (between 1 and max menu item number) provided by the user.
     * @throws InputMismatchException if user enters an invalid number/menu choice
     */
    private int showMenu() throws InputMismatchException
    {
        System.out.println("\n**** Application v0.1 ****\n");
        // Display the menu
        for ( String menuItem : menuItems )
        {
            System.out.println(menuItem);
        }
        int maxMenuItemNumber = menuItems.length + 1;
        // Add the "Exit"-choice to the menu
        System.out.println(maxMenuItemNumber + ". Exit\n");
        System.out.println("Please choose menu item (1-" + maxMenuItemNumber + "): ");
        // Read input from user
        Scanner reader = new Scanner(System.in);
        int menuSelection = reader.nextInt();
        if ((menuSelection < 1) || (menuSelection > maxMenuItemNumber))
        {
            throw new InputMismatchException();
        }
        return menuSelection;
    }

    // ------ The methods below this line are "helper"-methods, used from the menu ----
    // ------ All these methods are made privat, since they are only used by the menu ---

    /**
     * Initializes the application.
     * Typically you would create the LiteratureRegistrer-instance here
     */
    private void init()
    {
        printDebugging("init() was called");
    }

    /**
     * Lists all the products/literature in the register
     */
    private void listAllProducts()
    {
        printDebugging("listAllProducts() was called");
        if (!bookReg.getListOfBooks().isEmpty())
        {
            bookReg.getListOfBooks().forEach(book -> System.out.println(book.getTitle()));
        }
        else
        {
            System.out.println("No product was found");
        }
        waitForInput();
    }


    /**
     * Add a new product/literature to the register.
     * In this method you have to add code to ask the
     * user for the necessary information you need to
     * create an instance of the product, which you
     * then send as a parameter to the addNewspaper()-
     * method of the register.
     * Remember to also handle invalid input from the
     * user!!
     */
    private void addNewProduct()
    {
        printDebugging("addNewProduct was called");
        System.out.println("Chose which product to add:");
        System.out.println("1. Single book");
        System.out.println("2. Book in series");
        System.out.println("addNewProduct() was called");
        switch (getIntInput())
        {
            //adds a single book

            case 1:
                bookReg.registrateSingleBook(this.askFor("title"), this.askFor("author"), this.askFor("publisher"), this.askFor("publicationDate"), this.askForEdition());
                break;
            case 2:
                bookReg.registrateBookInSeries(this.askFor("title"), this.askFor("seriesTitle"), this.askFor("author"), this.askFor("publisher"), this.askFor("publicationDate"));
                break;
            default:
                outOfBound();
                addNewProduct();
                break;
        }

    }

    /**
     * Search for a specified product
     */
    private void search()
    {
        System.out.println("Chose wich product to search");
        System.out.println("1.Book");
        switch (this.getIntInput())
        {
            case 1:
                this.searchBook();
                break;
            default:
                outOfBound();
                search();
                break;
        }
    }

    /**
     * Provides different methods to search for a book
     */
    private void searchBook()
    {
        System.out.println("Chose search criteria");
        System.out.println("1. Find a product by title and publisher");
        System.out.println("2. Find a product by title");
        System.out.println("3. Find all books by a publisher");
        switch(this.getIntInput())
        {
            case 1:
                this.findProduct("nameAndPublisher");
                break;
            case 2:
                this.findProduct("title");
                break;
            case 3:
                this.findProduct("listPublisher");
                break;
            default:
                this.searchBook();
                outOfBound();
                break;
        }

    }
    /**
     * Find and display a product based om name (title) and publisher.
     * As with the addNewProduct()-method, you have to
     * ask the user for the string (name/title/publisher)
     * to search for, and then use this string as input-
     * parameter to the method in the register-object.
     * Then, upon return from the register, you need
     * to print the details of the found item.
     * @param criteria the search criteria.
     */
    private void findProduct(String criteria)
    {
        printDebugging("findProduct() was called");
        System.out.println();
        switch (criteria)
        {
            case "nameAndPublisher":
                String title = askFor("title");
                String publisher = askFor("publisher");
                if (bookReg.searchBookBy(title, publisher) != null)
                {
                    System.out.println("The book that was found: ");
                    System.out.println(bookReg.searchBookBy(title, publisher).getTitle());
                }
                else
                {
                    System.out.println("No such book was found!");
                }
                break;
            case "title":
                title = askFor("title");
                if (bookReg.searchBookByTitle(title) != null)
                {
                    System.out.println("The book that was found: ");
                    System.out.println(bookReg.searchBookByTitle(title).getTitle());
                }
                else
                {
                    System.out.println("No Such book was found!");
                }
                break;
            case "listPublisher":
                publisher = askFor("publisher");
                if (!bookReg.searchBookByPublisher(publisher).isEmpty())
                {
                    System.out.println("The books that was found: ");
                    bookReg.searchBookByPublisher(publisher).forEach(book ->
                            System.out.println(book.getTitle()));
                }
                else
                {
                    System.out.println("There are not registered any books from this publisher");
                }
                break;
        }
        this.waitForInput();
    }

    /**
     * Administrate books. Delete or add book to series
     *
     */
    private void administrateBooks()
    {
        printDebugging("administrateBooks() was called");
        System.out.println("1. Delete book");
        System.out.println("2. add book to series");

        switch (getIntInput())
        {
            case 1:
                String outPutString = bookReg.deleteBook(
                        bookReg.searchBookBy(askFor("title"), askFor("publisher"))) ?
                        "Book was deleted" : "Couldn't find book to delete";
                System.out.println(outPutString);
                waitForInput();
                break;
            case 2:
                if (!bookReg.addBookToSeries(askFor("title"), askFor("publisher"), askFor("seriesTitle"), askFor("publicationDate")))
                {
                    System.out.println("Couldn't add the book to a series" +
                            ".\n Probable cause: title or publisher "
                            + "doesn't correspond to a registered book.");
                }
                waitForInput();
                break;
            default:
                outOfBound();
                administrateBooks();
                break;

        }
    }

    /**
     * Prints debugging information
     * @param info The debugging information to print
     */
    private void printDebugging(String info)
    {
        if (debugging)
        {
            System.out.println(info);
        }
    }

    /**
     * waits for input before continuing
     */
    private void waitForInput()
    {
        System.out.println("Press enter to continue");
        getStringInput();
    }

    /**
     * Looks for a string input provided by the user
     * @return The input provided by the user
     */
    private String getStringInput()
    {
        Scanner r = new Scanner(System.in);
        return r.nextLine();
    }

    /**
     * Looks for a integer input provided by the user
     * @return input or 1 as default
     */
    private int getIntInput()
    {
        Scanner r = new Scanner(System.in);
        return r.hasNextInt() ? r.nextInt() : 1;
    }

    /**
     * Prints a message telling what to do when an invalid number is entered
     */
    private void outOfBound()
    {
        System.out.println("You must chose one of the numbers listed under");
    }

    /**
     * Ask for title
     * @param infoToAskFor
     * @return the input from the user
     */
    private String askFor(String infoToAskFor)
    {
        switch (infoToAskFor)
        {
            case "title":
                System.out.println("Title:");
                break;

            case "author":
                System.out.println("Author:");
                break;

            case "publisher":
                System.out.println("Publisher:");
                break;

            case "publicationDate":
                System.out.println("Publication Date: ");
                break;

            case "seriesTitle":
                System.out.println("Series Title");
                break;

            case "edition":
                System.out.println("edition");
                break;
        }
        return getStringInput();
    }
    /**
     * Asks for edition of the product
     * @return the input from the user
     */
    private int askForEdition()
    {
        System.out.println("Edition:");
        return getIntInput();
    }


}
