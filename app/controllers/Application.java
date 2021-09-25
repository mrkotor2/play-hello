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

//        if ((new Random()).nextInt(2) == 1) {
//
//        }

        render();
    }

    public static void test() {
        flash.put("info", "something");
        renderArgs.put("", "");
        index();
    }

}