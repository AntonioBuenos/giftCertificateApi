package by.smirnov.giftcertapi.dto;

import by.smirnov.giftcertapi.domain.GiftCertificate;

public interface GiftCertificateConverter {

    GiftCertificate convert(GiftCertificateRequest request);
    GiftCertificateResponse convert(GiftCertificate object);
}
