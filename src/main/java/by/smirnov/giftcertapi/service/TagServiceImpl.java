package by.smirnov.giftcertapi.service;

import by.smirnov.giftcertapi.domain.Tag;
import by.smirnov.giftcertapi.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository repository;

    @Override
    public Tag findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Tag> findAll() {
        return repository.findAll();
    }

    @Override
    public Tag create(Tag object) {
        return repository.save(object);
    }

    @Override
    public Tag update(Tag toBeUpdated) {
        return repository.save(toBeUpdated);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
