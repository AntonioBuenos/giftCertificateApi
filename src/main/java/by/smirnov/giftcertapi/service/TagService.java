package by.smirnov.giftcertapi.service;

import by.smirnov.giftcertapi.domain.Tag;

import java.util.List;

public interface TagService {

    Tag findById(Long id);

    List<Tag> findAll();

    Tag create(Tag object);

    Tag update(Tag toBeUpdated);

    void delete(Long id);
}
