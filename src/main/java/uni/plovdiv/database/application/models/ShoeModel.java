package uni.plovdiv.database.application.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
public class ShoeModel {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "Release year")
    private short releaseYear;

    @Column(name = "Price")
    private short price;

    @Column(name = "Image URL")
    private String URL;

    @ManyToOne
    @JoinTable(
            name = "shoe_brand",
            joinColumns = @JoinColumn(name = "shoe_id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id")
    )
    private List<BrandModel> brands;

    @ManyToMany
    @JoinTable(
            name = "shoes_categories",
            joinColumns = @JoinColumn(name = "shoe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CategoryModel> categories;
}