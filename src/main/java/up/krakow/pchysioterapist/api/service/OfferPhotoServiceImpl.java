package up.krakow.pchysioterapist.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.dto.OfferPhotoDTO;
import up.krakow.pchysioterapist.api.model.OfferPhoto;
import up.krakow.pchysioterapist.api.repository.OfferPhotoRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OfferPhotoServiceImpl implements OfferPhotoService{

    private final OfferPhotoRepository offerPhotoRepository;

    @Override
    public OfferPhoto addOfferPhoto(OfferPhotoDTO dto) {
        OfferPhoto offerPhoto = new OfferPhoto();
        offerPhoto.setPhotoByte(dto.getPhotoByte());
        offerPhoto.setPhotoName(dto.getPhotoName());
        offerPhoto.setPhotoType(dto.getPhotoType());
        offerPhotoRepository.save(offerPhoto);
        return offerPhoto;
    }

    @Override
    public OfferPhoto getOfferPhoto(Integer offerPhotoId) {
        return offerPhotoRepository.findById(offerPhotoId).orElseThrow(() ->
                new NoSuchElementException("Photo with id: " + offerPhotoId + "does not exist"));
    }

    @Override
    public OfferPhoto updateOfferPhoto(OfferPhotoDTO dto, Integer offerPhotoId) {
        OfferPhoto offerPhoto = offerPhotoRepository.findById(offerPhotoId).orElseThrow(() ->
                new NoSuchElementException("Photo with id: " + offerPhotoId + "does not exist"));
        offerPhoto.setPhotoByte(dto.getPhotoByte());
        offerPhoto.setPhotoName(dto.getPhotoName());
        offerPhoto.setPhotoType(dto.getPhotoType());
        offerPhotoRepository.save(offerPhoto);
        return offerPhoto;
    }

    @Override
    public void deleteOfferPhoto(Integer offerPhotoId) {
        offerPhotoRepository.deleteById(offerPhotoId);
    }
}
