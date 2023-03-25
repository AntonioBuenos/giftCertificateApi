package by.smirnov.giftcertapi.dto;

import by.smirnov.giftcertapi.domain.GiftCertificate;
import org.mapstruct.Mapper;

@Mapper
public interface GiftCertificateConverter {

    GiftCertificate convert(GiftCertificateRequest request);
    GiftCertificateResponse convert(GiftCertificate object);
}
