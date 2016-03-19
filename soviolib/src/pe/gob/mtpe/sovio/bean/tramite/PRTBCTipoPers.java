package pe.gob.mtpe.sovio.bean.tramite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="PRTBC_TIPOPERS")
@Table(name="PRTBC_TIPOPERS", schema="TRAMITE")
public class PRTBCTipoPers {

	@Id
	@Column(name="V_CODTIPO") 
	private String codTipo;
	
	@Column(name="V_DESTIPO") private String desTipo;
	@Column(name="N_FLGACTIVO") private String flgActivo;
	
	
	
	public String getCodTipo() {
		return codTipo;
	}
	public void setCodTipo(String codTipo) {
		this.codTipo = codTipo;
	}
	public String getDesTipo() {
		return desTipo;
	}
	public void setDesTipo(String desTipo) {
		this.desTipo = desTipo;
	}
	public String getFlgActivo() {
		return flgActivo;
	}
	public void setFlgActivo(String flgActivo) {
		this.flgActivo = flgActivo;
	}
	
	
}
