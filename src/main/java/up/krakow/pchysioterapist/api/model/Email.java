package up.krakow.pchysioterapist.api.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.model.enums.EEmail;
import up.krakow.pchysioterapist.api.service.EmailService;

import java.io.Serializable;
import java.time.LocalDateTime;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Email {
    //id visit report
    private int visitId;
    private String recipientEmail;
    private LocalDateTime startTime;
    LocalDateTime endTime;
    private String username;
    private String eventName;
    private String description;
    @Enumerated(EnumType.STRING)
    private EEmail email;
}
