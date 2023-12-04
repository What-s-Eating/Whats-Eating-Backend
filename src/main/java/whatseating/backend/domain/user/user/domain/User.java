package whatseating.backend.domain.user.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import whatseating.backend.domain.user.user.domain.enums.Provider;
import whatseating.backend.domain.user.user.domain.enums.Role;
import whatseating.backend.global.entity.BaseTimeEntity;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "user")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Size(min = 2, max = 20)
    @Column(nullable = false)
    private String name;

    @Size(min = 2, max = 50)
    @Column(nullable = false, unique = true)
    private String email;

    @Size(min = 2, max = 100)
    @Column(nullable = false)
    private String password;

    @Column
    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Provider provider;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String id, String name, String email, String password, String profileImage, Provider provider, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
        this.provider = provider;
        this.role = role;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
