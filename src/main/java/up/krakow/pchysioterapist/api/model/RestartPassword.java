package up.krakow.pchysioterapist.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "restart_paassword")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestartPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "users_id")
    private Integer usersId;
    @Column(name = "start_time")
    private LocalDateTime startLocalDateTime;
    @Column(name = "end_time")
    private LocalDateTime endLocalDateTime;
}
