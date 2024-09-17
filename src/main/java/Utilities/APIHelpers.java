package Utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class APIHelpers {
    public RequestSpecification requestSpecificationBaseURI() {
        return new RequestSpecBuilder().setBaseUri(APIEndpoints.base_uri).build();
    }

    public io.restassured.specification.RequestSpecification requestSpecificationWithoutAuth() {
        return new RequestSpecBuilder().
                setBaseUri(APIEndpoints.base_uri).
                addHeader("Content-Type", "application/json").
                build();
    }


    public RequestSpecification requestSpecificationWithoutAuthMultiPart() {
        return new RequestSpecBuilder().
                setBaseUri(APIEndpoints.base_uri).
                addHeader("Content-Type", "multipart/form-data").
                build();
    }
}
