package me.bajmo.kanbaniser.services.tasks.models;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.bajmo.kanbaniser.services.tasks.entities.User;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    @NotBlank
    @Size(max = 50)
    private String firstName;
    @NotBlank
    @Size(max = 50)
    private String lastName;
    @NotBlank
    @Size(max = 50)
    private String phoneNumber;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    @Email
    @Size(max = 50)
    private String email;
    @NotBlank
    @Size(min = 6)
    private String password;
    @NotBlank
    @Size(max = 250)
    private String image;
    private List<String> roles; // Assuming you want to store role names as strings

    public void setUserDTOFromUser(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phoneNumber = user.getPhoneNumber();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.image = user.getImage();
        // Map the list of Role entities to role names
        List<String> list = new ArrayList<>();
        for (Role role : user.getRoles()) {
            ERole name = role.getName();
            list.add(String.valueOf(name));
        }
        this.roles = list;
    }

}
