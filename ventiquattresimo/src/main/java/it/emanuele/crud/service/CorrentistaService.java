package it.emanuele.crud.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import it.emanuele.crud.model.Correntista;

public interface CorrentistaService {



		List<Correntista> getAllCorrentisti();
		void saveCorrentista(Correntista correntista);
		Correntista getCorrentistaById(Integer id);
		void deleteCorrentistaById(Integer id);
		Correntista getCorrentistaByCodicefiscale(String codicefiscale);
		Optional<Correntista> findByCodicefiscale(String codicefiscale);
		Page<Correntista> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
		
		
	}

