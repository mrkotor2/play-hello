package controllers;

import com.mysql.jdbc.Connection;
import play.*;
import play.db.jpa.JPA;
import play.mvc.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import models.*;

import javax.persistence.Query;

public class Application extends Controller {

    public static void index() {
//        Query query = JPA.em().createQuery("select * from Student");
//        List<Student> students = query.getResultList();
//        render(students);



        if ((new Random()).nextInt(2) == 1) {

        }


        Connection dbConnection = null;

        try {
            String url = "jdbc:mysql://localhost:3306/playdb";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");

            dbConnection = (Connection) DriverManager.getConnection(url, info);

            if (dbConnection != null) {
                System.out.println("Successfully connected to MySQL database");
            }

        } catch (SQLException ex) {
            System.out.println("An error occurred while connecting MySQL database");
            ex.printStackTrace();
        }

        render();
    }

    public static void test() {
        flash.put("info", "fdfdfd");
        renderArgs.put("", "");
        index();
    }

}