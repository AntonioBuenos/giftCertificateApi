package by.smirnov.giftcertapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class TagRequest {

    private long id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
}
