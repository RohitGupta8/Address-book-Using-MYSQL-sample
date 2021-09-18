package com.bridgelabz.dbsample;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main ( String[] args ) {
        System.out.println ( "**********************************\n information saved on database \n**********************************\n" );
        int ch = 0;
        while (ch == 0) {
            Scanner scanner = new Scanner ( System.in );
            System.out.print ( "1. = Add Details\n2. = View Details\n3. = Edit Details\n4. = Delete Details\n5. = Exit\nEnter Your Choice: " );
            int choice = scanner.nextInt ();
            switch (choice) {
                case 1 -> {
                    try {
                        add ();
                    } catch (SQLException e) {
                        e.printStackTrace ();
                    }
                }
                case 2 -> {
                    try {
                        view ();
                    } catch (SQLException e) {
                        e.printStackTrace ();
                    }
                }
                case 3 -> {
                    try {
                        update ();
                    } catch (SQLException e) {
                        e.printStackTrace ();
                    }
                }
                case 4 -> {
                    try {
                        delete ();
                    } catch (SQLException e) {
                        e.printStackTrace ();
                    }
                }
                case 5 -> ch = 6;
                default -> System.out.println ( "Plz Enter 1 or 2 or 3  only" );
            }
        }
    }

    public static void add () throws SQLException {
        Connection connection = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/rohit", "root", "click" );
        Statement statement = connection.createStatement ();
        Scanner scanner = new Scanner ( System.in );
        System.out.print ( "Enter id: " );
        int id = scanner.nextInt ();
        scanner.nextLine ();
        System.out.print ( "Enter name: " );
        String name = scanner.nextLine ();
        System.out.print ( "Enter number: " );
        String number = scanner.nextLine ();
        System.out.print ( "Enter address: " );
        String address = scanner.nextLine ();

        PreparedStatement smt = connection.prepareStatement ( "insert into contact(id,name,number,address) values('"+id+"','"+name+"','"+number+"','"+address+"')" );
        smt.executeUpdate ();
        System.out.println ( "success" );

    }

    public static void view () throws SQLException {
        Scanner scanner = new Scanner ( System.in );

        System.out.print ( "1. = Name only\n2. = Name with Details\nEnter Number: " );
        int num = scanner.nextInt ();
        if (num == 1) {
            Connection connection = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/rohit", "root", "click" );
            Statement statement = connection.createStatement ();
            ResultSet resultSet = statement.executeQuery ( "select * from contact" );
            while (resultSet.next ()) {
                System.out.println ( resultSet.getString ( 2 ) );
            }
        } else if (num == 2) {
            Connection connection = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/rohit", "root", "click" );
            Statement statement = connection.createStatement ();
            ResultSet resultSet = statement.executeQuery ( "select * from contact" );
            while (resultSet.next ()) {
                System.out.println ( "----------------------------\nid       =  " + resultSet.getInt ( 1 ) + "\nname     =  " + resultSet.getString ( 2 ) + "\nnumber   =  " + resultSet.getString ( 3 ) + "\naddress  =  " + resultSet.getString ( 4 ) + "\n--------------------------\n" );
            }
        } else System.out.println ( "plz enter 1 or 2 next time" );
    }

    public static void delete () throws SQLException {
        Connection connection = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/rohit", "root", "click" );
        Statement statement = connection.createStatement ();
        Scanner scanner = new Scanner ( System.in );
        System.out.println ( "Enter Id you want to delete: " );
        int id = scanner.nextInt ();
        PreparedStatement smt = connection.prepareStatement ( "delete from contact where id = '"+id+"'" );

        smt.executeUpdate ();
        System.out.println ( "\n successfully Deleted........." );

    }

    public static void update () throws SQLException {
        Connection connection = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/rohit", "root", "click" );
        Statement statement = connection.createStatement ();
//        ResultSet resultSet = statement.executeQuery ( "select * from contact" );
        Scanner scanner = new Scanner ( System.in );


        System.out.print ( "Enter id: " );
        int id = scanner.nextInt ();
        scanner.nextLine ();
        System.out.print ( "Enter name: " );
        String name = scanner.nextLine ();
        System.out.print ( "Enter number: " );
        String number = scanner.nextLine ();
        System.out.print ( "Enter address: " );
        String address = scanner.nextLine ();

        PreparedStatement smt = connection.prepareStatement ( "update contact set name='"+name+"',number='"+number+"',address='"+address+"' where id='"+id+"'" );

        smt.executeUpdate ();
        System.out.println ("success" );
    }
}
