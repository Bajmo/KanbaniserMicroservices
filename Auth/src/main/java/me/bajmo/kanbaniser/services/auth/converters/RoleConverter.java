package me.bajmo.kanbaniser.services.auth.converters;

import me.bajmo.kanbaniser.services.auth.models.Role;
import me.bajmo.kanbaniser.services.auth.models.RoleDTO;

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
