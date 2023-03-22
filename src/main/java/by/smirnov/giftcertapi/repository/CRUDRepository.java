package by.smirnov.giftcertapi.repository;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository <K, T>{

    T findById(K id);
    Optional<T> findOne(K id);
    List<T> findAll();
    T create(T object);
    T update(T object);
    K delete(K id);
}
