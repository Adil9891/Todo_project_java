package com.example.Todo.controller;

import com.example.Todo.helper.TodoDBUtil;
import com.example.Todo.model.Todo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TodoControllerServlet", value ="/TodoControllerServlet")
public class TodoControllerServlet extends HttpServlet {
    private TodoDBUtil todoDBUtil;
    private DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoControllerServlet() {
        super();
// TODO Auto-generated constructor stub
    }
    private DataSource getDataSource() throws NamingException {
        String jndi="java:comp/env/jdbc/tododb" ;
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(jndi);
        return dataSource;
    }
    @Override
    public void init() throws ServletException {
// TODO Auto-generated method stub
        System.out.println("ici init()");
        super.init();
        try {
            dataSource= getDataSource();
            todoDBUtil = new TodoDBUtil(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        try {
            System.out.println("ici get");
            HttpSession session= request.getSession();
            String userName= (String)session.getAttribute("userName");
            System.out.println(userName);
            List<Todo> todos = todoDBUtil.getTodos(userName);
            request.setAttribute("todos", todos);

            request.getRequestDispatcher("/WEB-INF/list-todos.jsp").forward(request, response);;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("ici post");

    }


}
