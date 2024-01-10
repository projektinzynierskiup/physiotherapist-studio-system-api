package up.krakow.pchysioterapist.api.service;

import jakarta.mail.MessagingException;
import up.krakow.pchysioterapist.api.model.Newsletter;

import java.io.IOException;
import java.util.UUID;

public interface NewsletterService {

    Newsletter signToNewsletter(String userEmail) throws MessagingException, IOException;

    void signOutFromNewsletter(String userEmail);

    void signOutFromNewsletterByEmail(UUID deleteKey);
    boolean isSignedIn(String email);
    void remove(String email);
}
