package by.smirnov.giftcertapi.repository;

import by.smirnov.giftcertapi.domain.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

@Component
public class GiftCertificateRowMapper implements RowMapper<GiftCertificate> {

    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        return GiftCertificate.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .price(rs.getDouble("price"))
                .duration(Duration.ofDays(rs.getInt("duration")))
                .createDate(rs.getTimestamp("create_date"))
                .lastUpdateDate(rs.getTimestamp("last_update_date"))
                .build();
    }
}
