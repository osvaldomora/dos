package mx.santander.fiduciario.gestion.model.service;

import mx.santander.fiduciario.gestion.dto.password.request.UserCPaswordReq;
import mx.santander.fiduciario.gestion.dto.password.response.UserResPassDto;
import mx.santander.fiduciario.gestion.dto.usuarios.UserDataDto;
import mx.santander.fiduciario.gestion.dto.usuarios.UserDto;
import mx.santander.fiduciario.gestion.dto.usuarios.login.UserDataLoginDto;

/**
 * @author Osvaldo Morales - (Arquetipo creado por Santander Tecnologia Mexico)
 * O
 */
public interface IUser  {
    

	UserDataLoginDto findByUser(UserDto user);
	UserDataDto findByUser(String user);
	UserDataLoginDto enroll(UserDto user);
	UserResPassDto changePassword(UserCPaswordReq user);
	

}
