package it.emanuele.crud.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.emanuele.crud.model.ContoCorrente;
import it.emanuele.crud.model.Correntista;


	// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
	// CRUD refers Create, Read, Update, Delete

@Repository
	public interface ContoCorrenteRepository extends JpaRepository<ContoCorrente, Integer> {

	List<ContoCorrente> findAllByCorrentista(Correntista correntista);


	ContoCorrente getContoByNumeroConto(String numeroconto);
	Optional<ContoCorrente> findByNumeroConto(String numeroconto);
			 

		
	}
	
	
	
	
	
	
	
	

