package by.gsu.epamlab.factories;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.impl.FileManager;
import by.gsu.epamlab.impl.UserDBImpl;
import by.gsu.epamlab.interfaces.IUserDAO;

public class FileFactory {
    private static FileManager fileManager;

    public static void create(String strPath){
        fileManager = new FileManager();
        fileManager.setPath(strPath);
    }

    public static FileManager getFileManager(){
        return fileManager;
    }
}
