package com.example.Todo.controller;

import com.example.Todo.helper.TodoDBUtil;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "EditTodoServlet", value = "/EditTodoServlet")
public class EditTodoServlet extends HttpServlet {
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
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int todoId = Integer.parseInt(request.getParameter("todoId"));

        todoDBUtil.updateTodoStatus(todoId);
        response.sendRedirect("TodoControllerServlet");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
