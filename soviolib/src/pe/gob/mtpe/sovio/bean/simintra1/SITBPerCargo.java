package pe.gob.mtpe.sovio.bean.simintra1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="SITB_PERCARGO")
@Table(name="SITB_PERCARGO", schema="SIMINTRA1")
public class SITBPerCargo {

	@Id 
	@Column(name="V_CODCARGO") private String codCargo;
	
	@Column(name="V_NOMCARGO") private String nomCargo;
	@Column(name="V_DESCARGO") private String desCargo;
	@Column(name="V_FLGACT") private String flgAct;


	public String getCodCargo() {
		return codCargo;
	}
	public void setCodCargo(String codCargo) {
		this.codCargo = codCargo;
	}
	public String getNomCargo() {
		return nomCargo;
	}
	public void setNomCargo(String nomCargo) {
		this.nomCargo = nomCargo;
	}
	public String getDesCargo() {
		return desCargo;
	}
	public void setDesCargo(String desCargo) {
		this.desCargo = desCargo;
	}
	public String getFlgAct() {
		return flgAct;
	}
	public void setFlgAct(String flgAct) {
		this.flgAct = flgAct;
	}
	
}
