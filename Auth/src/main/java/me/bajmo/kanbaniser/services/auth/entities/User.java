package me.bajmo.kanbaniser.services.auth.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import me.bajmo.kanbaniser.services.auth.models.ERole;
import me.bajmo.kanbaniser.services.auth.models.Role;
import me.bajmo.kanbaniser.services.auth.models.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_to_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public User(String firstName, String lastName, String phoneNumber, String username, String email, String password, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public void setUserFromUserDTO(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.username = userDTO.getUsername();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.image = userDTO.getImage();
        // Map roles directly without using RoleConverter
        this.roles = userDTO.getRoles().stream()
                .map(roleName -> {
                    Role role = new Role();
                    role.setName(ERole.valueOf(roleName));
                    return role;
                })
                .collect(Collectors.toList());
        // Add any additional mapping logic based on your entity structure
    }

}
