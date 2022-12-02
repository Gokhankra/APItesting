package APiTestingPRoject.serializationAndDeserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Deserialization {

    public static void main(String[] args) throws JsonProcessingException {


        String jsonObject="{\n" + "\"bookId\" : \"1\",\n" + "\"customerName\" : \"Yaseen\"\n" + "}";

        //create and obectmapper object

    ObjectMapper objectMapper=new ObjectMapper();


    //then read the json object with object from mapper class
    SerializationAndDeserialization Deserialization=objectMapper.readValue(jsonObject,SerializationAndDeserialization.class);


            //once we get deserilization object we can use to get all available variable
        System.out.println(Deserialization.getBookId());
        System.out.println(Deserialization.getCustomerName());


        //Deserilization Json object to Map Object
        Map<String,Object> orderAsMap=objectMapper.readValue(jsonObject,Map.class);

        System.out.println(orderAsMap);

    }
}
