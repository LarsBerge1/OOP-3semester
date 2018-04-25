
package kiosk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Provides file-handling methods for the literature register
 * This includes methods to write and read the collection of literatures
 * to a file
 * 
 */
public class LiteratureRegisterFileHandler {
    private LiteratureRegister litReg;
    private String fileName;
    private Path file;
    public LiteratureRegisterFileHandler(LiteratureRegister register, String fileName)
    {
        litReg = register;
        this.fileName = fileName;
        findFile(fileName);
    }
    
    /**
     * Finds the file to manipulate or read from
     * @param fileName the name of the file to manipulate
     */
    public void findFile(String fileName)
    {
        file = Paths.get(fileName).toAbsolutePath();
    }
    
    /**
     * Saves all the literatures to the file
     * @throws java.io.IOException
     */
    public void saveAllToFile() throws IOException
    {
        ObjectOutputStream os = new ObjectOutputStream(
                                    new FileOutputStream(file.toString()));
        os.writeObject(litReg);
        os.close();
    }
    
    /**
     * Reads a literatureRegister from a file
     * The method is mainly written by  David J. Barnes and Michael Kölling. And
     * is found in the AdressBook-io project(chapter 14) in the 
     * AddressBookFileHandler-class.
     * @return the literatureRegister 
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws URISyntaxException 
     */
    public LiteratureRegister readFromFile() 
            throws FileNotFoundException, ClassNotFoundException, IOException, URISyntaxException 
    {
        URL resource = getClass().getResource(fileName);
        if(resource == null) {
            throw new FileNotFoundException(fileName);
        }
        File source = new File(resource.toURI());
        ObjectInputStream is = new ObjectInputStream(
                               new FileInputStream(source));
        LiteratureRegister savedRegister = (LiteratureRegister) is.readObject();
        is.close();
        return savedRegister;        
    }    
}

