package pe.gob.mtpe.sovio.proceso;

import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.apache.bcel.classfile.ConstantObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import oracle.jdbc.Const;
import pe.gob.mtpe.sovio.bean.simintra1.SITBPais;
import pe.gob.mtpe.sovio.bean.simintra1.SITBTDocIde;
import pe.gob.mtpe.sovio.datos.Acceso;
import pe.gob.mtpe.sovio.datos.General;
import pe.gob.mtpe.sovio.util.Constantes;
import pe.gob.mtpe.sovio.util.log.InjectLogger;
import pe.gob.mtpe.sovio.util.process.MessageType;
import pe.gob.mtpe.sovio.util.process.ProcessResponse;
import pe.gob.mtpe.sovio.util.process.SovioProcess;


@Component
public class Commons extends ProcessResponse {

	
	@Autowired
	private General datosGeneral;
	

	
	@InjectLogger
	private static Logger log;
	
	
	@SovioProcess
	public List<SITBPais> getPaises() {
		return datosGeneral.getPaises();
	}
	
	
	public static boolean validaCamposObligatorios(ProcessResponse proceso, 
			String valor, String nombreValor, String mensajeKey) {
		if(valor==null || valor.trim().length()==0) {
			proceso.addResultado(mensajeKey, nombreValor + " es obligatorio");
			proceso.setTipoMensaje(MessageType.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	
	public static void enviarCorreo() {
		log.info("enviar Correo");
		
	}
	
	
}
