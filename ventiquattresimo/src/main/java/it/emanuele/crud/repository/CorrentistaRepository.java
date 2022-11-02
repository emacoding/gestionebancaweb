package it.emanuele.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.emanuele.crud.model.Correntista;


	
	
	

	// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
	// CRUD refers Create, Read, Update, Delete

	public interface CorrentistaRepository extends JpaRepository<Correntista, Integer> {

		Correntista getCorrentistaByCodicefiscale(String codicefiscale);
		Optional<Correntista> findByCodicefiscale(String codicefiscale);


		
	}
	
	
	
	