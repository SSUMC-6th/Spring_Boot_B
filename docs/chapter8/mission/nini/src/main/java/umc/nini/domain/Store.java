package umc.nini.domain;

import jakarta.persistence.*;
import lombok.Getter;
import umc.nini.domain.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private Long score;


    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> review=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;



}
