package com.seviceBookingBackendapp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seviceBookingBackendapp.modal.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer>
{
	Optional<Company> findByCompEmail(String compEmail);
}
