package it.emanuele.crud.model;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity // This tells Hibernate to make a table out of this class
@Table( name =  "conto")
public class ContoCorrente {

	
	


	/* n prodotto  1 categoria
	 * 	 1 categoria n prodotto
		
		utente 1 ruolo
		ruolo n utente 
		
		n conto 1 correntista
		1 correntista n conto
	 * 
	 * 
	 * 
	 */
	 



	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    @SequenceGenerator(name="GeneratorName", sequenceName = "idruolo")

	@Column(name = "id")
	    private Integer id;
		
	    	    

	    @ManyToOne
	    @JoinColumn(name = "id_correntista")
	    private Correntista correntista;


	@Column(name = "numero_conto" , unique=true)
		
	  private String numeroConto;

	@Column(name = "saldo")
		
	  private double saldo;


	  public ContoCorrente() {
		  
		  
	  }
	  

		public ContoCorrente(String numeroConto, double saldo) {
			this.numeroConto= numeroConto;
			this.saldo = saldo;

		}


		
		
		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}


		@Override
		public String toString() {
			return "ContoCorrente [id=" + id + ", correntista=" + correntista + ", numeroConto=" + numeroConto
					+ ", saldo=" + saldo + "]";
		}


		public Correntista getCorrentista() {
			return correntista;
		}


		public void setCorrentista(Correntista correntista) {
			this.correntista = correntista;
		}


		public String getNumeroConto() {
			return numeroConto;
		}


		public void setNumeroConto(String numeroConto) {
			this.numeroConto = numeroConto;
		}


		public double getSaldo() {
			return saldo;
		}


		public void setSaldo(double saldo) {
			this.saldo = saldo;
		}




	
		
		
		
		



		
		



	  
	  
	  
	}
	
	
	
	

