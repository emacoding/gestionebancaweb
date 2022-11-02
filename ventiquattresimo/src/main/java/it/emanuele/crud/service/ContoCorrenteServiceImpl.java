package it.emanuele.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.emanuele.crud.model.ContoCorrente;
import it.emanuele.crud.model.Correntista;
import it.emanuele.crud.repository.ContoCorrenteRepository;


@Service
public class ContoCorrenteServiceImpl implements ContoCorrenteService {

	
	@Autowired
	private ContoCorrenteRepository contorepo;
	@Override
	public List<ContoCorrente> getAllConti() {
		// TODO Auto-generated method stub
		return contorepo.findAll();
	}
	@Override
	public void saveConto(ContoCorrente conto) {

		 this.contorepo.save(conto);

	}
	
	



	public 	List<ContoCorrente> findAllByCorrentista(Correntista correntista) 
 {
		// TODO Auto-generated method stub
	
		return contorepo.findAllByCorrentista(correntista);
		
	}
	
	
	
	@Override
	public ContoCorrente getContoById(Integer id) {
		
		
		
		 Optional<ContoCorrente> optional = contorepo.findById(id);
		 ContoCorrente conto = null;
		if(optional.isPresent())
		{
			
			conto = optional.get();
			
			
		}
	
		else{
			
			throw new RuntimeException("Conto non trovato");
			
		}
		
		return conto;

		
		
	}
	@Override
	public ContoCorrente getContoByNumeroConto(String numeroconto) {
			

		 Optional<ContoCorrente> optional = contorepo.findByNumeroConto(numeroconto);
		ContoCorrente conto = null;
		if(optional.isPresent())
		{
			
			conto = optional.get();
			
			
		}
	
		else{
			
			throw new RuntimeException("Conto non trovato");
			
		}
		
		return conto;

		

	}
	@Override
	public Optional<ContoCorrente> findByNumeroConto(String numeroconto) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}






	

}
