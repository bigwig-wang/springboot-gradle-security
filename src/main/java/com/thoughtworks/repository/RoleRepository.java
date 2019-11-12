package com.thoughtworks.repository;

import com.thoughtworks.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {

    @Query(value = "select * from role where id in (select role_id from user_role where user_id = ?1)", nativeQuery = true)
    List<Role> findRolesByUserId(String userId);
}
