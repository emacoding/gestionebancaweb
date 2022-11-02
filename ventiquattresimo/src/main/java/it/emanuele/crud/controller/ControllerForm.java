package it.emanuele.crud.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.emanuele.crud.model.ContoCorrente;
import it.emanuele.crud.model.Correntista;
import it.emanuele.crud.service.CorrentistaService;
import it.emanuele.crud.service.ContoCorrenteService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller


public class ControllerForm {

	

		
		  @Autowired 
		  private ContoCorrenteService contoservice;

		  @Autowired 
		  private CorrentistaService correntistaservice;
			

			
			

			@GetMapping("/formCorrentista")
			   public  String formcorrentista(Model model) {
					
				Correntista correntista = new Correntista();
				
					model.addAttribute("correntista", correntista);

			        return "formcorrentista";
			    }
			

			
			
			@GetMapping("/formConto")
			   public  String formConto(Model model ) {
					
				ContoCorrente conto = new ContoCorrente();
				
					model.addAttribute("listacorrentisti", correntistaservice.getAllCorrentisti());

					model.addAttribute("conto", conto);
			        return "formconto";
			    }

			@PostMapping("/salvaConto")
			   public  String salvaconto(@ModelAttribute("conto") ContoCorrente conto ) {
					
				
				try {
				
					contoservice.saveConto(conto);

					
					
			        return "redirect:/index";
				}
			        
			    	catch(Exception e) {
						 return "errore3";

					}
			    }
			
			
			@PostMapping("/salvaContoPrelievo")
			   public  String salvacontoprelievo(@ModelAttribute("conto") ContoCorrente conto,double saldo,  HttpServletRequest request ) {
					
				  double richiesto = Double.parseDouble(request.getParameter("richiesto"));

				if(richiesto <= saldo && richiesto>0 )
				{
					double  saldoaggiornato = saldo - richiesto;
					conto.setSaldo(saldoaggiornato);
					
					contoservice.saveConto(conto);
			        return "preleva";


				}
				else {

			        return "preleva";
					}
				

			    }
			
			

			@PostMapping("/salvaContoVersamento")
			   public  String salvacontoversamento(@ModelAttribute("conto") ContoCorrente conto,  double saldo,  HttpServletRequest request ) {
					
				  double versato = Double.parseDouble(request.getParameter("versato"));

				if(versato>0 )
				{
					double  saldoaggiornato = saldo + versato;
					conto.setSaldo(saldoaggiornato);
					
					contoservice.saveConto(conto);
			        return "versa";

				}
				else {
			        return "versa";


					}
				

			    }


			@PostMapping("/salvaCorrentista")
			   public  String salvacorrentista(@ModelAttribute("correntista") Correntista correntista ) {
					
				try {
					correntistaservice.saveCorrentista(correntista);
					 return "redirect:/index";

				}
				catch(Exception e) {
					 return "errore3";

				}
			       
			    }
			
			


			@PostMapping("/cercaCorrentista")
			   public  String cercacorrentista(Model model, HttpServletRequest request ) {
					
				  String codicefiscale= request.getParameter("codicefiscale");

				  try
				  {
					Correntista correntista = correntistaservice.getCorrentistaByCodicefiscale(codicefiscale);


					model.addAttribute("correntista", correntista);
					
			        return "modificacorrentista";
				  }
				  catch(Exception e) {
					  
				        return "errore2";

					  
				  }
				  
			    }


			@GetMapping("/modificaCorrentista/{id}")
			   public  String modificacorrentista(@PathVariable(value = "id") Integer id , Model model  ) {
					
				Correntista correntista = correntistaservice.getCorrentistaById(id);

				model.addAttribute("correntista", correntista);

			        return "modificacorrentista";
			    }
			
			
			@GetMapping("/contoCorrente/{id}")
			   public  String listaconti(@PathVariable(value = "id") Integer id , Model model  ) {
					

				Correntista correntista = correntistaservice.getCorrentistaById(id);

				
				model.addAttribute("listaconti", contoservice.findAllByCorrentista(correntista));

			        return "gestioneconti";
			    }
			
			@GetMapping("/preleva/{id}")
			   public  String prelievo(@PathVariable(value = "id") Integer id , Model model ) {
					
				ContoCorrente conto = contoservice.getContoById(id);
				  
				model.addAttribute("conto", conto);

			        return "preleva";
			    }
			

			@GetMapping("/versa/{id}")
			   public  String versa(@PathVariable(value = "id") Integer id , Model model ) {
					
				ContoCorrente conto = contoservice.getContoById(id);
				  
				model.addAttribute("conto", conto);

			        return "versa";
			    }
			

			@GetMapping("/eliminaCorrentista/{id}")
			   public  String eliminacorrentista(@PathVariable(value = "id") Integer id  ) {
					
				try {
				 correntistaservice.deleteCorrentistaById(id);
			        return "redirect:/index";

				}
				
				catch(Exception e)
				{

			        return "errore";

					
				}


			    }
			
			@GetMapping("/pagina/{pageNo}")

			public  String pagina(@PathVariable(value = "pageNo") int pageNo, Model model, 
					@RequestParam("campo") String campo, 				
					@RequestParam("ordine") String ordine) {
				

				
				int pageSize = 5;
				    
				Page<Correntista> page = correntistaservice.findPaginated(pageNo, pageSize, campo, ordine);
				List<Correntista> listacorrentisti = page.getContent();
				  
				model.addAttribute("paginaAttuale", pageNo);
				model.addAttribute("pagineTotali", page.getTotalPages());
				model.addAttribute("correntistiTotali", page.getTotalElements());
				model.addAttribute("listacorrentisti", listacorrentisti);

				model.addAttribute("campo", campo);
				model.addAttribute("ordine", ordine);
				model.addAttribute("ordineinverso", ordine.equals("asc") ? "desc" : "asc");

				
				
			        return "index";
			    }
			

			@GetMapping("/index")
		   public String index(Model model) {
				
		        return pagina(1, model, "nome", "asc");
		    }
			
			

			@PostMapping("/cercaConto")
			   public  String cercaconto(Model model, HttpServletRequest request ) {
					
				  String numeroconto= request.getParameter("numeroconto");

				  try
				  {
					ContoCorrente conto = contoservice.getContoByNumeroConto(numeroconto);


					model.addAttribute("conto", conto);
					
			        return "versa";
				  }
				  catch(Exception e) {
					  
				        return "errore2";

					  
				  }
				  
			    }
			
			
			
}
			
			
			
			
			/*
			@PostMapping
		    public String addUser(@Valid User user, BindingResult result, Model model) {
		        if (result.hasErrors()) {
		            return "add-user";
		        }
		        
		        userRepository.save(user);
		        return "redirect:/index";
		    }
			
			
			
			
		    @GetMapping("/index")
		    public String showUserList(Model model) {
		        model.addAttribute("users", userRepository.findAll());
		        return "index";
		    }
			
			
		  
		  @PostMapping("/inserimentoutente") // Map ONLY POST Requests
		  public String inserisciUtente(Map <String, String> utentemodel , 
					HttpServletRequest request, @ModelAttribute("Utente") Utente utente){
		    // @ResponseBody means the returned String is the response, not a view name
		    // @RequestParam means it is a parameter from the GET or POST request




		    repo.save(utente);

		    
		    return "stampa";
		    
		    

			
				
				Opzionalmente, stampare un messaggio nel caso in cui la data inserita non sia valida.
				
				
			
		  }
		  
		  

		
		  
		  
		  
		  @PostMapping("/ricerca") // Map ONLY POST Requests
		  public String modificaUtente(Map <String, String> utente){
		    // @ResponseBody means the returned String is the response, not a view name
		    // @RequestParam means it is a parameter from the GET or POST request

			 String codicericerca = request.getParameter("codicefiscalericerca");
			  
			 	  
			 	  
		    
			 	  
		    return "modifica";
		    
		    

			
				
				Opzionalmente, stampare un messaggio nel caso in cui la data inserita non sia valida.
				 
				
			
		  }
		  
		  

		  
		}
		
		*/
		
		
		
		
		
		
	
	
	
	
	
	

