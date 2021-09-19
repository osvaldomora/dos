package mx.santander.fiduciario.gestion.model.service.validation;

import mx.santander.fiduciario.gestion.dto.password.request.UserCPaswordReq;

public interface IValidation {

	
    public void validatePassword(UserCPaswordReq user);
}
