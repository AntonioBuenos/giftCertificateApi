package by.smirnov.giftcertapi.dto;

import by.smirnov.giftcertapi.domain.GiftCertificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GiftCertificateConverter {

    @Mapping(target = "duration", expression = "java( Duration.ofDays ( request.getDuration() )")
    GiftCertificate convert(GiftCertificateRequest request);

    @Mapping(target = "duration", expression = "java( request.getDuration().toDays() )")
    GiftCertificateResponse convert(GiftCertificate object);

}
