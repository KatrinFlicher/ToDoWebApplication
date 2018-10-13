package by.gsu.epamlab.impl;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.interfaces.ITaskDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

public class FileManager {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean UploadFile(InputStream inputStream, String directory, String fileName, String idTask)
            throws IOException {
        boolean result = false;
        OutputStream out = null;
        InputStream filecontent = null;
        File dirFile = new File(directory);
        if (!dirFile.exists())
            dirFile.mkdirs();
        try {
            out = new FileOutputStream(new File(dirFile+ File.separator + fileName));
            filecontent = inputStream;
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            ITaskDAO iTaskDAO = TaskFactory.getTaskDAO();
            iTaskDAO.editFileInTask(idTask,fileName);
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
        }
        return result;
    }

    public boolean DownloadFile(OutputStream out, String directory, String fileName)
            throws IOException {
        File dirFile = new File(directory + File.separator + fileName);
        FileInputStream fileInputStream = new FileInputStream(dirFile);
        int i;
        while ((i = fileInputStream.read()) != -1) {
            out.write(i);
        }
        fileInputStream.close();
        out.close();
        return true;
    }


    public boolean DeleteFile(String flag, String directory, String idTask){
        File dirFile = new File(directory);
        deleteFile(dirFile);
        ITaskDAO iTaskDAO = TaskFactory.getTaskDAO();
        if (flag.equals("FILE_CONTROLLER"))
        iTaskDAO.editFileInTask(idTask,"no file");
        if (!dirFile.exists())
            return true;
        else return false;
    }

    private static void deleteFile(File element) {
        if (element.isDirectory()) {
            for (File sub : element.listFiles()) {
                deleteFile(sub);
            }
        }
        element.delete();
    }
}
