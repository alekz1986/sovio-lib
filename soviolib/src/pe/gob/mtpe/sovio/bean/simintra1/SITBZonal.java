package pe.gob.mtpe.sovio.bean.simintra1;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.gob.mtpe.sovio.bean.simintra1.pk.SITBZonalPK;



@Entity(name="SITB_ZONAL")
@Table(name="SITB_ZONAL", schema="SIMINTRA1")
public class SITBZonal implements Serializable {

	
	@EmbeddedId
	private SITBZonalPK key;
	
	@Column(name="V_NOMZON") private String nomZon;
	@Column(name="V_ESTRETCC") private String estRetCC;
	
	
	public SITBZonalPK getKey() {
		return key;
	}
	public void setKey(SITBZonalPK key) {
		this.key = key;
	}
	public String getNomZon() {
		return nomZon;
	}
	public void setNomZon(String nomZon) {
		this.nomZon = nomZon;
	}
	public String getEstRetCC() {
		return estRetCC;
	}
	public void setEstRetCC(String estRetCC) {
		this.estRetCC = estRetCC;
	}
	
	
}







