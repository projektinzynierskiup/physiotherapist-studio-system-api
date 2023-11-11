package up.krakow.pchysioterapist.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.exception.EmailDoesNotExistException;
import up.krakow.pchysioterapist.api.exception.UserIsSignedToNewsletterException;
import up.krakow.pchysioterapist.api.model.Newsletter;
import up.krakow.pchysioterapist.api.repository.NewsletterRepository;

@Service
@AllArgsConstructor
public class NewsletterServiceImpl implements NewsletterService{

    private final NewsletterRepository newsletterRepository;

    @Override
    public Newsletter signToNewsletter(String userEmail) {
        Newsletter newsletter = newsletterRepository.findByUserEmail(userEmail);
        if (newsletter == null){
            Newsletter newNewsletter = new Newsletter();
            newNewsletter.setUserEmail(userEmail);
            newsletterRepository.save(newNewsletter);
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
}
