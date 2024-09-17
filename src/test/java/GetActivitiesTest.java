import Utilities.APIEndpoints;
import Utilities.APIHelpers;
import dataobjects.GetActivitiesResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class GetActivitiesTest {
    SoftAssert softAssert = new SoftAssert();
    APIHelpers apiHelpers = new APIHelpers();

    @Test
    public void verifyThatGetActivitiesDataIsDisplayed() {

        Response response = given()
                .spec(apiHelpers.requestSpecificationWithoutAuth())
                .get(APIEndpoints.getActivities_Endpoint);

        GetActivitiesResponse[] swaggerGetActivitiesResponse = response.as(GetActivitiesResponse[].class);

        softAssert.assertEquals(response.getStatusCode(), 200, "Status code is not matching");
        softAssert.assertTrue(Arrays.stream(swaggerGetActivitiesResponse).noneMatch(el -> el.getId() == null), "Id is not null");
        softAssert.assertTrue(Arrays.stream(swaggerGetActivitiesResponse).noneMatch(el -> el.getTitle() == null), "Title is not null");
        softAssert.assertTrue(Arrays.stream(swaggerGetActivitiesResponse).noneMatch(el -> el.getDueDate() == null), "Due Date is not null");
        softAssert.assertTrue(Arrays.stream(swaggerGetActivitiesResponse).noneMatch(el -> el.getCompleted() == null), "Completed is not null");
        softAssert.assertAll();
    }
}
