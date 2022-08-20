package instagram.instagram_CloneCoding;

import instagram.instagram_CloneCoding.config.auth.PrincipalDetails;
import instagram.instagram_CloneCoding.dto.user.UserLoginDto;

import instagram.instagram_CloneCoding.domain.user.User;
import instagram.instagram_CloneCoding.domain.user.UserRepository;
import instagram.instagram_CloneCoding.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Transactional
    public boolean save(UserLoginDto userLoginDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userRepository.save(User.builder()
                .email(userLoginDto.getEmail())
                .password(encoder.encode(userLoginDto.getPassword()))
                .phone(userLoginDto.getPhone())
                .name(userLoginDto.getName())
                .title(null)
                .website(null)
                .profileImgUrl("/img/default_profile.jpg")
                .build());
        return true;
    }

    @Transactional
    public void update(UserUpdateDto userUpdateDto, MultipartFile multipartFile, PrincipalDetails principalDetails) {
        User user = userRepository.findUserByEmail(userUpdateDto.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.update(
                encoder.encode(userUpdateDto.getPassword()),
                        userUpdateDto.getPhone(),
                        userUpdateDto.getName(),
                        userUpdateDto.getTitle(),
                        userUpdateDto.getWebsite(),
                        userUpdateDto.getProfileImgUrl()
                );
    }
}
