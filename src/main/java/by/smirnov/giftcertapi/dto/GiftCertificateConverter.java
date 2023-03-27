package by.smirnov.giftcertapi.dto;

import by.smirnov.giftcertapi.domain.GiftCertificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Duration;

@Mapper
public interface GiftCertificateConverter {

    @Mapping(target = "duration", expression = "java( java.time.Duration.ofDays ( request.getDuration() ) )")
    GiftCertificate convert(GiftCertificateRequest request);

    @Mapping(target = "duration", expression = "java( (int) object.getDuration().toDays() )")
    GiftCertificateResponse convert(GiftCertificate object);

}
