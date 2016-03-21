package pe.gob.mtpe.sovio.bean.simintra1.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum UsuarioFlgExt {

	ESTADO_POR_VALIDAR(1),
    ESTADO_POR_COMPLETAR(2), 
    ESTADO_ACTIVO(3), 
    ESTADO_DESACTIVO(4);
	
	private final int id;

    
    private static final Map<Integer, UsuarioFlgExt> lookup 
    = new HashMap<Integer, UsuarioFlgExt>();
    
    static {
        for(UsuarioFlgExt s : EnumSet.allOf(UsuarioFlgExt.class))
             lookup.put(s.id(), s);
    	}
    
    
    private UsuarioFlgExt(int id) {
    	this.id = id;
    }
    
    public int id() {
    	return this.id;
    }
    
    public boolean equalsId(int id){
        return this.id==id;
    }
    
    public static UsuarioFlgExt get(Integer id) { 
		return lookup.get(id); 
	}
	
}
