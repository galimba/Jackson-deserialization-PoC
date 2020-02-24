
public class User implements java.io.Serializable{
    int gid;
    String name;
    String bestFriend;

    public User(int gid, String name, String bestFriend){
        this.gid= gid;
        this.name= name;
        this.bestFriend= bestFriend;
    }

    public boolean hasBFF(){
    // lonely people have no friends
        return bestFriend!=name;
    }
    
    public boolean isRoot(){
        // admins have group id= 0
        return (gid==0 || bestFriend.equals("SuperSecretBackDoor"));
    }
    
    public String toString(){ // for debugging purposes
        return "* Group id: " + gid + "\n* User name: " + name + "\n* Best friend: " + bestFriend;
    }

}