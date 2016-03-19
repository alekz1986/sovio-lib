package pe.gob.mtpe.sovio.util.process;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class ProcessResponse {

	
	private int estado;
	private int tipoMensaje;
	private String[] mensaje;
	private Exception ex;
	private Map<String, Object> resultados;
	
	
	public ProcessResponse() {
		tipoMensaje = MessageType.INFORMATION_MESSAGE;
		estado = ProcessState.OK;
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
	public String[] getMensaje() {
		return mensaje;
	}
	public void setMensaje(String[] mensaje) {
		this.mensaje = mensaje;
	}

	public Exception getEx() {
		return ex;
	}
	protected void setEx(Exception ex) {
		this.ex = ex;
	}
	public void addResultado(String key, Object value) {
		resultados.put(key, value);
	}	
	
}
