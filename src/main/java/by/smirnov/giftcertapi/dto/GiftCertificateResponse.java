package by.smirnov.giftcertapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class GiftCertificateResponse {
    private long id;
    private String name;
    private String description;
    private double price;
    private Integer duration;
    private Timestamp createDate;
    private Timestamp lastUpdateDate;
}
