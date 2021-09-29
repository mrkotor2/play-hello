package controllers;

import models.Student;
import play.Logger;
import play.db.jpa.JPA;
import play.mvc.Controller;
import play.mvc.results.Result;
import views.*;

import java.util.List;

public class StudentController extends Controller {

    public static void show() {
        Logger.error("dfdfdf");
        List<Student> students = Student.findAll();
        System.out.println(students);
        render(students);
    }

    public static void create() {
//        renderTemplate("StudentController/create.html");
        render();
    }

    //    public static void save(String fname, String lname, Integer age) {
    public static void save(Student student) {
//        Student u =  new Student();
////        u.setFirstName(); = "bob";
//        u.save();
//        JPA.em().flush();
//        JPA.em().getTransaction().commit();
//        request.params.get();

        System.out.println("Starting save");

//        new Student(fname, lname, age).save();
        student.save();

        System.out.println("Completing save");
        create();

    }


//    public static void create() {
//        Student student = new Student("CR","CRL", 21);
//        if (student != null) {
//            System.out.println(student.getFirstName());
//        }
//        else{
//            System.out.println("NOPE");
//        }
//        render(student);
//    }
//    public static void getResultList() {
//        Query query = JPA.em().createQuery("select Student.firstName, Student.lastName from Student");
//        List<Student> students = query.getResultList();

//        render(students);
//    }
//    public CompletionStage<Result> create(Http.Request request) {
//        JsonNode json = request.body().asJson();
//        return supplyAsync(() -> {
//            if (json == null) {
//                return badRequest(Util.createResponse("Expecting Json data", false));
//            }
//
//            Optional<Student> studentOptional = studentStore.addStudent(Json.fromJson(json, Student.class));
//            return studentOptional.map(student -> {
//                JsonNode jsonObject = Json.toJson(student);
//                return created(Util.createResponse(jsonObject, true));
//            }).orElse(internalServerError(Util.createResponse("Could not create data.", false)));
//        }, ec.current());
//    }
}
