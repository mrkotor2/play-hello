package controllers;

import play.mvc.Controller;

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