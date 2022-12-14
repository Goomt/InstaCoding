package instagram.instagram_CloneCoding.controller;

import instagram.instagram_CloneCoding.dto.user.UserLoginDto;
import instagram.instagram_CloneCoding.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AccountController {
    @Autowired
    private final UserService userService;

    @PostMapping("/signup")
    public String signup(UserLoginDto userLoginDto) {
        if (userService.save(userLoginDto)) {
            return "redirect:/login";
        } else {
            return "redirect:/signup?error";
        }
    }
}
