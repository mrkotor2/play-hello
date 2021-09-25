import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Test
    public void aVeryImportantThingToTest() {
        assertEquals(2, 1 + 1);
    }

//    @Test
//    public void createAndRetrieveStudent() {
//        // Create a new user and save it
//        new Student("Test", "Testovich", 21).save();
//
//        // Retrieve the user with e-mail address bob@gmail.com
//        Student bob = Student.find("byFirstName", "Test").first();
//
//        // Test
//        assertNotNull(bob);
//        assertEquals("Test", bob.getFirstName());
//    }

}
