import org.json.simple.parser.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;

public class Utils {

    JSONArray jsonList = new JSONArray();
    JSONObject jsonObject = new JSONObject();

    public void writeToFile(String filePath){
        String fileName = filePath;
        jsonList.add(jsonObject);

        try (FileWriter file = new FileWriter(fileName)){

            file.write(jsonList.toJSONString());
            file.flush();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(String filePath, String key){
        String fileName = filePath;
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(fileName)) {

            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            JSONObject jsonObjectRead = (JSONObject) jsonArray.get(0);
            System.out.println(jsonObjectRead.get(key));

            if (key.equals("boardID")) {
                String boardID = (String) jsonObjectRead.get(key);
            } else if (key.equals("firstListID")) {
                String firstListID = (String) jsonObjectRead.get(key);
            } else if (key.equals("secondListID")) {
                String secondListID = (String) jsonObjectRead.get(key);
            } else if (key.equals("firstListFirstCardID")) {
                String firstListFirstCardID = (String) jsonObjectRead.get(key);
            } else if (key.equals("secondListFirstCardID")) {
                String secondListFirstCardID = (String) jsonObjectRead.get(key);
            } else {
                System.err.println("Illegal input for key!");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
