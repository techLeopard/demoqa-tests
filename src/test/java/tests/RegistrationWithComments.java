package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationWithComments {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen=true;
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

        // Открыть страницу
        open("/automation-practice-form");

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
//        $("#gender-radio-1']").click(); // wrong: not clickable element
//        $("#gender-radio-1']").parent().click(); // wrong: invalid
//        $(byText("Male")).click(); // not very good
//        $("label[for='gender-radio-1']").click(); // ok
        $("#genterWrapper").$(byText("Male")).click(); // best

        // mobile number
        $("#userNumber").setValue(userNumber);

        // date of birth
        $("#dateOfBirthInput").scrollIntoView(true).click();

        // date of birth: year
//        $(".react-datepicker__year-select").click();
//        $("option[value='1993']").scrollIntoView(true).click();
//        $(".react-datepicker__year-select").selectOptionByValue("1993"); // ok
        $(".react-datepicker__year-select").selectOption("1993"); // best (в случае select и option)

        // date of birth: month
//        $(".react-datepicker__month-select").click();
//        $("option[value='3']").click();
        //        $(".react-datepicker__month-select").selectOptionByValue("6"); // less readable
        $(".react-datepicker__month-select").selectOption("July"); // best (в случае select и option)

        // date of birth: day
//        $x("//div[text()='14']").click(); // not very good
//        $x("//*[@class='react-datepicker__day--030'][not(contains(@class, 'react-datepicker__day--outside-month'))}").click(); // xpath
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click(); // best

        //subjects
//        $("#subjectsInput").setValue(subjects);
//        $(".subjects-auto-complete__menu").click();
        $("#subjectsInput").setValue(subjects).pressEnter();

        // hobbies
//        $("label[for='hobbies-checkbox-1']").scrollIntoView(true).click(); // ok
        $("#hobbiesWrapper").$(byText("Sports")).click();

        // upload image
//        $("#uploadPicture").uploadFile(new File(imageRootPath)); // not very good
        $("#uploadPicture").uploadFromClasspath((imageClassPath)); // good

        // current address
        $("#currentAddress").setValue(currentAddress);

        // state
//        $("#react-select-3-input").setValue(state);
//        $("#react-select-3-option-0").click();
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
//        $("#react-select-3-option-0").click();

        // city
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
//        $("#react-select-4-input").setValue(city);

        // submit
        $("#submit").click();

        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName), text(lastName), text(userEmail), text(userNumber));
    }
}
