package pe.gob.mtpe.sovio.bean.simintra1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name="SITB_TDOCIDE")
@Table(name="SITB_TDOCIDE", schema="SIMINTRA1")
public class SITBTDocIde {

	@Id
	@Column(name="V_CODTDOCIDE") 
	private String codTDocIde;
	
	@Column(name="V_DESTDOCIDE") private String desTDocIde;
	@Column(name="V_DESABR") private String desAbr;
	@Column(name="N_FLGSUNMIN") private Integer flgSunMin;
	@Column(name="N_FLGESTDOC") private Integer flgEstDoc;
	@Column(name="N_FLGVIDLEY") private Integer flgVidLey;
	@Column(name="N_FLGVAC") private Integer flgVac;
	@Column(name="V_FLGVUPE") private String flgVupe;
	@Column(name="V_FLGVUPEEMP") private String flgVupeEmp;
	@Column(name="V_FLGTH") private String flgTH;
	@Column(name="V_FLGSIROV") private String flgSirov;
	
	
	
	
	public String getCodTDocIde() {
		return codTDocIde;
	}
	public void setCodTDocIde(String codTDocIde) {
		this.codTDocIde = codTDocIde;
	}
	public String getDesTDocIde() {
		return desTDocIde;
	}
	public void setDesTDocIde(String desTDocIde) {
		this.desTDocIde = desTDocIde;
	}
	public String getDesAbr() {
		return desAbr;
	}
	public void setDesAbr(String desAbr) {
		this.desAbr = desAbr;
	}
	public Integer getFlgSunMin() {
		return flgSunMin;
	}
	public void setFlgSunMin(Integer flgSunMin) {
		this.flgSunMin = flgSunMin;
	}
	public Integer getFlgEstDoc() {
		return flgEstDoc;
	}
	public void setFlgEstDoc(Integer flgEstDoc) {
		this.flgEstDoc = flgEstDoc;
	}
	public Integer getFlgVidLey() {
		return flgVidLey;
	}
	public void setFlgVidLey(Integer flgVidLey) {
		this.flgVidLey = flgVidLey;
	}
	public Integer getFlgVac() {
		return flgVac;
	}
	public void setFlgVac(Integer flgVac) {
		this.flgVac = flgVac;
	}
	public String getFlgVupe() {
		return flgVupe;
	}
	public void setFlgVupe(String flgVupe) {
		this.flgVupe = flgVupe;
	}
	public String getFlgVupeEmp() {
		return flgVupeEmp;
	}
	public void setFlgVupeEmp(String flgVupeEmp) {
		this.flgVupeEmp = flgVupeEmp;
	}
	public String getFlgTH() {
		return flgTH;
	}
	public void setFlgTH(String flgTH) {
		this.flgTH = flgTH;
	}
	public String getFlgSirov() {
		return flgSirov;
	}
	public void setFlgSirov(String flgSirov) {
		this.flgSirov = flgSirov;
	}

	
}
