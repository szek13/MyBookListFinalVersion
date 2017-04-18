package servlet;

import mybookslit.UserAccesList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Csongor on 3/28/2017.
 */

@WebServlet("/pwdchange")
public class PasswordChangeServlet extends HttpServlet {

    public void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // read user nd password introduced by the user in the UI
        String username = request.getParameter("u");
        String password = request.getParameter("p");

        UserAccesList passChange = new UserAccesList();

        passChange.passwordChange(username, password);

        String back = "/mybooklist.html";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(back);
        dispatcher.forward(request, response);
    }
}
