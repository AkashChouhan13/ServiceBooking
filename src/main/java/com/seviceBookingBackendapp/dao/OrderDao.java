package com.seviceBookingBackendapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seviceBookingBackendapp.modal.OrderDetails;

public interface OrderDao extends JpaRepository<OrderDetails, Integer>
{
    List<OrderDetails> findByEmailId(String emailId);

	List<OrderDetails> findByCompanyName(String compname);

}
