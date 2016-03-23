package pe.gob.mtpe.sovio.proceso;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.apache.bcel.classfile.ConstantObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import oracle.jdbc.Const;
import pe.gob.mtpe.sovio.bean.simintra1.SITBPais;
import pe.gob.mtpe.sovio.bean.simintra1.SITBTDocIde;
import pe.gob.mtpe.sovio.datos.Acceso;
import pe.gob.mtpe.sovio.datos.General;
import pe.gob.mtpe.sovio.proceso.util.ValorObligatorio;
import pe.gob.mtpe.sovio.util.Constantes;
import pe.gob.mtpe.sovio.util.Mensajes;
import pe.gob.mtpe.sovio.util.SendMail;
import pe.gob.mtpe.sovio.util.StringLib;
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


	public static boolean validaCampoObligatorio(ProcessResponse proceso, 
			String valor, String mensajeKey, String nombreValor) {
		if(StringLib.esVacioNulo(valor)) {
			proceso.addResultado(
					mensajeKey, 
					Mensajes.get(Mensajes.Adv.OBLIGATORIO, 
							nombreValor)
					);
			proceso.setTipoMensaje(MessageType.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	
	public static Map<String, Object> validaCamposObligatorios(
			List<ValorObligatorio> campos) {
		Map<String, Object> resultado = new HashMap<String, Object>();
		for(ValorObligatorio campo: campos) {
			if(StringLib.esVacioNulo(campo.getValor()))
				resultado.put(
					campo.getKeyMensaje(), //key para el mensaje
					Mensajes.get(Mensajes.Adv.OBLIGATORIO, 
							campo.getDescripcionCampo()) //Descr. del campo
				);
		}
		return resultado;
	}
	
}
