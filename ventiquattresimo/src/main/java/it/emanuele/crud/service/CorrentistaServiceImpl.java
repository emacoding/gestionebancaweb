package it.emanuele.crud.service;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.emanuele.crud.model.Correntista;
import it.emanuele.crud.repository.CorrentistaRepository;
import java.util.Optional;
@Service
public class CorrentistaServiceImpl implements CorrentistaService{

	@Autowired
	private CorrentistaRepository correntistarepo;

	@Override
	public List<Correntista> getAllCorrentisti() {
		// TODO Auto-generated method stub
		return correntistarepo.findAll();
	}
	
	

	@Override
	public void saveCorrentista(Correntista correntista) {
		// TODO Auto-generated method stub
		this.correntistarepo.save(correntista);

	}


	@Override
	public Correntista getCorrentistaById(Integer id) {
		// TODO Auto-generated method stub
		 Optional<Correntista> optional = correntistarepo.findById(id);
		 Correntista correntista = null;
		if(optional.isPresent())
		{
			
			correntista = optional.get();
			
			
		}
	
		else{
			
			throw new RuntimeException("Correntista non trovato");
			
		}
		
		return correntista;

		
		
		
		
		
	}



	@Override
	public void deleteCorrentistaById(Integer id) {
		// TODO Auto-generated method stub
		this.correntistarepo.deleteById(id);
	}



	@Override
	public Correntista getCorrentistaByCodicefiscale(String codicefiscale) {
		
		
		 Optional<Correntista> optional = correntistarepo.findByCodicefiscale(codicefiscale);
		 Correntista correntista = null;
		if(optional.isPresent())
		{
			
			correntista = optional.get();
			
			
		}
	
		else{
			
			throw new RuntimeException("Correntista non trovato");
			
		}
		
		return correntista;

		
		
		
		
	}



	@Override
	public Optional<Correntista> findByCodicefiscale(String codicefiscale) {
		return Optional.empty();
	}



	


	@Override
	public Page<Correntista> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort .by(sortField).ascending():
			Sort .by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		return this.correntistarepo.findAll(pageable);
	}




}
	
	

