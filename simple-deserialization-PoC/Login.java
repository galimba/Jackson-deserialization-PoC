import java.io.*;
import java.util.Base64;

public class Login{
    private User user;

    // login (create cookie from user / pass)
    public Login(String username, String password){
        user= authenticate(username,password);
        if(user != null)  {
            System.out.println("Hello, "+username+"! Here's your cookie! Om nom nom");
            System.out.println(serialize(user));
        }
    }

    public Login(String cookie){
        user = deserialize(cookie);
        if(user.isRoot()){
            System.out.println("Welcome back Admin!");
        }else{
            System.out.println("Greetings "+ user.name+ " ! Wanna talk to your BFF "+user.bestFriend+"?");
        }
    }

    // auth method
    private User authenticate(String username, String password){
        if(username.equals("Alice") &&  password.equals("imisswonderland")){ // hardcoded for PoC
            return new User(1, username, "Bob"); // BFF ("Bob") could be loaded from db
        } else if( username.equals("root") && password.equals("toor")){
            return new User(0, "root", "root");
        } else {
            System.out.println("Bad LogIn!");
            return null;
        }
    }
    
    /*
    // I wanted to try this just to check how the cookie changes from using a guid=0 to guid=1
    // turns out:
    // (1,Alice,Bob) -> rO0ABXNyAARVc2Vy1Al7dcgwEDwCAANJAANnaWRMAApiZXN0RnJpZW5kdAASTGphdmEvbGFuZy9TdHJpbmc7TAAEbmFtZXEAfgABeHAAAAABdAADQm9idAAFQWxpY2U=
    // (0,Alice,Bob) -> rO0ABXNyAARVc2Vy1Al7dcgwEDwCAANJAANnaWRMAApiZXN0RnJpZW5kdAASTGphdmEvbGFuZy9TdHJpbmc7TAAEbmFtZXEAfgABeHAAAAAAdAADQm9idAAFQWxpY2U=
    // (only an 'A' changes to a 'B') // gonna have to check out the hex values for the decoded cookie to double check
    private User authenticate(String username, String password){
        if(username.equals("Alice") &&  password.equals("imisswonderland")){ // hardcoded for PoC
            return new User(1, "Alice", "Bob"); // BFF ("Bob") could be loaded from db
        } else if( username.equals("root") && password.equals("toor")){
            return new User(0, "Alice", "Bob");
        } else {
            System.out.println("Bad LogIn!");
            return null;
        }
    }*/

    //serialization methods
    public String serialize(User user){
        byte[] userBytes={};
        try{
            ByteArrayOutputStream bOut= new ByteArrayOutputStream();
            ObjectOutput oOut= new ObjectOutputStream(bOut);
            oOut.writeObject(user); // writes | serialization
            oOut.flush();
            userBytes= bOut.toByteArray();
            oOut.close();
            bOut.close();
        } catch (Exception e){
            System.out.println(e);
        }
        return new String(Base64.getEncoder().encode(userBytes));
    }

    public User deserialize(String base64In){
        byte[] userBytes= Base64.getDecoder().decode(base64In.getBytes());
        User userTo=null;
        try{
            ByteArrayInputStream bIn= new ByteArrayInputStream(userBytes);
            ObjectInput oIn= new ObjectInputStream(bIn);
            user= (User) oIn.readObject(); //reads | deserialization
            oIn.close();
            bIn.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return user;
    }

    // main
    public static void main(String [] args){
        if(args.length == 2) {
            Login session = new Login(args[0], args[1]);
        }else {
            Login session = new Login(args[0]);
        }
    }

}