package pe.gob.mtpe.sovio.util.process;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class ProcessResponse /* implements InvocationHandler */ {

	
	private int estado;
	private int tipoMensaje;
	private List<String> mensaje;
	private Exception ex;
	private Map<String, Object> resultados;
	
	
	
	public ProcessResponse() {
		reiniciar();
	}
	
	protected void reiniciar() {
		mensaje = new ArrayList<String>();
		tipoMensaje = MessageType.INFORMATION_MESSAGE;
		estado = ProcessState.ERROR;
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
	public List<String> getMensaje() {
		return mensaje;
	}
	public void setMensaje(List<String> mensaje) {
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
	public void setMensaje(String m) {
		
	}

	/*
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("invoke");
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
}
