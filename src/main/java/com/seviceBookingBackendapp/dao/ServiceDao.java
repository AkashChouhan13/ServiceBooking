package com.seviceBookingBackendapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seviceBookingBackendapp.modal.Services;

public interface ServiceDao extends JpaRepository<Services, Long>
{

	List<Services> findAllByName(String servName);

}
