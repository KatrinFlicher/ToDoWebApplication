package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.constants.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseController extends HttpServlet {

    public void forward(String url, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request,response);
  }

    public void forwardError(String errorPage, String message, HttpServletRequest request,
                             HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(Constants.ERROR_MESSAGE,message);
        forward(errorPage,request,response);
  }

}
