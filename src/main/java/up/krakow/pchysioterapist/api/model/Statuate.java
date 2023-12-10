package up.krakow.pchysioterapist.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "statuate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Statuate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;
}

