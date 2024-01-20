package me.bajmo.kanbaniser.services.boards.models;

import lombok.*;

@Data
@NoArgsConstructor
public class RoleDTO {

    private Long id;
    private String name;

    public void setRoleDTOFromRole(Role role) {
        this.id = role.getId();
        this.name = role.getName().name();
    }

}
