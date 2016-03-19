package pe.gob.mtpe.sovio.bean.tramite;


import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pe.gob.mtpe.sovio.bean.simintra1.SITBEscalaRemun;
import pe.gob.mtpe.sovio.bean.simintra1.SITBPerCargo;
import pe.gob.mtpe.sovio.bean.simintra1.SITBUsuario;

@Entity(name="PRTBC_PERSONAL")
@Table(name="PRTBC_PERSONAL", schema="TRAMITE")
public class PRTBCPersonal {

	@Id 
	@Column(name="V_CODPERSONAL") private String codPersonal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="V_CODTIPO", nullable=true) 
	private PRTBCTipoPers tipoPers;
	
	@Column(name="V_DESAPEPAT") private String desApePat;
	@Column(name="V_DESAPEMAT") private String desApeMat;
	@Column(name="V_DESNOMBRES") private String desNombres;
	@Column(name="V_CODESCALA") private String codEscala;
	@Column(name="V_DESCARNIV") private String desCarNiv;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="N_NUMDEP", nullable=true) 
	private TDTBCDependencia dependencia;	

	@Column(name="N_FLGACTIVO") private Integer flgActivo;
	@Column(name="V_NOMUSUREG") private String nomUsuReg;
	@Column(name="D_FECREG") private Date fecReg ;
	@Column(name="V_NOMUSUMOD") private String nomUsuMod;
	@Column(name="D_FECMOD") private Date fecMod;
	@Column(name="V_CORREOE") private String correoe;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="V_CODESCALAREMUN", nullable=true) 
	private SITBEscalaRemun escalaRemun;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="V_CODCARGO", nullable=true) 
	private SITBPerCargo perCargo;
	
	@Column(name="V_FLGRESTUPA") private String flgResTupa;
	@Column(name="V_FLGALERT") private String flgAlert;
	@Column(name="V_FLGCONG") private String flgCong;
	@Column(name="V_FLGARCH") private String flgArch;
	@Column(name="V_FLGBNDASIG") private String flgBndaSig;

	
	
	@OneToMany(mappedBy="personal")
	private Collection<SITBUsuario> usuarios;
	
		

	public String getCodPersonal() {
		return codPersonal;
	}
	public void setCodPersonal(String codPersonal) {
		this.codPersonal = codPersonal;
	}
	public PRTBCTipoPers getTipoPers() {
		return tipoPers;
	}
	public void setTipoPers(PRTBCTipoPers tipoPers) {
		this.tipoPers = tipoPers;
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
	public String getCodEscala() {
		return codEscala;
	}
	public void setCodEscala(String codEscala) {
		this.codEscala = codEscala;
	}
	public String getDesCarNiv() {
		return desCarNiv;
	}
	public void setDesCarNiv(String desCarNiv) {
		this.desCarNiv = desCarNiv;
	}
	public TDTBCDependencia getDependencia() {
		return dependencia;
	}
	public void setDependencia(TDTBCDependencia dependencia) {
		this.dependencia = dependencia;
	}
	public Integer getFlgActivo() {
		return flgActivo;
	}
	public void setFlgActivo(Integer flgActivo) {
		this.flgActivo = flgActivo;
	}
	public String getNomUsuReg() {
		return nomUsuReg;
	}
	public void setNomUsuReg(String nomUsuReg) {
		this.nomUsuReg = nomUsuReg;
	}
	public Date getFecReg() {
		return fecReg;
	}
	public void setFecReg(Date fecReg) {
		this.fecReg = fecReg;
	}
	public String getNomUsuMod() {
		return nomUsuMod;
	}
	public void setNomUsuMod(String nomUsuMod) {
		this.nomUsuMod = nomUsuMod;
	}
	public Date getFecMod() {
		return fecMod;
	}
	public void setFecMod(Date fecMod) {
		this.fecMod = fecMod;
	}
	public String getCorreoe() {
		return correoe;
	}
	public void setCorreoe(String correoe) {
		this.correoe = correoe;
	}
	public String getFlgResTupa() {
		return flgResTupa;
	}
	public void setFlgResTupa(String flgResTupa) {
		this.flgResTupa = flgResTupa;
	}
	public String getFlgAlert() {
		return flgAlert;
	}
	public void setFlgAlert(String flgAlert) {
		this.flgAlert = flgAlert;
	}
	public String getFlgCong() {
		return flgCong;
	}
	public void setFlgCong(String flgCong) {
		this.flgCong = flgCong;
	}
	public String getFlgArch() {
		return flgArch;
	}
	public void setFlgArch(String flgArch) {
		this.flgArch = flgArch;
	}
	public String getFlgBndaSig() {
		return flgBndaSig;
	}
	public void setFlgBndaSig(String flgBndaSig) {
		this.flgBndaSig = flgBndaSig;
	}
	public Collection<SITBUsuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Collection<SITBUsuario> usuarios) {
		this.usuarios = usuarios;
	}
	public SITBEscalaRemun getEscalaRemun() {
		return escalaRemun;
	}
	public void setEscalaRemun(SITBEscalaRemun escalaRemun) {
		this.escalaRemun = escalaRemun;
	}
	public SITBPerCargo getPerCargo() {
		return perCargo;
	}
	public void setPerCargo(SITBPerCargo perCargo) {
		this.perCargo = perCargo;
	}

}
