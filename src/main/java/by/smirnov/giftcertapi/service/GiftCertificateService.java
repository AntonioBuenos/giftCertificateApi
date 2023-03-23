package by.smirnov.giftcertapi.service;

import by.smirnov.giftcertapi.domain.GiftCertificate;

import java.util.List;

public interface GiftCertificateService {

    GiftCertificate findById(Long id);

    List<GiftCertificate> findAll();

    GiftCertificate create(GiftCertificate object);

    GiftCertificate update(GiftCertificate toBeUpdated);

    void delete(Long id);
}
