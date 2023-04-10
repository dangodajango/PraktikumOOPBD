package uni.plovdiv.database.application.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

public class CategoryModel {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Description", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "categories")
    private List<ShoeModel> shoes;
}
