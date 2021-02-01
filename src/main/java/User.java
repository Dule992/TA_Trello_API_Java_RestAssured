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
import java.io.*;
import java.util.ArrayList;

public class User extends GeneralEnvironment {

        String userURL;
        private String boardID;
        String userName = "dusan992";
        String fullName = "Dusan Milic";
        String fileName = "D:\\workspace\\TrelloAPI-Rest Assured Project\\src\\main\\resources\\board.json";

        @Test
        public void getUserInfo() {
            readFromFile(fileName);
            userURL = "/1/boards/" + boardID + "/members?key=" + trelloKey + "&token=" + trelloToken + "&userName=" + userName + "&fullName=" + fullName;

            RestAssured.baseURI = trelloAPIURL;
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type","application/json");

            Response response = request.get(userURL);

            Assert.assertEquals(200, response.statusCode());
            System.out.println(response.asString());

            ArrayList nameFromArray = response.jsonPath().get("username");
            Assert.assertEquals(userName, nameFromArray.get(0));
            ArrayList fullNameFromArray = response.jsonPath().get("fullName");
            Assert.assertEquals(fullName, fullNameFromArray.get(0));
        }

        @Test
        public void getUserMailInfo(){
            userURL = "/1/members/" + userName + "?key=" + trelloKey + "&token=" + trelloToken;

            RestAssured.baseURI = trelloAPIURL;
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type","application/json");

            Response response = request.get(userURL);

            Assert.assertEquals(200,response.getStatusCode());
            System.out.println(response.asString());

            System.out.println((String) response.jsonPath().get("aaEmail"));
        }

        @Test
        public void getAllOrganizations(){
            userURL = "/1/members/" + userName + "/organizations?key=" + trelloKey + "&token=" + trelloToken;

            RestAssured.baseURI = trelloAPIURL;
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");

            Response response = request.get(userURL);

            Assert.assertEquals(200, response.getStatusCode());
            System.out.println(response.asString());

            ArrayList arrayList = response.jsonPath().get("displayName");
            System.out.println("Number of total organizations: " + arrayList.size());
            for (int i = 0; i < arrayList.size(); i++){

                System.out.println((i+1) + ". organizations name: " + arrayList.get(i));
            }
        }


    /*public void writeToFile(String filePath) {
        String fileName = filePath;
        jsonList.add(jsonObject);

        try (FileWriter file = new FileWriter(fileName)) {

            file.write(jsonList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void readFromFile(String fileName){
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(fileName)) {

            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            JSONObject jsonObjectRead = (JSONObject) jsonArray.get(0);
            System.out.println(jsonObjectRead.get("boardID"));
            boardID = (String) jsonObjectRead.get("boardID");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e){
            e.printStackTrace();
        }
    }
}
