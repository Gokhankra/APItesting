package APiTestingPRoject.serializationAndDeserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Serialization {

    // 1. Create an object of pojo class and declare requiered variables

    // Create object of pojo class and set values


    public static void main(String[] args) throws JsonProcessingException {

        SerializationAndDeserialization obj = new SerializationAndDeserialization();
            obj.setBookId("1");
            obj.setCustomerName("Yaseen");


            //  ObjectMAPPER class will help us to convert to JSON
              //create an objetmapper   obj providing by Jackson Dependency;

        ObjectMapper objectMapper=new ObjectMapper();

        String jsonPayload=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);

        System.out.println("Json object is : "+"\n"+jsonPayload);

        //output
   /*     Json object is :
        {
            "bookId" : "1",
                "customerName" : "Yaseen"
        }
        */


    }
}
