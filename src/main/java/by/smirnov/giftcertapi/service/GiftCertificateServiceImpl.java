package by.smirnov.giftcertapi.service;

import by.smirnov.giftcertapi.domain.GiftCertificate;
import by.smirnov.giftcertapi.repository.GiftCertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateRepository repository;

    @Override
    public GiftCertificate findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<GiftCertificate> findAll() {
        return repository.findAll();
    }

    @Override
    public GiftCertificate create(GiftCertificate object) {
        return repository.create(object);
    }

    @Override
    public GiftCertificate update(GiftCertificate toBeUpdated) {
        return repository.update(toBeUpdated);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
