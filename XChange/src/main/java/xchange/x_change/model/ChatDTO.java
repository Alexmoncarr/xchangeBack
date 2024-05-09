package xchange.x_change.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDTO {

    private Integer id;

    @NotNull
    private Integer sender;

    @NotNull
    private Integer receiver;

    @NotNull
    @Size(max = 1000)
    private String message;

    @NotNull
    private LocalDateTime timestamp;
}