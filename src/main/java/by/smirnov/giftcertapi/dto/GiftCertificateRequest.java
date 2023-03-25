package by.smirnov.giftcertapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiftCertificateRequest {
    private String name;
    private String description;
    private double price;
    private Integer duration;
}
