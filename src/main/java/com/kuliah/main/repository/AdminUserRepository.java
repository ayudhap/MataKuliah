package com.kuliah.main.repository;

import org.springframework.data.repository.CrudRepository;

import com.kuliah.main.entity.AdminUser;

public interface AdminUserRepository extends CrudRepository<AdminUser, Long> {
	AdminUser findByUsername(String username);
	AdminUser findByIdAdmin(long id);

}
