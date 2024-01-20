package me.bajmo.kanbaniser.services.tasks.repositories;

import me.bajmo.kanbaniser.services.tasks.models.ERole;
import me.bajmo.kanbaniser.services.tasks.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

}
