package instagram.instagram_CloneCoding.dto.user;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class UserLoginDto {
    private String email;
    private String password;
    private String phone;
    private String name;
}
