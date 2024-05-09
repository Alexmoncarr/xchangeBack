package xchange.x_change.model;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderDTO {

    private Integer id;
    private LocalDate orderDate;
    private List<Integer> orderProductProducts;
    private Integer seller;

}
