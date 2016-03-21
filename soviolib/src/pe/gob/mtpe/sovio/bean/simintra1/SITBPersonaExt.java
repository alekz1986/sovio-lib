package pe.gob.mtpe.sovio.bean.simintra1;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity(name="SITB_PERSONAEXT")
@Table(name="SITB_PERSONAEXT", schema="SIMINTRA1")
public class SITBPersonaExt {

	@Id
	@Column(name="V_CODPEREXT") 
	private String codPerExt;
	
	@Column(name="V_CODTDOCIDE") private String codTDocIde;
	@Column(name="V_DESAPEPAT") private String desApePat;
	@Column(name="V_DESAPEMAT") private String desApeMat;
	@Column(name="V_DESNOMBRES") private String desNombres;
	@Column(name="V_GENTRA") private String genTra;
	@Column(name="V_CODDEP") private String codDep;
	@Column(name="V_CODPRO") private String codPro;
	@Column(name="V_CODDIS") private String codDis;
	@Column(name="V_CORREOE") private String correoe;
	@Column(name="D_FECNAC") private Date fecNac;
	@Column(name="V_CODNIVEDU") private String codNivEdu;
	@Column(name="V_CODREG") private String codReg;
	@Column(name="V_OTRAREG") private String otraReg;
	
	
	@JoinColumn(name="V_CODPAIS") 
	@ManyToOne(fetch = FetchType.LAZY)
	private SITBPais pais;
	
	@Column(name="V_FLGACT") private String flgAct;
	@Column(name="V_CODUSUREG") private String codUsuReg;
	@Column(name="V_HOSTREG") private String hostReg;
	@Column(name="D_FECREG") private Date fecReg;
	@Column(name="V_CODUSUMOD") private String codUsuMod;
	@Column(name="V_HOSTMOD") private String hostMod;
	@Column(name="D_FECMOD") private Date fecMod;


	
	public String getCodPerExt() {
		return codPerExt;
	}
	public void setCodPerExt(String codPerExt) {
		this.codPerExt = codPerExt;
	}
	public String getCodTDocIde() {
		return codTDocIde;
	}
	public void setCodTDocIde(String codTDocIde) {
		this.codTDocIde = codTDocIde;
	}
	public String getDesApePat() {
		return desApePat;
	}
	public void setDesApePat(String desApePat) {
		this.desApePat = desApePat;
	}
	public String getDesApeMat() {
		return desApeMat;
	}
	public void setDesApeMat(String desApeMat) {
		this.desApeMat = desApeMat;
	}
	public String getDesNombres() {
		return desNombres;
	}
	public void setDesNombres(String desNombres) {
		this.desNombres = desNombres;
	}
	public String getGenTra() {
		return genTra;
	}
	public void setGenTra(String genTra) {
		this.genTra = genTra;
	}
	public String getCodDep() {
		return codDep;
	}
	public void setCodDep(String codDep) {
		this.codDep = codDep;
	}
	public String getCodPro() {
		return codPro;
	}
	public void setCodPro(String codPro) {
		this.codPro = codPro;
	}
	public String getCodDis() {
		return codDis;
	}
	public void setCodDis(String codDis) {
		this.codDis = codDis;
	}
	public String getCorreoe() {
		return correoe;
	}
	public void setCorreoe(String correoe) {
		this.correoe = correoe;
	}
	public Date getFecNac() {
		return fecNac;
	}
	public void setFecNac(Date fecNac) {
		this.fecNac = fecNac;
	}
	public String getCodNivEdu() {
		return codNivEdu;
	}
	public void setCodNivEdu(String codNivEdu) {
		this.codNivEdu = codNivEdu;
	}
	public String getCodReg() {
		return codReg;
	}
	public void setCodReg(String codReg) {
		this.codReg = codReg;
	}
	public String getOtraReg() {
		return otraReg;
	}
	public void setOtraReg(String otraReg) {
		this.otraReg = otraReg;
	}
	public String getFlgAct() {
		return flgAct;
	}
	public void setFlgAct(String flgAct) {
		this.flgAct = flgAct;
	}
	public String getCodUsuReg() {
		return codUsuReg;
	}
	public void setCodUsuReg(String codUsuReg) {
		this.codUsuReg = codUsuReg;
	}
	public String getHostReg() {
		return hostReg;
	}
	public void setHostReg(String hostReg) {
		this.hostReg = hostReg;
	}
	public Date getFecReg() {
		return fecReg;
	}
	public void setFecReg(Date fecReg) {
		this.fecReg = fecReg;
	}
	public String getCodUsuMod() {
		return codUsuMod;
	}
	public void setCodUsuMod(String codUsuMod) {
		this.codUsuMod = codUsuMod;
	}
	public String getHostMod() {
		return hostMod;
	}
	public void setHostMod(String hostMod) {
		this.hostMod = hostMod;
	}
	public Date getFecMod() {
		return fecMod;
	}
	public void setFecMod(Date fecMod) {
		this.fecMod = fecMod;
	}
	public SITBPais getPais() {
		return pais;
	}
	public void setPais(SITBPais pais) {
		this.pais = pais;
	}
	
}
