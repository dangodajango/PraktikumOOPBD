package uni.plovdiv.database.application.dto.category;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryUpdateDto {

    private Long id;

    private String title;

    private String description;
}
