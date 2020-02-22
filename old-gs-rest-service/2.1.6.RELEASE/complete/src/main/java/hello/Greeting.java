package hello;
import com.fasterxml.jackson.core.Version;

public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
	        Version version = com.fasterxml.jackson.databind.cfg.PackageVersion.VERSION;
        System.out.println("Jackson: "+version);
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
