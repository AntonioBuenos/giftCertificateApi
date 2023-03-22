package by.smirnov.giftcertapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Duration;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GiftCertificate {

    private long id;
    private String name;
    private String description;
    private double price;
    private Duration duration;
    private Timestamp createDate;
    private Timestamp lastUpdateDate;
}
