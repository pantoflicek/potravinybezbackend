package cz.vse.potravinyBEZ.repository.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "user_role")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleEntity {
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}
