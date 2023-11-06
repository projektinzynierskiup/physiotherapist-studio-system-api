package up.krakow.pchysioterapist.api.service;

import up.krakow.pchysioterapist.api.model.Newsletter;

public interface NewsletterService {

    Newsletter signToNewsletter(String userEmail);
}
