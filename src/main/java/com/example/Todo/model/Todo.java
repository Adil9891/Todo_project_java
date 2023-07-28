package com.example.Todo.model;

public class Todo {
    int id;
    String description;
    int status;
    String userName;

    public Todo(int id, String description, int status) {
    }

    public Todo(int id, String description, int status, String userName) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.userName = userName;
    }


    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", userName='" + userName + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
