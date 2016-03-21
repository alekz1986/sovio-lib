package pe.gob.mtpe.sovio.proceso.acceso;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.gob.mtpe.sovio.bean.simintra1.SITBTDocIde;
import pe.gob.mtpe.sovio.datos.Acceso;
import pe.gob.mtpe.sovio.util.Constantes;
import pe.gob.mtpe.sovio.util.log.InjectLogger;
import pe.gob.mtpe.sovio.util.process.ProcessResponse;
import pe.gob.mtpe.sovio.util.process.SovioProcess;


@Component
public class SovioGeneral extends ProcessResponse {

	@Autowired
	private Acceso datosAcceso;
	
	@InjectLogger
	private Logger log;
	
	
	@SovioProcess
	public List<SITBTDocIde> getTipoDocumentosParaRegUsuario(String codPais) {
		if(codPais==null || codPais.trim().length()==0)
			return null;

		List<SITBTDocIde> tipoDocumentos = datosAcceso.getTipoDocParaRegistrarUsuario();
		
		//Para Peru remueve todos los objetos que no sean DNI
		if(codPais.equals(Constantes.Pais.PERU)) { 
			for(int i=0; i<tipoDocumentos.size(); i++) {
				SITBTDocIde tipoDoc = tipoDocumentos.get(i);
				if(!Constantes.TipoDocumento.CODIGO_DNI.
						equals( tipoDoc.getCodTDocIde() )) {
					tipoDocumentos.remove(i);
					i--;
				}
			}
		} 
		
		//Para otros paises, remueve DNI
		else {
			for(int i=0; i<tipoDocumentos.size(); i++) {
				SITBTDocIde tipoDoc = tipoDocumentos.get(i);
				if(Constantes.TipoDocumento.CODIGO_DNI
						.equals( tipoDoc.getCodTDocIde() )) {
					tipoDocumentos.remove(i);
					break;
				}
			}
		}
		
		return tipoDocumentos;
	}
	
}
