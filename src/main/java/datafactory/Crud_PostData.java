package datafactory;

import dataobjects.Crud_PostRequest;

public class Crud_PostData {

    Crud_PostRequest crudPostRequest = new Crud_PostRequest();

    public Crud_PostRequest activitiesPostData() {
        crudPostRequest.setId(1);
        crudPostRequest.setTitle("Working");
        crudPostRequest.setCompleted(Boolean.FALSE);
        return crudPostRequest;
    }
}
