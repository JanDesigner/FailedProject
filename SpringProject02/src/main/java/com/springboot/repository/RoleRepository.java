package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.model.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role,Integer>{
		
	public Role getByRole(String role);
	
}
