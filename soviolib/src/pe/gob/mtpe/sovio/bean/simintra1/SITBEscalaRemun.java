package pe.gob.mtpe.sovio.bean.simintra1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="SITB_ESCALAREMUN")
@Table(name="SITB_ESCALAREMUN", schema="SIMINTRA1")
public class SITBEscalaRemun {

	
	@Id
	@Column(name="V_CODESCALA") private String codEscala;
	@Column(name="V_ABRVESCALA") private String abrvEscala;
	@Column(name="V_NOMESCALA") private String nomEscala;
	@Column(name="V_DESESCALA") private String desEscala;
	@Column(name="V_FLGACT") private String flgAct;


	public String getCodEscala() {
		return codEscala;
	}
	public void setCodEscala(String codEscala) {
		this.codEscala = codEscala;
	}
	public String getAbrvEscala() {
		return abrvEscala;
	}
	public void setAbrvEscala(String abrvEscala) {
		this.abrvEscala = abrvEscala;
	}
	public String getNomEscala() {
		return nomEscala;
	}
	public void setNomEscala(String nomEscala) {
		this.nomEscala = nomEscala;
	}
	public String getDesEscala() {
		return desEscala;
	}
	public void setDesEscala(String desEscala) {
		this.desEscala = desEscala;
	}
	public String getFlgAct() {
		return flgAct;
	}
	public void setFlgAct(String flgAct) {
		this.flgAct = flgAct;
	}
	
	
}
