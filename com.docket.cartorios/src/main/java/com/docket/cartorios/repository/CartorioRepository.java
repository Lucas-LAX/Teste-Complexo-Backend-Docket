package com.docket.cartorios.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.docket.cartorios.model.Cartorio;

public interface CartorioRepository extends CrudRepository<Cartorio, Long>{
	
	@Query("select a from Cartorio a where id = :id")
	List<Cartorio> findByExtraId(@Param("id") Long id);
	
	@Query("select a from Cartorio a where id = :id")
	Cartorio findByExtraIdCar(@Param("id") Long id2);
	


}
