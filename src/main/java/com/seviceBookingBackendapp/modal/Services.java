package com.seviceBookingBackendapp.modal;

import jakarta.persistence.FetchType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "services")
public class Services 
{
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private int prices;
	    private String description;

//	    @ManyToOne(fetch = FetchType.LAZY)
//	    @JoinColumn(name = "user_id")
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "compId")
	    private Company company;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getPrices() {
			return prices;
		}

		public void setPrices(int prices) {
			this.prices = prices;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Company getCompany() {
			return company;
		}

		public void setCompany(Company company) {
			this.company = company;
		}
	    
		
		public Services()
		{
			
		}

		public Services(String name, int prices, String description) {
			super();
			this.name = name;
			this.prices = prices;
			this.description = description;
		}
	    
		

}
