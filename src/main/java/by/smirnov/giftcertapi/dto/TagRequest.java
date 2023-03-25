package by.smirnov.giftcertapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TagRequest {

    private long id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
}
