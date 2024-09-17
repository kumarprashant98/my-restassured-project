package datafactory;

import dataobjects.Crud_PutRequest;

public class Crud_PutData {
    Crud_PutRequest crudPutRequest = new Crud_PutRequest();

    public Crud_PutRequest activitiesPutData() {
        crudPutRequest.setId(1);
        crudPutRequest.setTitle("Completed");
        crudPutRequest.setCompleted(Boolean.TRUE);
        return crudPutRequest;
    }
}
