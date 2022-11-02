package it.emanuele.crud.service;

import java.util.List;
import java.util.Optional;

import it.emanuele.crud.model.ContoCorrente;
import it.emanuele.crud.model.Correntista;


public interface ContoCorrenteService {

	List<ContoCorrente> getAllConti();
	void saveConto(ContoCorrente conto);
	List<ContoCorrente> findAllByCorrentista(Correntista correntista);
	ContoCorrente getContoById(Integer id);


	ContoCorrente getContoByNumeroConto(String numeroconto);
	Optional<ContoCorrente> findByNumeroConto(String numeroconto);
}
