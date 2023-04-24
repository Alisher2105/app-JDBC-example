package org.example;

import javax.xml.crypto.Data;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {
    public static Integer id;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DataBaseService dataBaseService = new DataBaseService();

        int i = -1;

        while(i != 0){
            System.out.println("0=>Exit, 1=>New User, 2=>Edit User, 3=>Delete User" +
                    "4=>List Users");
            i = sc.nextInt();
            sc = new Scanner(System.in);  // scannerni yangilash
            switch (i){
                case 1:
                    System.out.println(" Enter first name");
                    String firstName = sc.nextLine();
                    System.out.println(" Enter last name");
                    String lastName = sc.nextLine();
                    System.out.println(" Enter username");
                    String username = sc.nextLine();
                    System.out.println(" Enter password ");
                    String password = sc.nextLine();

                    User user = new User(id, firstName, lastName, username, password);
//                    dataBaseService.saveUser(user);
//                    dataBaseService.saveUserPreparedStatement(user);
                    dataBaseService.saveUserCollableStatement(user);
                    break;
                case 2:
                    System.out.println("Enter user`s id");
                    int id = sc.nextInt();
                    sc = new Scanner(System.in);
                    System.out.println("Enter editing first name");
                    firstName = sc.nextLine();
                    System.out.println("Enter editing lastName");
                    lastName = sc.nextLine();
                    System.out.println("Enter editing username");
                    username = sc.nextLine();
                    System.out.println("Enter editing password");
                    password = sc.nextLine();
                    user = new User(id, firstName, lastName, username, password);
//                    dataBaseService.editUser(user);
                    break;
                case 3:
                    System.out.println("Enter user`s id");
                    id = sc.nextInt();
                    dataBaseService.deleteUser(id);
                    break;
                case 4:
//                    dataBaseService.getUsers();
                    dataBaseService.getUsersPreparedStatement();
                    break;
            }

        }

    }

}