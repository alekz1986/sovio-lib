package pe.gob.mtpe.sovio.bean.simintra1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="SITB_REGIONAL")
@Table(name="SITB_REGIONAL", schema="SIMINTRA1")
public class SITBRegional {

	@Id
	@Column(name="V_CODREG") private String codReg;
	
	@Column(name="V_NOMREG") private String nomReg;
	@Column(name="V_CODEP") private String codep;

	
	public String getCodReg() {
		return codReg;
	}
	public void setCodReg(String codReg) {
		this.codReg = codReg;
	}
	public String getNomReg() {
		return nomReg;
	}
	public void setNomReg(String nomReg) {
		this.nomReg = nomReg;
	}
	public String getCodep() {
		return codep;
	}
	public void setCodep(String codep) {
		this.codep = codep;
	}

}
