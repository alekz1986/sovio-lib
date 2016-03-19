package pe.gob.mtpe.sovio.bean.tramite;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.gob.mtpe.sovio.bean.simintra1.SITBZonal;

@Entity(name="TDTBC_DEPENDENCIA")
@Table(name="TDTBC_DEPENDENCIA", schema="TRAMITE")
public class TDTBCDependencia {

	@Id
	@Column(name="N_NUMDEP") private Integer numDep;
	@Column(name="V_CODDEP") private String codDep;
	@Column(name="V_DESDEP") private String desdep;
	@Column(name="V_CODNIVEL") private String codNivel;
	@Column(name="N_NUMDEPSUP") private Integer numDepSup;
	@Column(name="V_CODDEPANT") private String codDepAnt;
	@Column(name="V_CODDIRREG") private String codDirReg;
	@Column(name="D_FECINICIO") private Date fecInicio;
	@Column(name="D_FECFIN") private Date fecFin;
	@Column(name="N_FLGACTIVO") private Integer flgActivo;
	@Column(name="V_NOMUSUREG") private String nomUsuReg;
	@Column(name="D_FECREG") private Date fecReg;
	@Column(name="V_NOMUSUMOD") private String nomUsuMod;
	@Column(name="D_FECMOD") private Date fecMod;
	@Column(name="V_DESDEPABR") private String desDepAbr;
	@Column(name="N_FLGGENEXP") private Integer flgGenExp;
	@Column(name="N_FLGGENREC") private Integer flgGenRec;
	@Column(name="N_NUMDEPEXP") private Integer numDepExp;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="V_CODREG", nullable=true, referencedColumnName="V_CODREG"),
		@JoinColumn(name="V_CODZON", nullable=true, referencedColumnName="V_CODZON")
	})
	private SITBZonal zonal;
	
	@Column(name="N_FLGINP") private Integer flgInp;
	@Column(name="N_FLGPROC") private Integer flgProc;
	@Column(name="V_PISO") private String piso;
	@Column(name="V_CODJEFE") private String codJefe;
	@Column(name="V_DESDEPANT") private String desDepAnt;
	
	
	
	public Integer getNumDep() {
		return numDep;
	}
	public void setNumDep(Integer numDep) {
		this.numDep = numDep;
	}
	public String getCodDep() {
		return codDep;
	}
	public void setCodDep(String codDep) {
		this.codDep = codDep;
	}
	public String getDesdep() {
		return desdep;
	}
	public void setDesdep(String desdep) {
		this.desdep = desdep;
	}
	public String getCodNivel() {
		return codNivel;
	}
	public void setCodNivel(String codNivel) {
		this.codNivel = codNivel;
	}
	public Integer getNumDepSup() {
		return numDepSup;
	}
	public void setNumDepSup(Integer numDepSup) {
		this.numDepSup = numDepSup;
	}
	public String getCodDepAnt() {
		return codDepAnt;
	}
	public void setCodDepAnt(String codDepAnt) {
		this.codDepAnt = codDepAnt;
	}
	public String getCodDirReg() {
		return codDirReg;
	}
	public void setCodDirReg(String codDirReg) {
		this.codDirReg = codDirReg;
	}
	public Date getFecInicio() {
		return fecInicio;
	}
	public void setFecInicio(Date fecInicio) {
		this.fecInicio = fecInicio;
	}
	public Date getFecFin() {
		return fecFin;
	}
	public void setFecFin(Date fecFin) {
		this.fecFin = fecFin;
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
	public String getDesDepAbr() {
		return desDepAbr;
	}
	public void setDesDepAbr(String desDepAbr) {
		this.desDepAbr = desDepAbr;
	}
	public Integer getFlgGenExp() {
		return flgGenExp;
	}
	public void setFlgGenExp(Integer flgGenExp) {
		this.flgGenExp = flgGenExp;
	}
	public Integer getFlgGenRec() {
		return flgGenRec;
	}
	public void setFlgGenRec(Integer flgGenRec) {
		this.flgGenRec = flgGenRec;
	}
	public Integer getNumDepExp() {
		return numDepExp;
	}
	public void setNumDepExp(Integer numDepExp) {
		this.numDepExp = numDepExp;
	}
	public SITBZonal getZonal() {
		return zonal;
	}
	public void setZonal(SITBZonal zonal) {
		this.zonal = zonal;
	}
	public Integer getFlgInp() {
		return flgInp;
	}
	public void setFlgInp(Integer flgInp) {
		this.flgInp = flgInp;
	}
	public Integer getFlgProc() {
		return flgProc;
	}
	public void setFlgProc(Integer flgProc) {
		this.flgProc = flgProc;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getCodJefe() {
		return codJefe;
	}
	public void setCodJefe(String codJefe) {
		this.codJefe = codJefe;
	}
	public String getDesDepAnt() {
		return desDepAnt;
	}
	public void setDesDepAnt(String desDepAnt) {
		this.desDepAnt = desDepAnt;
	}
	
	
}
