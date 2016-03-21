package pe.gob.mtpe.sovio.bean.simintra1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="SITB_CONTIN")
@Table(name="SITB_CONTIN", schema="SIMINTRA1")
public class SITBContin {

	@Id
	@Column(name="V_CODCONTIN") 
	private String codConTin;
	
	@Column(name="V_DESCONTIN") private String desConTin;


	public String getCodConTin() {
		return codConTin;
	}
	public void setCodConTin(String codConTin) {
		this.codConTin = codConTin;
	}
	public String getDesConTin() {
		return desConTin;
	}
	public void setDesConTin(String desConTin) {
		this.desConTin = desConTin;
	}
	
}
