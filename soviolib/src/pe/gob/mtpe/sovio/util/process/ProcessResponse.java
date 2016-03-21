package pe.gob.mtpe.sovio.util.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessResponse /* implements InvocationHandler */ {

	
	private int estado;
	private int tipoMensaje;
	private String mensajePrincipal;
	private Exception exception;
	private Map<String, Object> resultados;
	
	
	
	public ProcessResponse() {
		reiniciar();
	}
	
	protected void reiniciar() {
		mensajePrincipal = null;
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
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	public void addResultado(String key, Object value) {
		resultados.put(key, value);
	}
	public String getMensajePrincipal() {
		return mensajePrincipal;
	}
	
	public void setMensaje(String mensaje) {
		setMensaje(mensaje, MessageType.INFORMATION_MESSAGE);
	}
	public void setMensaje(String mensaje, int tipoMensaje) {
		this.mensajePrincipal = mensaje;
		this.tipoMensaje = tipoMensaje;
	}

}
