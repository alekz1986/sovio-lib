package pe.gob.mtpe.sovio.bean.simintra1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="SITB_PAIS")
@Table(name="SITB_PAIS", schema="SIMINTRA1")
public class SITBPais {

	@Id
	@Column(name="V_CODPAIS") 
	private String codPais;
	
	
	@JoinColumn(name="V_CODCONTIN")
	@ManyToOne(fetch = FetchType.LAZY)
	private SITBContin continente;

	@Column(name="V_DESPAIS") private String desPais;

	
	
	public String getCodPais() {
		return codPais;
	}
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
	public SITBContin getContinente() {
		return continente;
	}
	public void setContinente(SITBContin continente) {
		this.continente = continente;
	}
	public String getDesPais() {
		return desPais;
	}
	public void setDesPais(String desPais) {
		this.desPais = desPais;
	}
	
	
}
