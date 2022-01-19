package hexa.bank.account.bank.cucumber;

import hexa.bank.account.bank.BankApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest(classes = { BankApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberOptions(features = "src/test/resources/cucumber",
        plugin = {"pretty", "html:target/cucumber"})
public class CucumberIT {
}
