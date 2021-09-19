package mx.santander.fiduciario.gestion.mapper;

import mx.santander.fiduciario.gestion.dto.password.response.ResponsePasswordApi;
import mx.santander.fiduciario.gestion.dto.password.response.UserResPassDto;
import mx.santander.fiduciario.gestion.dto.usuarios.UserDataDto;
import mx.santander.fiduciario.gestion.dto.usuarios.login.UserDataLoginDto;
import mx.santander.fiduciario.gestion.model.UserLoginModel;
import mx.santander.fiduciario.gestion.model.UserModel;

public interface IUserLoginMapper {
	
	public UserDataLoginDto convertDto(UserLoginModel userModel);
	public UserDataLoginDto convertDtoEnroll(UserLoginModel userModel);
	public UserDataDto convertDto(UserModel userModel);
	
	public UserResPassDto convertPasswordDto(ResponsePasswordApi respinseApi,int status);

}
