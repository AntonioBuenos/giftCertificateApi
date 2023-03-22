package by.smirnov.giftcertapi.repository;

import by.smirnov.giftcertapi.domain.GiftCertificate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GiftCertificateRepositoryImpl implements GiftCertificateRepository {
    @Override
    public GiftCertificate findById(Long id) {
        return null;
    }

    @Override
    public Optional<GiftCertificate> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public List<GiftCertificate> findAll() {
        return null;
    }

    @Override
    public GiftCertificate create(GiftCertificate object) {
        return null;
    }

    @Override
    public GiftCertificate update(GiftCertificate object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }
}
