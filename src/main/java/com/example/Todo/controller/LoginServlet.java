package com.example.Todo.controller;

import com.example.Todo.helper.TodoDBUtil;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {



    private TodoDBUtil todoDBUtil;
    private DataSource dataSource;
    private DataSource getDataSource() throws NamingException {
        String jndi="java:comp/env/jdbc/tododb" ;
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(jndi);
        return dataSource;
    }



    @Override
    public void init() throws ServletException {
        super.init();
        try {

            dataSource= getDataSource();
            todoDBUtil = new TodoDBUtil(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie [] cookies = request.getCookies();
        if(cookies!= null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("userName"))
                    request.setAttribute("userName", cookie.getValue()) ;
            }
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

       Boolean isAuthenticated = todoDBUtil.authenticate(userName, password);

        if (isAuthenticated) {
            Cookie cookie = new Cookie("userName", userName);
            cookie.setMaxAge(60*60*24);
            response.addCookie(cookie);
            HttpSession session= request.getSession();
            session.setAttribute("userName", userName);
            response.sendRedirect("TodoControllerServlet");
          // request.getRequestDispatcher("TodoControllerServlet").forward(request,response);

        } else {
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);

        }

    }
}
