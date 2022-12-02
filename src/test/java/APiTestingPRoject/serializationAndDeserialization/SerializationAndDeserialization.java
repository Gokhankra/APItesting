package APiTestingPRoject.serializationAndDeserialization;

public class SerializationAndDeserialization {




    //specific to rest assured. It is related to progrraming language - java
    // what is serialization Deserialization
    //   S    is conversion  of state of a  JAVA object bytte stream , D is the reverse flow
    //POJO - Plain Old Java Object.

    //  Converting a pojo object to a Json objet >>> Serialization
    // Converting a Json object to a POJO object >>> Deserialization


    //Json Gson
    // book id and Customer name


    // String to primitive   Double.Parsedouble method
    // string to wrapper class value   like str to obj     double d = Double.ValueOF(string);


    //    first step is to declare variable

    private String bookId;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    private String customerName;


    public String getBookId(){
        return bookId;
    }

    public String setBookId(String bookID){
        return     this.bookId=bookID;
    }



}
