package servlet;



import mybookslit.AccessMybooksList;
import mybookslit.BookListBean;
import mybookslit.MyListOfBooksMock;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Csongor on 3/29/2017.
 */

@WebServlet("/items")
public class MyBookListServlet extends HttpServlet {

    private static final String ACTION = "action";
    private static final String LIST_BOOK = "list";
    private static final String ADD_BOOK = "add";
    private static final String ID_BOOK = "id";
    private static final String VALUE_NEWBOOK = "value";

    public void service (HttpServletRequest request, HttpServletResponse response) {
        System.out.println("mybooklist service called");

        HttpSession session = request.getSession(true);

        String username = (String)session.getAttribute("username");
        System.out.println(username + "123");
        if (username == null){ // you are not logged in
            System.out.println("you're not logged in");
            notLoginAction(request, response, true);
        }

        else {   // you are logged in

            String action = request.getParameter(ACTION);

            if( action != null && action.equals(LIST_BOOK)){
                listBook(request, response);
            } else if (action != null && action.equals(ADD_BOOK)){
                addBook(request, response);
            }
            else if (action != null && action.equals("seeLogin")) {
                notLoginAction(request, response, false);
            }

            if (request.getSession(false).getAttribute("username") == null && action.equals("logout")) {
                try {
                    response.sendRedirect("./mybokklist.html");
                } catch (IOException e){
                    e.printStackTrace();
                }
                return;
            }
        }

    }

    private void listBook(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("list book");
        HttpSession session = request.getSession(true);

        AccessMybooksList abl = new AccessMybooksList();

        Integer s = (Integer)session.getAttribute("userid");

        List<BookListBean> list = abl.getBooksList(s);

        // put the list in a json
        JsonObjectBuilder jObjBuild = Json.createObjectBuilder();
        JsonArrayBuilder jArrBuild = Json.createArrayBuilder();
        for (ListIterator<BookListBean> iterator = list.listIterator(); iterator.hasNext();) {
            BookListBean element = iterator.next();
            jArrBuild.add(Json.createObjectBuilder()
                      .add("name", element.getBookName())
                      .add("id", element.getId())
            );
        }

        jObjBuild.add("books", jArrBuild);
        JsonObject jFinal = jObjBuild.build();

        returnJsonResponse(response, jFinal.toString());
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);

        String value = request.getParameter(VALUE_NEWBOOK);

        MyListOfBooksMock myListobject = MyListOfBooksMock.getInstance();
        myListobject.printList();
        myListobject.addItem(value);

        AccessMybooksList abl = new AccessMybooksList();
        abl.insertBook(value, (Integer)session.getAttribute("userid"));
    }

    private void returnJsonResponse (HttpServletResponse response, String jsonResponse){
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e){
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }

    private void notLoginAction(HttpServletRequest request, HttpServletResponse response, boolean notLogin){

        String jsonResponse;
        if (notLogin)
            jsonResponse = "{\"You are not logged in. \"}";
        else {
            String username = (String)request.getSession().getAttribute("username");
            jsonResponse = "{\"Hello  "+username+"\"}";
        }

        returnJsonResponse(response, jsonResponse.toString());
    }

    private void logout(HttpServletRequest request, HttpServletResponse response, boolean doLogout){

        String jsonResponse;
        if(doLogout)
            jsonResponse = "{\"You are logged out. \"}";
        else {
            jsonResponse = "{\"You are logged in. \"}";
        }

        returnJsonResponse(response, jsonResponse.toString());
    }

}
