package mx.santander.fiduciario.gestion.mapper;

import org.springframework.stereotype.Component;

import mx.santander.fiduciario.gestion.dto.password.response.ResponsePasswordApi;
import mx.santander.fiduciario.gestion.dto.password.response.UserDataPasswordDto;
import mx.santander.fiduciario.gestion.dto.password.response.UserPasswordDto;
import mx.santander.fiduciario.gestion.dto.password.response.UserResPassDto;
import mx.santander.fiduciario.gestion.dto.usuarios.UserDataDto;
import mx.santander.fiduciario.gestion.dto.usuarios.UserDto;
import mx.santander.fiduciario.gestion.dto.usuarios.UserStatusDto;
import mx.santander.fiduciario.gestion.dto.usuarios.login.UserDataLoginDto;
import mx.santander.fiduciario.gestion.dto.usuarios.login.UserLogin;
import mx.santander.fiduciario.gestion.dto.usuarios.login.UserLoginDto;
import mx.santander.fiduciario.gestion.model.UserLoginModel;
import mx.santander.fiduciario.gestion.model.UserModel;


@Component
public class UserLoginMapper  implements IUserLoginMapper{
	
	private final String loginDescription="Contraseña cambiada correctamente";
	
	
	
	
	public UserDataLoginDto convertDto(UserLoginModel userModel) {
		UserStatusDto usatatus=UserStatusDto.builder()
		.id(userModel.getStatus())
	    .attempts(userModel.getAttempts())
	    .description(userModel.getMessage())
	    .dateOperation(userModel.getLastConecction().toString())
	    .build();
	
	
	UserLogin userDto =UserLogin.builder()
		.buc(userModel.getBuc())
		.name(userModel.getName())
		.surnames(userModel.getSurnames())
		.session(userModel.isSession())
		.status(usatatus)
		.build()	
		;
	
	UserLoginDto userlogin=UserLoginDto.builder().user(userDto).build();
	
	
	UserDataLoginDto dataResponse=UserDataLoginDto.builder().data(userlogin).build();
	
	return dataResponse;
		
	}
	
	public UserDataLoginDto convertDtoEnroll(UserLoginModel userModel) {
		UserStatusDto usatatus=UserStatusDto.builder()
		.id(userModel.getStatus())
         .build();
	
	
	UserLogin userDto =UserLogin.builder()
		.buc(userModel.getBuc())
		.name(userModel.getName())
		.surnames(userModel.getSurnames())
		.status(usatatus)
		.build()	
		;
	
	UserLoginDto userlogin=UserLoginDto.builder().user(userDto).build();
	
	
	UserDataLoginDto dataResponse=UserDataLoginDto.builder().data(userlogin).build();
	
	return dataResponse;
		
	}
	
	public UserDataDto convertDto(UserModel userModel) {
		UserStatusDto usatatus=UserStatusDto.builder()
		.id(userModel.getStatus())
	    .attempts(userModel.getAttempts()).build();
	
	
	UserDto userDto =UserDto.builder().buc(userModel.getBuc())
		.name(userModel.getName())
		.mail(userModel.getMail())
		.surnames(userModel.getSurnames())
		.status(usatatus).build();
	
	UserDataDto dataResponse=UserDataDto.builder().data(userDto).build();
	
	return dataResponse;
		
	}

	@Override
	public UserResPassDto convertPasswordDto(ResponsePasswordApi responseseApi, int status) {

	
		
		UserDataPasswordDto us=UserDataPasswordDto.builder().buc(responseseApi.getBuc()).dateOperation(responseseApi.getDateOperation())
				.build();
		if(status==201)
			{us.setChanged(true);
			 us.setDescription(loginDescription);
			
			}
			
		UserPasswordDto.builder().user(us).build();
		UserResPassDto ur=UserResPassDto.builder().data(UserPasswordDto.builder().user(us).build()).build();
		
		return ur;
	}

}
