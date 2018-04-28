
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
    private LiteratureRegister register;
    private String fileName;
    private File file;
    private String filePath;
    public LiteratureRegisterFileHandler(LiteratureRegister register, File file)
    {
        this.register = register;
        this.file = file;
        setPath(file);
    }
    
    /**
     * 
     */
    private void setPath(File f){
        this.filePath = f.getAbsolutePath();
    }
    
    /**
     * Saves all the literatures to the file
     * @throws java.io.IOException
     */
    public void saveAllToFile() throws IOException
    {
        ObjectOutputStream os = new ObjectOutputStream(
                                    new FileOutputStream(filePath));
        os.writeObject(register);
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
    public LiteratureRegister readFromFileOld() 
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
    
    public LiteratureRegister readSavedFile() throws IOException, ClassNotFoundException
    {
        LiteratureRegister lr;
        FileInputStream fIS = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fIS);
        //lr = (LiteratureRegister) 
        lr = (LiteratureRegister) ois.readObject();
        
        ois.close();
        
        return lr;
            
    }
    
    
    
    
    
    /**
     * Finds the file to manipulate or read from
     * @param fileName the name of the file to manipulate
     
    public void findFile(String fileName)
    {
        file = Paths.get(fileName).toAbsolutePath();
    }
    */
}

