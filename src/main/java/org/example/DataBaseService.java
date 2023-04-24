package org.example;

import java.sql.*;

public class DataBaseService {
    private String url = "jdbc:postgresql://localhost:5432/app-jdbc-example";
    private String dbUser = "postgres";
    private String dbPassword = "postgres";


    public void saveUser(User user){
        try {
            Connection connection = DriverManager.getConnection(url,dbUser,dbPassword);
            Statement statement = connection.createStatement();
            String query = "insert into users(first_name,last_name,username,password) values('"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getUsername()+"','"+user.getPassword()+"');";
            statement.execute(query);
            System.out.println("User added");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void getUsers(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,dbUser,dbPassword);
            Statement statement = connection.createStatement();
            String query = "select * from users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString("last_name");
                String username = resultSet.getString(4);
                String password = resultSet.getString("password");
                User user = new User(id, firstName, lastName, username, password);
                System.out.println(user);
                statement.close();
                connection.close();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void deleteUser (int userid){
        try {
            Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            String query = "delete from users where id="+userid;
            boolean execute = statement.execute(query);
            System.out.println("User deletsed");
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUserPreparedStatement(User user){
        try {
            Connection connection = DriverManager.getConnection(url,dbUser,dbPassword);
            String query = "insert into users(first_name,last_name,username,password) values(?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3,user.getUsername());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.executeUpdate();
            System.out.println("User Added from preparedStatement");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void getUsersPreparedStatement(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,dbUser,dbPassword);
            String query = "select * from users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString("last_name");
                String username = resultSet.getString(4);
                String password = resultSet.getString("password");
                User user = new User(id, firstName, lastName, username, password);
//                System.out.println("User from getuserPreparedStateent");
                System.out.println(user);
//                statement.close();
                connection.close();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void saveUserCollableStatement(User user){
        try {
            Connection connection = DriverManager.getConnection(url,dbUser,dbPassword);
            String query = "{call add_user(?,?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setString(1, "first_name");
            callableStatement.setString(2, "last_name");
            callableStatement.setString(3, "username");
            callableStatement.setString(4, "password");

            System.out.println("User added");
//            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }






}
