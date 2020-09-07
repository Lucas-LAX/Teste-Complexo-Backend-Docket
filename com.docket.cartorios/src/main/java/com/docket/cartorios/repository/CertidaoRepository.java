package com.docket.cartorios.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.docket.cartorios.model.Certidao;

public interface CertidaoRepository extends CrudRepository<Certidao, Long> {
	
	@Query("select u from Certidao u where cartorio_id = :id")
	List<Certidao> findByCartorio(@Param("id") Long id);


}
