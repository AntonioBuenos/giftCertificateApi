package by.smirnov.giftcertapi.repository;

import by.smirnov.giftcertapi.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends
        CrudRepository<Long, Tag>,
        JpaRepository<Long, Tag> {
}
