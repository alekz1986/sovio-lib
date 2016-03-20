package pe.gob.mtpe.sovio.util.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessResponse /* implements InvocationHandler */ {

	
	private int estado;
	private int tipoMensaje;
	private List<String> mensajes;
	private Exception exception;
	private Map<String, Object> resultados;
	
	
	
	public ProcessResponse() {
		reiniciar();
	}
	
	protected void reiniciar() {
		mensajes = new ArrayList<String>();
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
	public List<String> getMensajes() {
		return mensajes;
	}
	public void setMensajes(List<String> mensajes) {
		this.mensajes = mensajes;
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

	public void setMensaje(String m) {
		mensajes.add(m);
	}
	
}
