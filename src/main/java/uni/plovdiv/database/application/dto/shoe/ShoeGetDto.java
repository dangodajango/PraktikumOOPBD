package uni.plovdiv.database.application.dto.shoe;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ShoeGetDto {

    private Long id;

    private String name;

    private String imageURL;

    private short price;

    private short minSize;

    private short maxSize;

    private Long brandId;

    private List<Long> categoryIds;
}
