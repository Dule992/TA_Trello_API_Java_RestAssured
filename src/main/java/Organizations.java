import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class Organizations extends GeneralEnvironment {

    String organizationURL;
    String displayName = "Trello Api Organization";
    private String organizationID;
    String newBoardName = "From Org Created Board";
    String newOrganizationsName = "Updated Trello Api Organization";
    JSONArray jsonList = new JSONArray();
    JSONObject jsonObject = new JSONObject();
    String fileName = "D:\\workspace\\TrelloAPI-Rest Assured Project\\src\\main\\resources\\organization.json";

    @Test
    public void createNewOrganization(){

        organizationURL = "/1/organizations?key=" + trelloKey + "&token=" + trelloToken + "&displayName=" + displayName;

        RestAssured.baseURI = trelloAPIURL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");

        Response response = request.post(organizationURL);

        Assert.assertEquals(200, response.getStatusCode());
        System.out.println(response.asString());

        String displayNameActual = response.jsonPath().get("displayName");
        System.out.println(displayNameActual);
        Assert.assertEquals(displayName, displayNameActual);

        organizationID = response.jsonPath().get("id");
        System.out.println("created organization id is: " + organizationID);
        jsonObject.put("organizationID", organizationID);
        writeToFile();
    }

    @Test
    public void inCreatedOrgCreateBoard(){
        readFromFile();
        organizationURL = "/1/boards/?name=" + newBoardName + "&token=" + trelloToken + "&key=" + trelloKey + "&idOrganization=" + organizationID;

        RestAssured.baseURI = trelloAPIURL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");

        Response response = request.post(organizationURL);

        Assert.assertEquals(200, response.getStatusCode());
        System.out.println(response.asString());

        String boardName = response.jsonPath().get("name");
        System.out.println(boardName);
        Assert.assertEquals(newBoardName, boardName);

    }

    @Test
    public void updateOrganizationName(){
        readFromFile();
        organizationURL =  "/1/organizations/" + organizationID + "?key=" + trelloKey + "&token=" + trelloToken + "&key=" + trelloKey + "&displayName=" + newOrganizationsName;

        RestAssured.baseURI = trelloAPIURL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.put(organizationURL);

        Assert.assertEquals(200, response.getStatusCode());
        System.out.println(response.asString());

        Assert.assertEquals(newOrganizationsName, response.jsonPath().get("displayName"));
        System.out.println("New Name of Organization: " + response.jsonPath().get("displayName").toString());
    }

    @Test
    public void deleteOrganization(){
        readFromFile();
        organizationURL = "/1/organizations/" + organizationID + "?key=" + trelloKey + "&token=" + trelloToken;

        RestAssured.baseURI = trelloAPIURL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.delete(organizationURL);

        Assert.assertEquals(200, response.getStatusCode());
        System.out.println(response.asString());

        Response response1 = request.get(organizationURL);
        Assert.assertEquals(404, response1.getStatusCode());
        System.out.println(response1.asString());
    }

    //Methods
    public void writeToFile(){
        jsonList.add(jsonObject);
        try (FileWriter file = new FileWriter(fileName)) {

            file.write(jsonList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(){
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(fileName)) {

            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            //    System.out.println(jsonArray.get(0));
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
            System.out.println(jsonObject1.get("organizationID"));
            organizationID = (String) jsonObject1.get("organizationID");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
}
}
