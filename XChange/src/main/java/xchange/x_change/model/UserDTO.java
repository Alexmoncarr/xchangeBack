package xchange.x_change.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;

    @NotNull
    private String firstname;
    
    @NotNull
    private String lastname;

    @NotNull
    @Size(max = 255)
    @UserUsernameUnique
    private String username;

    @NotNull
    @Size(max = 255)
    @UserEmailUnique
    private String email;

    
    private LocalDate birthdate;

    @NotNull
    @Size(max = 255)
    private String password;

    private List<Integer> userRoleRoles;

}
