package umc.nini.domain;

import jakarta.persistence.*;
import lombok.Getter;
import umc.nini.domain.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reward;

    private LocalDateTime deadline;

    private String missionSpec;


    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MemberMission> memberMission =new ArrayList<>();

}
