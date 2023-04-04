package by.smirnov.giftcertapi.repository;

import by.smirnov.giftcertapi.domain.GiftCertificate;
import by.smirnov.giftcertapi.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository {

    private final SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @Override
    public Optional<Tag> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(Tag.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Tag> findAll() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery(
                "select p from Tag p order by p.id", Tag.class
        ).getResultList();
    }

    @Transactional
    @Override
    public Tag create(Tag object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
        return session.get(Tag.class, object.getId());
    }

    @Transactional
    @Override
    public Tag update(Tag object) {
        Session session = sessionFactory.getCurrentSession();
        Tag objectToBeUpdated = session.get(Tag.class, object.getId());
        objectToBeUpdated.setName(object.getName());

        return objectToBeUpdated;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(GiftCertificate.class, id));
    }
}
