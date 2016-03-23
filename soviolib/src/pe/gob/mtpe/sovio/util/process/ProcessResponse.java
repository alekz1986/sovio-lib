package pe.gob.mtpe.sovio.util.process;

import java.util.HashMap;
import java.util.Map;

public class ProcessResponse {

	
	private int estado;
	private int tipoMensaje;
	private String mensajePrincipal;
	private Map<String, Object> resultados;
	
	
	
	public ProcessResponse() {
		reiniciar();
	}
	
	public void reiniciar() {
		mensajePrincipal = null;
		resultados = new HashMap<String, Object>();
		tipoMensaje = MessageType.INFORMATION_MESSAGE;
		estado = ProcessState.PROCESSING;
	}
	
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getTipoMensaje() {
		return tipoMensaje;
	}
	public void setTipoMensaje(int tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}
	public void addResultado(String key, Object value) {
		resultados.put(key, value);
	}
	public String getMensajePrincipal() {
		return mensajePrincipal;
	}
	protected Map<String, Object> getResultados() {
		return this.resultados;
	}
	
	public void setMensaje(String mensaje) {
		setMensaje(mensaje, MessageType.INFORMATION_MESSAGE);
	}
	public void setMensaje(String mensaje, int tipoMensaje) {
		this.mensajePrincipal = mensaje;
		this.tipoMensaje = tipoMensaje;
	}

}
