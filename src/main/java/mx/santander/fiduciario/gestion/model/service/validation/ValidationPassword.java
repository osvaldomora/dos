package mx.santander.fiduciario.gestion.model.service.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import mx.santander.fiduciario.gestion.dto.password.request.UserCPaswordReq;
import mx.santander.fiduciario.gestion.exception.InvalidDataException;
import mx.santander.fiduciario.gestion.exception.catalog.InvalidDataCatalog;

@Component
public class ValidationPassword implements IValidation {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationPassword.class);

	@Override
	public void validatePassword(UserCPaswordReq user) {
		// TODO Auto-generated method stub
		if(user.getLocked())
			 validate( user.getPassword());
		
	}


	private void validate(String pass) {
		if (!pass.matches("[a-zA-Z0-9]{10}")) {
					
			for (int i = 0; i < pass.length() - 1; i++) {
				if (pass.charAt(i) == pass.charAt(i + 1)) {
					LOGGER.info("la contraseña no cuple con el patron solicitado");
					throw new InvalidDataException(InvalidDataCatalog.INVD003, "la contraseña no cuple con el patron solicitado");
					
				}
			}
			LOGGER.info("longitud no permitida o esta usando caracteres no alfanumericos");
			throw new InvalidDataException(InvalidDataCatalog.INVD003, "longitud no permitida o esta usando caracteres no alfanumericos");
		} 
		
	}
}
