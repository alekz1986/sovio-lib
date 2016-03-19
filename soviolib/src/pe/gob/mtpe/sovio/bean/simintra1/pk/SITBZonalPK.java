package pe.gob.mtpe.sovio.bean.simintra1.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class SITBZonalPK implements Serializable {

	@Column(name="V_CODREG") 
	private String codReg;
	
	@Column(name="V_CODZON")
	private String codZon;
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
        if (!(obj instanceof SITBZonalPK)) return false;
        if (obj == null) return false;
        SITBZonalPK pk = (SITBZonalPK) obj;
        return pk.codReg.equals(this.codReg) && pk.codZon.equals(this.codZon);
	}
	
	@Override
	public int hashCode() {
		return codReg.hashCode() + codZon.hashCode();
	}
	
}
