package pe.gob.mtpe.sovio.bean.simintra1;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import pe.gob.mtpe.sovio.bean.tramite.PRTBCPersonal;


@Entity(name="SITB_USUARIO")
@Table(name="SITB_USUARIO", schema="SIMINTRA1")
public class SITBUsuario {

	@Id
	@Column(name="V_CODUSU") private String codUsu;
	
	@JoinColumn(name="V_CODPERSONAL", nullable=true)
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action= NotFoundAction.IGNORE)
	private PRTBCPersonal personal;

	
	@Column(name="V_PASSUSU") private String passUsu;
	@Column(name="V_CODUSUREG") private String codUsuReg;
	@Column(name="D_FECREG") private Date fecReg;
	@Column(name="V_HOSTREG") private String hostReg;
	@Column(name="V_CODUSUMOD") private String codUsuMod;
	@Column(name="D_FECMOD") private Date fecMod;
	@Column(name="V_HOSTMOD") private String hostMod;
	@Column(name="V_FLGACT") private String flgAct;
	@Column(name="N_ADMIN") private int admin; //boolean
	@Column(name="V_FLGINI") private String flgIni;
	
	
	@JoinColumn(name="V_CODPEREXT")
	@ManyToOne(fetch = FetchType.LAZY)
	private SITBPersonaExt personaExt;
	
	
	@Column(name="V_FLGTIPO") private String flgTipo;
	@Column(name="V_FLGESTEXT") private String flgEstExt;
	@Column(name="V_CODVALIDACION") private String codValidacion;
	@Column(name="V_NOMBREIMAGEN") private String nombreImagen;
	
	
	

	public String getCodUsu() {
		return codUsu;
	}
	public void setCodUsu(String codUsu) {
		this.codUsu = codUsu;
	}
	public String getPassUsu() {
		return passUsu;
	}
	public void setPassUsu(String passUsu) {
		this.passUsu = passUsu;
	}
	public String getCodUsuReg() {
		return codUsuReg;
	}
	public void setCodUsuReg(String codUsuReg) {
		this.codUsuReg = codUsuReg;
	}
	public Date getFecReg() {
		return fecReg;
	}
	public void setFecReg(Date fecReg) {
		this.fecReg = fecReg;
	}
	public String getHostReg() {
		return hostReg;
	}
	public void setHostReg(String hostReg) {
		this.hostReg = hostReg;
	}
	public String getCodUsuMod() {
		return codUsuMod;
	}
	public void setCodUsuMod(String codUsuMod) {
		this.codUsuMod = codUsuMod;
	}
	public Date getFecMod() {
		return fecMod;
	}
	public void setFecMod(Date fecMod) {
		this.fecMod = fecMod;
	}
	public String getHostMod() {
		return hostMod;
	}
	public void setHostMod(String hostMod) {
		this.hostMod = hostMod;
	}
	public String getFlgAct() {
		return flgAct;
	}
	public void setFlgAct(String flgAct) {
		this.flgAct = flgAct;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public String getFlgIni() {
		return flgIni;
	}
	public void setFlgIni(String flgIni) {
		this.flgIni = flgIni;
	}
	public SITBPersonaExt getPersonaExt() {
		return personaExt;
	}
	public void setPersonaExt(SITBPersonaExt personaExt) {
		this.personaExt = personaExt;
	}
	public String getFlgTipo() {
		return flgTipo;
	}
	public void setFlgTipo(String flgTipo) {
		this.flgTipo = flgTipo;
	}
	public String getFlgEstExt() {
		return flgEstExt;
	}
	public void setFlgEstExt(String flgEstExt) {
		this.flgEstExt = flgEstExt;
	}
	public String getCodValidacion() {
		return codValidacion;
	}
	public void setCodValidacion(String codValidacion) {
		this.codValidacion = codValidacion;
	}
	public String getNombreImagen() {
		return nombreImagen;
	}
	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}
	public PRTBCPersonal getPersonal() {
		return personal;
	}
	public void setPersonal(PRTBCPersonal personal) {
		this.personal = personal;
	}
	
	
}
