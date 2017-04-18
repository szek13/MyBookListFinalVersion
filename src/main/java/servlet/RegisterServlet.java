package servlet;

import mybookslit.UserAccesList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Csongor on 3/29/2017.
 */

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    public void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        System.out.println("empty");

        String username = request.getParameter("u");
        String password = request.getParameter("p");

        UserAccesList uslst = new UserAccesList();

        uslst.insertUser(username, password);

        String back = "/login.html";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(back);
        dispatcher.forward(request, response);
    }
}
