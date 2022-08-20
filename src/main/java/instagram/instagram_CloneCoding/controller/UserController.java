package instagram.instagram_CloneCoding.controller;

import instagram.instagram_CloneCoding.UserService;
import instagram.instagram_CloneCoding.config.auth.PrincipalDetails;
import instagram.instagram_CloneCoding.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/user/update")
    public String update() {
        return "user/update";
    }

    @PostMapping("/user/update")
    public String updateUser(UserUpdateDto userUpdateDto, @RequestParam("profileImgUrl") MultipartFile multipartFile, RedirectAttributes redirectAttributes, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        userService.update(userUpdateDto, multipartFile, principalDetails);
        redirectAttributes.addAttribute("id", principalDetails.getUser().getId());
        return "redirect:/user/profile";
    }
}
