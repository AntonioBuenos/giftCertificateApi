package by.smirnov.giftcertapi.repository;

import by.smirnov.giftcertapi.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository {
    @Override
    public Tag findById(Long id) {
        return null;
    }

    @Override
    public Optional<Tag> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Tag> findAll() {
        return null;
    }

    @Override
    public Tag create(Tag object) {
        return null;
    }

    @Override
    public Tag update(Tag object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }
}
