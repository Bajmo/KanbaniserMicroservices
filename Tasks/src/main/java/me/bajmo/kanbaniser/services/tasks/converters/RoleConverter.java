package me.bajmo.kanbaniser.services.tasks.converters;


import me.bajmo.kanbaniser.services.tasks.models.Role;
import me.bajmo.kanbaniser.services.tasks.models.RoleDTO;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    public RoleDTO convertRoleToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleDTOFromRole(role);
        return roleDTO;
    }

    public Role convertDTOToRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleFromRoleDTO(roleDTO);
        return role;
    }

}
