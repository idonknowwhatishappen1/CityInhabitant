import java.io.Serializable;

public class Message implements Serializable{
    public String city;
    public String method_name;
    public Object[] params;

    public String getCity(){
        return city;
    }

    public Message(String city, String method_name, Object[] params){
        this.city = city;
        this.method_name = method_name;
        this.params = params;
    }
}