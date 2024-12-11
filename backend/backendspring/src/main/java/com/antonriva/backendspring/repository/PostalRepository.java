package com.antonriva.backendspring.repository;

import com.antonriva.backendspring.model.Postal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostalRepository extends JpaRepository<Postal, Long> {
	

}
