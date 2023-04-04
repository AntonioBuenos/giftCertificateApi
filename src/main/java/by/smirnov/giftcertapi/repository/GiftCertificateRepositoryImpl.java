package by.smirnov.giftcertapi.repository;

import by.smirnov.giftcertapi.domain.GiftCertificate;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GiftCertificateRepositoryImpl implements GiftCertificateRepository {

    private final SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @Override
    public Optional<GiftCertificate> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(GiftCertificate.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<GiftCertificate> findAll() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery(
                "select p from GiftCertificate p order by p.id", GiftCertificate.class
        ).getResultList();
    }

    @Transactional
    @Override
    public GiftCertificate create(GiftCertificate object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
        return session.get(GiftCertificate.class, object.getId());
    }

    @Transactional
    @Override
    public GiftCertificate update(GiftCertificate object) {
        Session session = sessionFactory.getCurrentSession();
        GiftCertificate objectToBeUpdated = session.get(GiftCertificate.class, object.getId());
        objectToBeUpdated.setLastUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
        objectToBeUpdated.setName(object.getName());
        objectToBeUpdated.setDescription(object.getDescription());
        objectToBeUpdated.setPrice(object.getPrice());
        objectToBeUpdated.setDuration(object.getDuration());

        return objectToBeUpdated;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(GiftCertificate.class, id));
    }
}
