package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    //@Autowired UserService userService;

    @RequestMapping(path="/users", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User User(@RequestParam User user){
        return userService.createUser(user);
    }
}
