package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize="1920x1080";
        Configuration.baseUrl="https://demoqa.com";
    }


    @Test
    void fillFormTest() {
        open("/text-box");
        $("#userName").setValue("Alex Egorov");
        $("#userEmail").setValue("alex@egorov.com");
        $("#currentAddress").setValue("JBL street, 1");
        $("#permanentAddress").setValue("Bose street, 2");
        $("#submit").click();

        $("#output").shouldHave(text("Alex Egorov"), text("Alex Egorov"),
                text("JBL street, 1"), text("Bose street, 2"));

    }
}
