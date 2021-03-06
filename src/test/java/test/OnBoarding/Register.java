package test.OnBoarding;

import controller.Homepage.HomepageController;
import controller.OnBoarding.RegisterController;
import jdk.jfr.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.BaseTest;

import java.util.Random;
import java.util.stream.IntStream;

public class Register extends BaseTest {

    private HomepageController homepageController;
    private RegisterController registerController;

    Random rand = new Random();
    IntStream number = rand.ints(100,1000);
    String email = "test-automation" + number + "@gmail.com";
    String pass = "P@ssw0rd" + number;

    @DataProvider(name = "credentials")
    public Object[][] getData(){
        return new Object[][] {
                {"test", "test", "FAILED"},
                {email, pass, "PASSED"}
        };
    }

    @Test(priority = 0)
    @Parameters({"environment"})
    @Description("Navigate to User Page")
    public void navigateToUserPage() {
        homepageController = new HomepageController(driver);
        homepageController.toUserPage();
    }

    @Test(priority = 1, dataProvider = "credentials")
    @Description("Register an Account")
    public void RegisterAccount(String email, String pass, String expected) {
        registerController = new RegisterController(driver);
        registerController
                .fillEmail(email)
                .fillPass(pass)
                .fillConfirmPass(pass)
                .clickRegister(expected);
    }

}
