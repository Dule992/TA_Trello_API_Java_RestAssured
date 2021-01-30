import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.impl.client.SystemDefaultCredentialsProvider;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class Board extends GeneralEnvironment {

    String boardUrl;
    String newBoardName = "Board Test";
    String updateName = "Updated Board Test";
    private String boardID;
    private String firstListID;
    private String secondListID;
    private String firstListFirstCardID;
    private String secondListFirstCardID;
    JSONArray jsonList = new JSONArray();
    JSONObject jsonObject = new JSONObject();
    String filePathBoard = "D:\\workspace\\TrelloAPI-Rest Assured Project\\src\\main\\resources\\board.json";
    String filePathList = "D:\\workspace\\TrelloAPI-Rest Assured Project\\src\\main\\resources\\lists.json";
    String filePathCard = "D:\\workspace\\TrelloAPI-Rest Assured Project\\src\\main\\resources\\card.json";

    @Test
    public void createBoard(){

        boardUrl = "/1/boards/?key=" + trelloKey + "&token=" + trelloToken +  "&name=" + newBoardName;

        RestAssured.baseURI = trelloAPIURL;
        RequestSpecification request = RestAssured.given();
        request.headers("Content-type","application/json");

        Response response = request.post(boardUrl);

        Assert.assertEquals(200, response.getStatusCode());
        System.out.println(response.toString());

        boardID = response.jsonPath().get("id");
        System.out.println("Created board id is: " + boardID);
        jsonObject.put("boardID", boardID);
        writeToFile(filePathBoard);
    }

}
