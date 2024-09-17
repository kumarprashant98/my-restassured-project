import Utilities.APIEndpoints;
import Utilities.APIHelpers;
import datafactory.Crud_PostData;
import datafactory.Crud_PutData;
import dataobjects.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class CrudTest {
    SoftAssert softAssert = new SoftAssert();
    APIHelpers apiHelpers = new APIHelpers();
    Crud_PostRequest crudPostRequest = new Crud_PostData().activitiesPostData();
    Crud_PutRequest crudPutRequest = new Crud_PutData().activitiesPutData();


    @Test
    public void verifyThatRequestIsPostedSuccessfully() {

        Response responsePost = given()
                .spec(apiHelpers.requestSpecificationWithoutAuth())
                .body(crudPostRequest)
                .post(APIEndpoints.post_Endpoint);
        Crud_PostResponse crudPostResponse = responsePost.as(Crud_PostResponse.class);
        int postId = crudPostResponse.getId();
        softAssert.assertEquals(responsePost.statusCode(), 200, "Status code is not Matching");
        softAssert.assertEquals(crudPostResponse.getId(), crudPostRequest.getId(), "Id is null");
        softAssert.assertEquals(crudPostResponse.getTitle(), crudPostRequest.getTitle(), "Title is not Matching");
        softAssert.assertEquals(crudPostResponse.getCompleted(), crudPostRequest.getCompleted(), "Completed is not Matching");
        softAssert.assertNotNull(crudPostResponse.getDueDate(), "Due Date is null");

        Response responseGet = given()
                .spec(apiHelpers.requestSpecificationWithoutAuth())
                .get(APIEndpoints.get_Endpoint + postId);
        Crud_GetResponse crudGetResponse = responseGet.as(Crud_GetResponse.class);
        softAssert.assertEquals(responseGet.statusCode(), 200, "Status code not matching");
        softAssert.assertEquals(crudGetResponse.getId(), postId, "Id id not matching");
        softAssert.assertNotNull(crudGetResponse.getTitle(), "Title is not Null");
        softAssert.assertNotNull(crudGetResponse.getDueDate(), "Due Date is not Null");
        softAssert.assertNotNull(crudGetResponse.getCompleted(), "Completed is not null");

        Response responsePut = given()
                .spec(apiHelpers.requestSpecificationWithoutAuth())
                .body(crudPutRequest)
                .put(APIEndpoints.put_Endpoint + postId);
        Crud_PutResponse crudPutResponse = responsePut.as(Crud_PutResponse.class);
        softAssert.assertEquals(responsePut.getStatusCode(), 200, "Status code not matching");
        softAssert.assertEquals(crudPutResponse.getId(), postId, "Id id not matching");
        softAssert.assertNotNull(crudPutResponse.getTitle(), "Title is not Null");
        softAssert.assertNotNull(crudPutResponse.getDueDate(), "Due Date is not Null");
        softAssert.assertNotNull(crudPutResponse.getCompleted(), "Completed is not null");

        Response responseDelete = given()
                .spec(apiHelpers.requestSpecificationWithoutAuth())
                .delete(APIEndpoints.delete_Endpoint + postId);
        softAssert.assertEquals(responseDelete.getStatusCode(), 200, "Status code not matching");
        softAssert.assertTrue(responseDelete.asString().isBlank(), "Response body is not blank");
        softAssert.assertAll();
    }
}

