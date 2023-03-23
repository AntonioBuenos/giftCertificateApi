package by.smirnov.giftcertapi.repository;

import by.smirnov.giftcertapi.domain.GiftCertificate;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GiftCertificateRepositoryImpl implements GiftCertificateRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final GiftCertificateRowMapper giftCertificateMapper;

    @Override
    public Optional<GiftCertificate> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from certificates.gift_certificate where id = " + id, giftCertificateMapper));
    }

    @Override
    public List<GiftCertificate> findAll() {
        return jdbcTemplate.query("select * from certificates.gift_certificate order by id", giftCertificateMapper);
    }

    @Override
    public GiftCertificate create(GiftCertificate object) {
        final String insertQuery =
                "insert into certificates.gift_certificate (name, description, price, duration, create_date) " +
                        "values (:name, :description, :price, :duration, now());";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", object.getName());
        mapSqlParameterSource.addValue("description", object.getDescription());
        mapSqlParameterSource.addValue("price", object.getPrice());
        mapSqlParameterSource.addValue("duration", object.getDuration());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Long lastInsertId = namedParameterJdbcTemplate.query("select currval('certificates.gift_certificate_id_seq') as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastInsertId).orElse(null);
    }

    @Override
    public GiftCertificate update(GiftCertificate object) {
        Long id = object.getId();
        jdbcTemplate.update(
                "update certificates.gift_certificate " +
                        "set name=?, description=?, price=?, duration=?, last_update_date=now() where id=?",
                object.getName(), object.getDescription(), object.getPrice(), object.getDuration(), id);
        return findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from certificates.gift_certificate where id=?", id);
    }
}
