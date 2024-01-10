package up.krakow.pchysioterapist.api.service;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.exception.EmailDoesNotExistException;
import up.krakow.pchysioterapist.api.exception.UserIsSignedToNewsletterException;
import up.krakow.pchysioterapist.api.model.Newsletter;
import up.krakow.pchysioterapist.api.repository.NewsletterRepository;

import java.io.IOException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class NewsletterServiceImpl implements NewsletterService{

    private final NewsletterRepository newsletterRepository;

    private final EmailService emailService;

    @Override
    public boolean isSignedIn(String email) {
        Newsletter newsletter = newsletterRepository.findByUserEmail(email);
        return newsletter != null;
    }

    @Override
    public Newsletter signToNewsletter(String userEmail) throws MessagingException, IOException {
        Newsletter newsletter = newsletterRepository.findByUserEmail(userEmail);
        if (newsletter == null){
            Newsletter newNewsletter = new Newsletter();
            newNewsletter.setUserEmail(userEmail);
            newNewsletter.setDeleteKey(UUID.randomUUID());
            newsletterRepository.save(newNewsletter);
            emailService.sendNewsletterConfirmation(newNewsletter.getUserEmail(), newNewsletter.getDeleteKey());
            return newNewsletter;
        } else {
            throw new UserIsSignedToNewsletterException("Użytkownik jest już zapisany do newslettera!");
        }
    }

    @Override
    public void signOutFromNewsletter(String userEmail) {
        Newsletter newsletter = newsletterRepository.findByUserEmail(userEmail);
        if (newsletter == null)
            throw new EmailDoesNotExistException("Użytkownik z danym emailem nie jest zapisany do newslettera.");
        newsletterRepository.delete(newsletter);
    }

    @Override
    @Transactional
    public void signOutFromNewsletterByEmail(UUID deleteKey) {
        newsletterRepository.deleteByDeleteKey(deleteKey);
    }
}
