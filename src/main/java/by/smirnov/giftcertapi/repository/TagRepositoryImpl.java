package by.smirnov.giftcertapi.repository;

import by.smirnov.giftcertapi.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final TagRowMapper tagRowMapper;

    @Override
    public Optional<Tag> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from certificates.tag where id = " + id, tagRowMapper));
    }
    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query("select * from certificates.tag order by id", tagRowMapper);
    }

    @Override
    public Tag create(Tag object) {
        final String insertQuery =
                "insert into certificates.tag (name) " +
                        " values (:name);";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", object.getName());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Long lastInsertId = namedParameterJdbcTemplate.query("select currval('certificates.tag_id_seq') as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastInsertId).orElse(null);
    }

    @Override
    public Tag update(Tag object) {
        Long id = object.getId();
        jdbcTemplate.update("update certificates.tag set name=? where id=?",
                object.getName(), id);
        return findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from certificates.tag where id=?", id);
    }
}
