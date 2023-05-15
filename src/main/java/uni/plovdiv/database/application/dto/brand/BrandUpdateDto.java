package uni.plovdiv.database.application.dto.brand;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BrandUpdateDto {

    private String name;

    private String description;
}
