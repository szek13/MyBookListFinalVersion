package servlet;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import mybookslit.UserAccesList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by Csongor on 3/28/2017.
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // read user and password introduced by the user in the UI
        String user = request.getParameter("u");
        String passwd = request.getParameter("p");

        UserAccesList userAccess = new UserAccesList();

        int iduser = -1;

        iduser = userAccess.checkUserCredentials(user, passwd);
        if (iduser != -1){
            System.out.println(user + "!!!");
            // the user exists in the db, so i authenticate him
            HttpSession session = request.getSession(true);
            session.setAttribute("username", user);
            session.setAttribute("userid", iduser);

            String success = "/mybooklist.html";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(success);
            dispatcher.forward(request, response);
        }

        else {
            System.out.println("user does not exist, so i donothing");
            String back = "/login.html";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(back);
            dispatcher.forward(request, response);
        }

        System.out.println("login service called");
    }
}
