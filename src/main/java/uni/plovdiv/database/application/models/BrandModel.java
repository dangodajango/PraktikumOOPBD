package uni.plovdiv.database.application.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@ToString
public class BrandModel {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Location", nullable = false)
    private String location;

    @ManyToMany(mappedBy = "brands")
    private List<ShoeModel> shoes;
}