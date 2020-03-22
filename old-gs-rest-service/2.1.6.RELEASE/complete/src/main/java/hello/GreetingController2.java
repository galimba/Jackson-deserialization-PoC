package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.a;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class GreetingController2 {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/greeting2",method = RequestMethod.POST,consumes = "text/plain")
    public Greeting2 Greeting2(@RequestBody String name) {
        System.out.println("Greeting2 constructor("+name+")");
        return new Greeting2(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
