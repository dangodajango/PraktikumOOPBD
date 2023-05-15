package uni.plovdiv.database.application.dto.shoe;

import java.util.List;

public class ShoeUpdateDto {

    private String name;

    private String imageURL;

    private short price;

    private short minSize;

    private short maxSize;

    private Long brandId;

    private List<Long> categoryIds;
}
