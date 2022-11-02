package it.emanuele.crud.model; 

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity // This tells Hibernate to make a table out of this class
@Table( name =  "correntista")
public class Correntista {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @SequenceGenerator(name="GeneratorName", sequenceName = "id_correntista")
    @Column(name = "id_correntista")
    private Integer id;
	
    

    @OneToMany(mappedBy = "correntista")
    private Set<ContoCorrente> conti;
    
    @Column(name = "nome_correntista")
  private String nome;

    @Column(name = "cognome_correntista")
  private String cognome;

    @Column(name = "codicefiscale", unique=true)
  private String codicefiscale;


  public Correntista() {
	  
	  
  }
  

	public Correntista(String nome, String cognome, String data , String codicefiscale) {
		this.nome= nome;
		this.cognome = cognome;
		this.codicefiscale = codicefiscale;


	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getCodicefiscale() {
		return codicefiscale;
	}


	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}


	@Override
	public String toString() {
		return "Correntista [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codicefiscale=" + codicefiscale
				+ "]";
	}




	
	


  
}
