package com.seviceBookingBackendapp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seviceBookingBackendapp.modal.User;

public interface UserRepo extends JpaRepository<User, String>
{
	Optional<User> findByEmailId(String email);


}
