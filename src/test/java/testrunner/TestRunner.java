package testrunner;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
features = "src\\test\\resources\\Features",
glue = "steps",
plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TestRunner  {

}
