
package kiosk;

import java.util.Scanner;

/**
 * Responsible for getting input from the user
 * @author amhat
 */
abstract public class InputGetter {
    public InputGetter()
    {        
    }    
    
    /**
     * Looks for a string input provided by the user
     * @return The input provided by the user
     */
    public static String getStringInput()
    {
        Scanner r = new Scanner(System.in);
        return r.nextLine();
    }
    
    /**
     * Looks for a integer input provided by the user
     * @return input or 1 as default 
     */
    public static int getIntInput()
    {
        Scanner r = new Scanner(System.in);
        return r.hasNextInt() ? r.nextInt() : 1;
    }
}
