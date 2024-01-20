package me.bajmo.kanbaniser.services.tasks.converters;

import me.bajmo.kanbaniser.services.tasks.entities.User;
import me.bajmo.kanbaniser.services.tasks.models.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDTO convertUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserDTOFromUser(user);
        return userDTO;
    }

    public User convertDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setUserFromUserDTO(userDTO);
        return user;
    }

}
