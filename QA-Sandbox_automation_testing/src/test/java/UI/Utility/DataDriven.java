package UI.Utility;

import UI.TestSuite.CRUD_People;
import org.junit.jupiter.params.provider.Arguments;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DataDriven extends CRUD_People {

    @DataProvider(name = "data-technology")
    public static Object[][] dataProviderMethodTechnology() {
        Object[][] data = new Object[4][1];
        data[0][0] = "C#"; data[1][0] = ".NET"; data[2][0] = "Java"; data[3][0] = "iOS";

        return data;
    }

    @DataProvider(name = "data-seniority")
    public static Object[][] dataProviderMethodSeniority() {
        Object[][] data = new Object[4][1];
        data[0][0] = "Junior"; data[1][0] = "Intermediate"; data[2][0] = "Senior"; data[3][0] = "Team lead";

        return data;
    }


    @DataProvider(name = "data-people")
    public static Object[][] dataProviderMethodPeople() {
        Object[][] data = new Object[4][4];
        data[0][0] = "Marko Markovic"; data[0][1] = "C#"; data[0][2] = "Junior"; data[0][3] = "QA Team";
        data[1][0] = "Petar Petrovic"; data[1][1] = ".NET"; data[1][2] = "Intermediate"; data[1][3] = "Development Team";
        data[2][0] = "Jovan Jovanovic"; data[2][1] = "Java"; data[2][2] = "Senior"; data[2][3] = "Development Team";
        data[3][0] = "Ivan Ivanovic"; data[3][1] = "iOS"; data[3][2] = "Team lead"; data[3][3] = "Development Team";

        return data;
    }

    @DataProvider(name = "data-existingPerson")
    public static Object[][] dataProviderMethodExistingPerson() {
        Object[][] data = new Object[1][4];
        data[0][0] = "Marko Markovic"; data[0][1] = "C#"; data[0][2] = "Junior"; data[0][3] = "QA Team";

        return data;
    }
}
