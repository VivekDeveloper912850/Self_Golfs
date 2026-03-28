package golf_app.Score.service;
import golf_app.Score.entity.User;
import golf_app.Score.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        user.setSubscriptionActive(false);
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent() && user.get().getPassword().equals(password)){
            return user.get();
        }
        return null;
    }
}