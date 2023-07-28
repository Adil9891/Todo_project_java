package com.example.Todo.helper;

import com.example.Todo.model.Todo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TodoDBUtil {
    private DataSource dataSource;
    public TodoDBUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public TodoDBUtil() {
    }

    public List<Todo> getTodos( String userName) throws Exception {
        List<Todo> todos= new ArrayList<Todo>();
        Connection myConn=null;
        PreparedStatement pstmt = null;
        ResultSet myRs= null;
        try {
            myConn = dataSource.getConnection();



            String sql= "select * from todo where status = ? and username = ?";
            pstmt =  myConn.prepareStatement(sql);
            pstmt.setInt(1, 0);
            pstmt.setString(2, userName);
            myRs = pstmt.executeQuery();
            while(myRs.next()){
                int id = myRs.getInt("id");
                String description = myRs.getString("description");
                int status=myRs.getInt("status");
                String username =myRs.getString("username");
                System.out.println(description);

                Todo tempTodo= new Todo(id, description,status,username);
                System.out.println(tempTodo);

                todos.add(tempTodo);
            }
            System.out.println(todos);
            return todos;
        } finally{
            close(myConn,pstmt,myRs);
        }
    }




public Boolean authenticate(String userName, String password){

            // authetifcation : 1 er partie
            /*if (userName.equals("Team") & password.equals("BestTeamEver")){
                return true;
            }else return false;*/

    // authentification : 2ème partie
    Connection myConn = null;
    PreparedStatement myStmt = null;
    ResultSet myRs = null;
    try {
        myConn = dataSource.getConnection();
        String sql = "select * from employe where username=? and password=?";
        myStmt =  myConn.prepareStatement(sql);
        myStmt.setString(1, userName);
        myStmt.setString(2, password);
        myRs = myStmt.executeQuery();
        if (myRs.next()) return true;
        else return false;

    } catch (Exception e) {
        System.out.println(e.getMessage());
        return false;
    } finally {
        close(myConn, myStmt, myRs);
    }

}


    public void updateTodoStatus(int todoId) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dataSource.getConnection();

            String sql = "UPDATE todo SET status = 1 WHERE id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,todoId);
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close(conn, pstmt,null);
        }
    }


    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try{
            if(myStmt!=null)
                myStmt.close();
            if(myRs!=null)
                myRs.close();
            if(myConn!=null)
                myConn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


}
