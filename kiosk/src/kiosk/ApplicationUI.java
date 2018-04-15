
package kiosk;

import java.util.ArrayList;
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
public class ApplicationUI 
{
    private final boolean debugging = false;
    
    // The menu that will be displayed. Please edit/alter the menu
    // to fit your application (i.e. replace "prodct" with "litterature"
    // etc.
     private final String[] menuItems =
    {"1. List all products",
     "2. Add new product",
     "3. Search for product",
     "4. Administrate books"       
    };
    
    //A register of the kiosk's books
    RegisterOfProducts products;
   

    /**
     * Creates an instance of the ApplicationUI User interface. 
     */
    public ApplicationUI() 
    {
        this.products = new RegisterOfProducts();
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
                        this.showSearchMenu();
                        break;
                    case 4:
                        this.administrateProducts();
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
        if (products.getProducts().iterator().hasNext())
        {
            products.getProducts().forEach(literature -> ViewFactory.getView(literature).display());
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
        System.out.println("3. Regularly published literature");
        switch (InputGetter.getIntInput())
        {
            //adds a single book
            
            case 1:
                products.registrateSingleBook(this.askForString("title"), this.askForString("author"), this.askForString("publisher"), this.askForString("publicationDate"), this.askForInteger("edition"));
                break;
            case 2:
                products.registrateBookInSeries(this.askForString("title"), this.askForString("seriesTitle"), this.askForString("author"), this.askForString("publisher"), this.askForString("publicationDate"));
                break;
            case 3:
                products.registrateRegularlyPublishedLiterature(this.askForString("title"), this.askForString("publisher"), this.askForInteger("Number of yearly releases"), this.askForString("subject"));
                break;
            default:
                tellUserThatChosenNumberIsInvalid();
                addNewProduct();
                break;
        }
        
    }
    
    /**
     * Search for a specified product
     */
    private void showSearchMenu()
    {
        System.out.println("Chose search criteria");
        System.out.println("1. Find a product by title and publisher");
        System.out.println("2. Find a product by title");
        System.out.println("3. Find all books by a publisher");
        switch(InputGetter.getIntInput())
        {
            case 1:
                search("nameAndPublisher");
                break;
            case 2:
                search("title");
                break;
            case 3:
                search("listPublisher");
                break;
            default:
                showSearchMenu();
                tellUserThatChosenNumberIsInvalid();
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
    private void search(String criteria)
    {
        printDebugging("findProduct() was called");
        System.out.println();
        switch (criteria)
        {
            case "nameAndPublisher":
                String title = askForString("title");
                String publisher = askForString("publisher");
                Literature foundProduct = products.searchProductBy(title, publisher);
                presentInfo(foundProduct);
                break;
            case "title":
                title = askForString("title");
                foundProduct = products.searchProductByTitle(title);
                presentInfo(foundProduct);
                break;
            case "listPublisher":
                publisher = askForString("publisher");
                ArrayList<Literature> productsByPublisher = products.searchProductByPublisher(publisher);
                presentInfo(productsByPublisher);                
                break;
        }
        this.waitForInput();
    }
    
    /**
     * Presents information about the product.
     * @param literature the litterature to present
     */
    private void presentInfo(Literature literature)
    {        
        if (literature != null)
        {                
            System.out.println("The product that was found: ");
            ViewFactory.getView(literature).display();
        }
        else
        {
            System.out.println("No such product was found!");
        }
    }
    
     /**
     * Presents information about the product.
     * @param literatures the litteratures to present
     */
    private void presentInfo(ArrayList<Literature> literatures)
    {
        if (literatures.isEmpty())
        {
            System.out.println("There are not registered any books from this publisher");
        }
        else 
        {            
            System.out.println("The products that was found: ");
            literatures.forEach(product -> ViewFactory.getView(product).display());
        }   
    }
    
    /**
     * Administrate products. Delete or add book to series
     * 
     */
    private void administrateProducts()
    {
        printDebugging("administrateBooks() was called");
        System.out.println("1. Delete product");
        System.out.println("2. Add book to series");
        
        switch (InputGetter.getIntInput())
        {
            case 1:
                String outputString = products.deleteProduct(products.searchProductBy(askForString("title"), askForString("publisher"))) ? 
                                    "Book was deleted" : "Couldn't find book to delete";
                System.out.println(outputString);
                waitForInput();
                break;
            case 2:
                if (!products.addBookToSeries(askForString("title"), askForString("publisher"), askForString("seriesTitle"), askForString("publicationDate")))
                {
                    System.out.println("Couldn't add the book to a series" + 
                                        ".\n Probable cause: title or publisher "
                                        + "doesn't correspond to a registered book.");
                }
                waitForInput();
                break; 
            default:
                tellUserThatChosenNumberIsInvalid();
                administrateProducts();
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
        InputGetter.getStringInput();
    }
    
    /**
     * Prints a message telling what to do when an invalid number is entered
     */
    private void tellUserThatChosenNumberIsInvalid()
    {
        System.out.println("You must chose one of the numbers listed under");
    }
    
    /**
     * Ask for title
     * @param infoToAskFor 
     * @return the input from the user
     */
    private String askForString(String infoToAskFor)
    {
        System.out.println(infoToAskFor + ":");
        return InputGetter.getStringInput();
    }
    
     /**
     * Asks for edition of the product
     * @return the input from the user
     */
    private int askForInteger(String infoToAskFor)
    {
        System.out.println(infoToAskFor + ": ");
        return InputGetter.getIntInput();
    }   
    
}   
