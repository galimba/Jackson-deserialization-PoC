package hello;
import com.fasterxml.jackson.core.Version;
//import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl

public class Greeting2 {

    private final long id;
    private final String content;

    public Greeting2(long id, String content) {
        this.id = id;
        this.content = content;
        // uncomment to show Jackson version. Check it's vulnerable (2.9.9)
	    //    Version version = com.fasterxml.jackson.databind.cfg.PackageVersion.VERSION;
        //System.out.println("Jackson: "+version);
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
