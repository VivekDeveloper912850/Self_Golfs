package golf_app.Score.controller;



import golf_app.Score.DTO.LoginRequest;
import golf_app.Score.entity.User;
import golf_app.Score.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

//    @PostMapping("/register")
//    public User register(@RequestBody User user){
//        return userService.register(user);
//    }


    @PostMapping("/register")
    public User register(@RequestBody Map<String, Object> body){

        User user = new User();
        user.setName((String) body.get("name"));
        user.setEmail((String) body.get("email"));
        user.setPassword((String) body.get("password"));
        user.setSubscriptionActive(false); // force set

        return userService.register(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request){
        return userService.login(request.getEmail(), request.getPassword());
    }
}