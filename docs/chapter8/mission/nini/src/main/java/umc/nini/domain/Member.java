package umc.nini.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.nini.domain.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String gender;

    private String address;

    private String status;

    private String social_type;

    private String email;

    private Long point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MemberPrefer> memberPrefer= new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MemberAgree> memberAgree= new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MemberMission> membeMission= new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> review= new ArrayList<>();


//    @Builder
//    private Member(Long id,String name,String gender,String address,String status,String social_type,String email,Long point)
//    {
//        this.id=id;
//        this.name=name;
//        this.gender=gender;
//        this.address=address;
//        this.status=status;
//        this.social_type=social_type;
//        this.email=email;
//        this.point=point;
//    }
}
