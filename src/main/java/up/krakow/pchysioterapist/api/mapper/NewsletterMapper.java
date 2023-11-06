package up.krakow.pchysioterapist.api.mapper;

import org.mapstruct.Mapper;
import up.krakow.pchysioterapist.api.dto.NewsletterDTO;
import up.krakow.pchysioterapist.api.model.Newsletter;

@Mapper(componentModel = "spring")
public interface NewsletterMapper {

    Newsletter mapToNewsletter(NewsletterDTO dto);

    NewsletterDTO mapToNewsletterDTO(Newsletter newsletter);
}
