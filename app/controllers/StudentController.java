package controllers;

import models.Student;
import play.db.jpa.JPA;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Util;
import play.mvc.results.Result;

import javax.inject.Inject;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static controllers.Application.index;

public class StudentController extends Controller {

    public static void show() {
        List<Student> students = Student.findAll();
        System.out.println(students);
        render(students);
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
