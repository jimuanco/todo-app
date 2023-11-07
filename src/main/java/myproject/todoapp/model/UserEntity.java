package myproject.todoapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String username;

    private String password; //SSO 구현 위해 null 허용, 회원 가입 controller에서 password를 반드시 입력하도록 구현

    private String role;

    private String authProvider; //이후 OAuth에서 사용할 유저 정보 제공자

}
