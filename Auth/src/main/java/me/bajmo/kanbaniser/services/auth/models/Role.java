package me.bajmo.kanbaniser.services.auth.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }

    public void setRoleFromRoleDTO(RoleDTO roleDTO) {
        this.id = roleDTO.getId();
        this.name = ERole.valueOf(roleDTO.getName());
        // Add any additional mapping logic based on your entity structure
    }

}
