package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.factories.FileFactory;
import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.impl.FileManager;
import by.gsu.epamlab.interfaces.BaseController;
import by.gsu.epamlab.interfaces.ITaskDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.*;

@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50)   // 50MB
public class FileController extends BaseController {
    private FileManager fileManager = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("fileAction");
        String idTask = request.getParameter(Constants.KEY_ID_TASK);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.KEY_USER);
        String section = (String) session.getAttribute(Constants.KEY_SECTION);
        fileManager = FileFactory.getFileManager();
        String directory = getDirectory(idTask, user);
        if (action.equals("UPLOAD"))
            UploadFile(request,response,directory,idTask);
        if (action.equals("DELETE"))
            DeleteFile(request,response,directory,idTask);
        if (action.equals("DOWNLOAD"))
            DownloadFile(request,response,directory);
        session.setAttribute(Constants.KEY_SECTION, section);
    }

    private void UploadFile(HttpServletRequest request, HttpServletResponse response, String directory, String idTask)
            throws ServletException, IOException {
        final Part filePart = request.getPart("file");
        final String fileName = filePart.getSubmittedFileName();
        if (fileManager.UploadFile(filePart.getInputStream(),directory,fileName,idTask))
            response.sendRedirect(Constants.TASK_CONTR);
    }

    private void DownloadFile(HttpServletRequest request, HttpServletResponse response, String directory)
            throws IOException {
        String fileName = request.getParameter("nameFile");
        response.setContentType("APPLICATION/OSTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        fileManager.DownloadFile(response.getOutputStream(),directory,fileName);
    }

    private void DeleteFile(HttpServletRequest request, HttpServletResponse response, String directory, String idTask)
            throws  IOException {
        if(fileManager.DeleteFile("FILE_CONTROLLER",directory, idTask))
            response.sendRedirect(Constants.TASK_CONTR);;
    }

    private String getDirectory(String idTask, User user){
        String savePath = "uploadFiles";
        String login = user.getLogin();
        String appPath = fileManager.getPath();
        String directory = appPath + File.separator + savePath + File.separator + login + File.separator + idTask;
        return directory;
    }
}
