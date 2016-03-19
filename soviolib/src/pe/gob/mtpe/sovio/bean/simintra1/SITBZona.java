package pe.gob.mtpe.sovio.bean.simintra1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="SITB_ZONA")
@Table(name="SITB_ZONA", schema="SIMINTRA1")
public class SITBZona {

	@Id
	@Column(name="V_CODZONA") private String codZona;
	@Column(name="V_DESZON") private String desZon;
	@Column(name="N_FLGSUNMIN") private Integer flgSunMin;

	
	public String getCodZona() {
		return codZona;
	}
	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}
	public String getDesZon() {
		return desZon;
	}
	public void setDesZon(String desZon) {
		this.desZon = desZon;
	}
	public Integer getFlgSunMin() {
		return flgSunMin;
	}
	public void setFlgSunMin(Integer flgSunMin) {
		this.flgSunMin = flgSunMin;
	}
	
}
