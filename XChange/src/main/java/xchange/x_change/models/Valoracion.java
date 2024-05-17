package xchange.x_change.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "valoraciones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int puntuacion;  // Escala, por ejemplo, de 1 a 5
    private String comentario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;  // Usuario que deja la valoración
}