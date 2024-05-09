package xchange.x_change.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReviewDTO {

    private Integer id;

    private String content;

    @NotNull
    private Integer rating;

    private Integer reviewer;

    private Integer reviewee;

}
