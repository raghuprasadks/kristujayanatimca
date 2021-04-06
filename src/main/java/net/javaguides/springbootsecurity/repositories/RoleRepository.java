package net.javaguides.springbootsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springbootsecurity.entities.Role;
public interface RoleRepository extends JpaRepository<Role,Integer>{

}
