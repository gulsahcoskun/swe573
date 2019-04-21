package com.boun.glearn.repository;

import com.boun.glearn.model.Role;
import com.boun.glearn.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(RoleName roleName);

}
