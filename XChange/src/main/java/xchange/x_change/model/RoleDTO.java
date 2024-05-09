package xchange.x_change.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {

    private Integer id;

    @NotNull
    @Size(max = 255)
    private String roleType;

}
