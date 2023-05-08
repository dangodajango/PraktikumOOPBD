package uni.plovdiv.database.application.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
public class Shoe {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "release_year")
    private short releaseYear;

    @Column(name = "price")
    private short price;

    @Column(name = "image_url")
    private String URL;

    @ManyToOne
    @JoinTable(
            name = "shoe_brand",
            joinColumns = @JoinColumn(name = "shoe_id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id")
    )
    private Brand brands;

    @ManyToMany
    @JoinTable(
            name = "shoes_categories",
            joinColumns = @JoinColumn(name = "shoe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
}