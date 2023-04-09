package by.smirnov.giftcertapi.repository;

import by.smirnov.giftcertapi.domain.GiftCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface GiftCertificateRepository extends
        CrudRepository<Long, GiftCertificate>,
        JpaRepository <Long, GiftCertificate> {
}
