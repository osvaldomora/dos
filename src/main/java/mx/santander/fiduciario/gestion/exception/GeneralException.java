package mx.santander.fiduciario.gestion.exception;

import org.springframework.http.HttpStatus;

import mx.santander.fiduciario.gestion.controller.exception.model.ModelException;
import mx.santander.fiduciario.gestion.exception.catalog.GeneralCatalog;
import mx.santander.fiduciario.gestion.exception.catalog.LevelException;

public class GeneralException extends ModelException{

	/**
	 * 
	 * @param catalog Excepcion del catalogo de GeneralCatalog
	 * @param description Descripcion detallada de la excepcion
	 */
	public GeneralException(GeneralCatalog catalog, String description) {
		super(catalog.getHtttpStatus(), catalog.getCode(), catalog.getMessage(), catalog.getLevelException().toString(), description);
		
		
	}
	
	/**
	 * 
	 * @param catalog Descripcion detallada de la excepcion
	 */
	public GeneralException(GeneralCatalog catalog) {
		super(catalog.getHtttpStatus(), catalog.getCode(), catalog.getMessage(), catalog.getLevelException().toString());
	}
	
	
	
	public GeneralException(String code, String msg,String description,HttpStatus status, LevelException lexception) {
		super(status, code, msg, lexception.toString(), description);
		
		
	}

	private static final long serialVersionUID = 1L;

}
