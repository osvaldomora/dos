package mx.santander.fiduciario.gestion.model.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import mx.santander.fiduciario.gestion.dto.password.request.UserCPaswordReq;
import mx.santander.fiduciario.gestion.dto.password.response.ResponsePasswordApi;
import mx.santander.fiduciario.gestion.dto.password.response.UserResPassDto;
import mx.santander.fiduciario.gestion.dto.request.RequestOtp;
import mx.santander.fiduciario.gestion.dto.usuarios.UserDataDto;
import mx.santander.fiduciario.gestion.dto.usuarios.UserDto;
import mx.santander.fiduciario.gestion.dto.usuarios.login.UserDataLoginDto;
import mx.santander.fiduciario.gestion.exception.GeneralException;
import mx.santander.fiduciario.gestion.exception.InvalidDataException;
import mx.santander.fiduciario.gestion.exception.catalog.GeneralCatalog;
import mx.santander.fiduciario.gestion.exception.catalog.InvalidDataCatalog;
import mx.santander.fiduciario.gestion.exception.catalog.LevelException;
import mx.santander.fiduciario.gestion.mapper.IUserLoginMapper;
import mx.santander.fiduciario.gestion.model.ErrorList;
import mx.santander.fiduciario.gestion.model.UserLoginModel;
import mx.santander.fiduciario.gestion.model.UserModel;
import mx.santander.fiduciario.gestion.model.service.validation.IValidation;

@Service
public class UserService implements IUser {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private RestTemplate clienteRest;
	@Autowired
	private IUserLoginMapper userLoginMapper;
	@Autowired
	private IValidation iValidation;

	private ObjectMapper mapper = new ObjectMapper();
	
	private final String url="https://authcontroller.herokuapp.com";
	// http://localhost:8080
	// https://authcontroller.herokuapp.com
	
    /**
     * Es metodo permite hacer una consulta aL microservicio que realiza la consulta a LDAP para el logeo
     * @param dn Parametro por el cual se hace la busqueda
     * @return regresa un UserModel con los datos asociados a la consulta.
     * @throws lanza un error de PersistenData "Recurso no encontrado" al no encontrar el recurso buscado.
     */

	@Override
	public UserDataLoginDto findByUser(UserDto user) {
		if (!user.getBuc().matches("^\\d+$")) // \\d{12}
			throw new GeneralException(GeneralCatalog.GRAL003, "Buc no valido");

		HttpEntity<UserDto> body = new HttpEntity<UserDto>(user);// http://localhost:8080
		LOGGER.info("findByUser: {}",user.getBuc());
		ResponseEntity<UserLoginModel> response;
		try {//se cosume el microservicio que nos trae los datos del LDAP
			response = clienteRest.exchange(url+"/authcontrol/v1/login", HttpMethod.POST, body,
					UserLoginModel.class);
		} catch (HttpStatusCodeException e) {
			ErrorList errorList = null;
			try {
				errorList = mapper.readValue(e.getResponseBodyAsString(), ErrorList.class);
				System.out.println(errorList.getErrors().get(0).getDescription());
				LOGGER.error("ERROR OBTENIDO2: {}", errorList.toString());
			} catch (Exception e1) {
				throw new GeneralException(GeneralCatalog.GRAL001, "error generico");

			}
             //se repliega el error del microservicio consumido
			throw new GeneralException(errorList.getErrors().get(0).getCode(),
					errorList.getErrors().get(0).getMessage(), errorList.getErrors().get(0).getDescription(),
					HttpStatus.OK, LevelException.valueOf(errorList.getErrors().get(0).getLevel()));

		}
		UserDataLoginDto userDataDto = userLoginMapper.convertDto(response.getBody());

		return userDataDto;
	}
	
	
	
    /**
     * Es metodo permite hacer una consulta para validar si un usuario se encuentra en LDAP
     * @param user Parametro por el cual se hace la busqueda
     * @return regresa un UserModel con los datos asociados a la consulta.
     * @throws lanza un error  "Recurso no encontrado" al no encontrar el recurso buscado.
     */

	@Override
	public UserDataDto findByUser(String user) {

		if (!user.matches("^\\d+$")) // \\d{12}
			throw new GeneralException(GeneralCatalog.GRAL003, "Buc no valido");
		UserModel userModel = null;

		Map<String, String> pathVariable = new HashMap<String, String>();
		pathVariable.put("buc", user);

		try {
			userModel = clienteRest.getForObject(url+"/authcontrol/v1/users/{buc}", UserModel.class,
					pathVariable);

		} catch (HttpClientErrorException e) {
			LOGGER.error("No se encontro recurso, buc: {}", user);

			ErrorList errorList = null;
			LOGGER.error("ERROR OBTENIDO DEL SERVICIO: {}", e.getResponseBodyAsString());
			try {
				errorList = mapper.readValue(e.getResponseBodyAsString(), ErrorList.class);
				System.out.println(errorList.getErrors().get(0).getDescription());
				LOGGER.error("ERROR OBTENIDO2: {}", errorList.toString());
			} catch (Exception e1) {
				throw new GeneralException(GeneralCatalog.GRAL001, "error generico");

			}

			throw new GeneralException(errorList.getErrors().get(0).getCode(),
					errorList.getErrors().get(0).getMessage(), errorList.getErrors().get(0).getDescription(),
					HttpStatus.OK, LevelException.valueOf(errorList.getErrors().get(0).getLevel()));

		}

		return userLoginMapper.convertDto(userModel);
	}
	
	
	/**
	 * Este metodo permite hacer el enrolamiento, actualizando o agregando datos nuevos, asociados a los datos del usuario enviado
	 * @param datos a actualizar del usuario
	 * @return regresa un UserDto con los datos actualizados
	 */
	@Override
	public UserDataLoginDto enroll(UserDto user) {

		HttpEntity<UserDto> body = new HttpEntity<UserDto>(user);
		System.out.println(user);
		ResponseEntity<UserLoginModel> response = null;
		try {
			response = clienteRest.exchange(url+"/authcontrol/v1/enroll", HttpMethod.PUT, body,
					UserLoginModel.class);

		} catch (HttpStatusCodeException ex) {

			ErrorList errorList = null;
			LOGGER.error("ERROR OBTENIDO: {}", ex.getResponseBodyAsString());
			ObjectMapper mapper = new ObjectMapper();
			try {
				errorList = mapper.readValue(ex.getResponseBodyAsString(), ErrorList.class);
				System.out.println(errorList.getErrors().get(0).getDescription());
				LOGGER.error("ERROR OBTENIDO2: {}", errorList.toString());
			} catch (Exception e1) {
				throw new GeneralException(GeneralCatalog.GRAL001, "error generico");

			}

			throw new GeneralException(errorList.getErrors().get(0).getCode(),
					errorList.getErrors().get(0).getMessage(), errorList.getErrors().get(0).getDescription(),
					HttpStatus.CONFLICT, LevelException.valueOf(errorList.getErrors().get(0).getLevel()));

		}
		UserDataLoginDto userDataDto = userLoginMapper.convertDtoEnroll(response.getBody());

		return userDataDto;
	}

	
	
	/**
	 * Este metodo permite hacer el cambio de contraseña de un usuario ya seas por bloqueo, o por olvido de esta
	 * @param datos asociados al usuario para reazaliar la operacion
	 * @return regresa un userDto con los datos de la operacion.
	 */
	@Override
	public UserResPassDto changePassword(UserCPaswordReq user) {
		
		iValidation.validatePassword(user);
		
		HttpEntity<UserCPaswordReq> body = new HttpEntity<UserCPaswordReq>(user);
		ResponseEntity<ResponsePasswordApi> response=null;
		try {
		 response = clienteRest.exchange("http://localhost:8080/authcontrol/v1/change-password", HttpMethod.PUT, body, ResponsePasswordApi.class);
		}
		catch(HttpStatusCodeException e){
			ErrorList errorList = null;
			LOGGER.error("ERROR OBTENIDO: {}", e.getResponseBodyAsString());
			ObjectMapper mapper = new ObjectMapper();
			try {
				errorList = mapper.readValue(e.getResponseBodyAsString(), ErrorList.class);
				System.out.println(errorList.getErrors().get(0).getDescription());
				LOGGER.error("ERROR OBTENIDO2: {}", errorList.toString());
			} catch (Exception e1) {
				throw new GeneralException(GeneralCatalog.GRAL001, "error generico");

			}

			throw new GeneralException(errorList.getErrors().get(0).getCode(),
					errorList.getErrors().get(0).getMessage(), errorList.getErrors().get(0).getDescription(),
					HttpStatus.CONFLICT, LevelException.valueOf(errorList.getErrors().get(0).getLevel()));
		}
			
		
		UserResPassDto responseDto= userLoginMapper.convertPasswordDto(response.getBody(), response.getStatusCode().value());

		return responseDto;
	}
	



	/**
	 * Este metodo permite hacer el cambio de contraseña de un usuario.
	 * @param datos asociados al usuario para reazaliar la operacion
	 * @return regresa un userDto con los datos de la operacion.
	 */

	public UserDto validateOtp(RequestOtp req) {
//		HttpEntity<UserDto> body = new HttpEntity<UserDto>(user);
//		System.out.println(user);
//		try {
//		ResponseEntity<UserModel> response = clienteRest.exchange("http://localhost:8080/authcontrol/v1/enroll", HttpMethod.PUT, body, UserModel.class);
//		}
//		catch{}
//		UserDto userDtoResponse = mapper.map(response.getBody(),UserDto.class);

		return null;
	}
	
	
	

}