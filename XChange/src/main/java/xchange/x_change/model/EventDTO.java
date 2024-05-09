package xchange.x_change.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EventDTO {

    private Integer id;

    @NotNull
    @Size(max = 255)
    private String eventName;

    @Size(max = 1000)
    private String description;

    private LocalDate eventDate;

    private Integer admin;
}
