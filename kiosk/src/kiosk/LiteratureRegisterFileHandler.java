package kiosk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Provides file-handling methods for the literature register
 * This includes methods to write and read the collection of literatures
 * to a file
 * 
 */
public class LiteratureRegisterFileHandler {
    private final LiteratureRegister register;
    private final File file;
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
     * @throws java.io.FileNotFoundException
     */
    public void saveAllToFile() 
            throws IOException, FileNotFoundException, SecurityException
    {
        try (ObjectOutputStream os = new ObjectOutputStream(
                new FileOutputStream(filePath))) {
            os.writeObject(register);
        }
    }
    
    /**
     * Reads an saved file containig a saved literatureregister and
     * returns it
     * @return LiteratureRegister read from a saved file
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws java.io.FileNotFoundException
     */
    public LiteratureRegister readSavedFile() 
            throws IOException, ClassNotFoundException, FileNotFoundException
    {
        LiteratureRegister lr;
        FileInputStream fIS = new FileInputStream(file);
        try (ObjectInputStream ois = new ObjectInputStream(fIS)) {
            lr = (LiteratureRegister) ois.readObject();
        }
        return lr;      
    }
}

