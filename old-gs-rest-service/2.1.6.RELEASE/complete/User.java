package hello;

public class User{
    //@JsonProperty("user_id")
    private long userId;
    //@JsonProperty("first_name")
    private String firstName;
    //@JsonProperty("last_name")
    private String lastName;
    //@JsonProperty("town")
    private String town;

    public User(){

    }

    public User(long id, String name, String surname, String town){
        this.user_id= id;
        this.first_name= name;
        this.last_name= surname;
        this.town=town;
    }

    
}

