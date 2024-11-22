package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    String firstName = "King";
    String lastName = "Saladin";
    String userEmail = "saladin@king.com";
    String userNumber = "9001118888";
    String subjects = "Math";
    String imageRootPath = "src/test/resources/img/batman.jpg";
    String imageClassPath = "img/batman.jpg";
    String currentAddress = "Jasmine street, 1";
    String state = "NCR";
    String city = "Noida";


    @Test
    @DisplayName("Тест формы регистрации студента")
    void testPracticeForm() {

        // open webpage
        open("/automation-practice-form");
        // remove banners
        executeJavaScript("$('#fixedban').remove();");
        executeJavaScript("$('footer').remove();");
        // Проверить заголовок
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        // first name
        $("#firstName").setValue(firstName);
        // last name
        $("#lastName").setValue(lastName);
        // user email
        $("#userEmail").setValue(userEmail);
        // gender
        $("#genterWrapper").$(byText("Male")).click(); // best
        // mobile number
        $("#userNumber").setValue(userNumber);
        // date of birth
        $("#dateOfBirthInput").scrollIntoView(true).click();
        // date of birth: year
        $(".react-datepicker__year-select").selectOption("1993"); // best (в случае select и option)
        // date of birth: month
        $(".react-datepicker__month-select").selectOption("July"); // best (в случае select и option)
        // date of birth: day
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click(); // best
        //subjects
        $("#subjectsInput").setValue(subjects).pressEnter();
        // hobbies
        $("#hobbiesWrapper").$(byText("Sports")).click();
        // upload image
        $("#uploadPicture").uploadFromClasspath((imageClassPath)); // good
        // current address
        $("#currentAddress").setValue(currentAddress);
        // state
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        // city
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        // submit
        $("#submit").click();
        // assertions
        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName), text(lastName), text(userEmail), text(userNumber));
    }
}
