package mx.santander.fiduciario.gestion.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.santander.fiduciario.gestion.dto.password.request.UserCPaswordReq;
import mx.santander.fiduciario.gestion.dto.password.response.UserResPassDto;
import mx.santander.fiduciario.gestion.dto.request.RequestOtp;
import mx.santander.fiduciario.gestion.dto.request.ResponseOtp;
import mx.santander.fiduciario.gestion.dto.usuarios.UserDataDto;
import mx.santander.fiduciario.gestion.dto.usuarios.UserDto;
import mx.santander.fiduciario.gestion.dto.usuarios.login.UserDataLoginDto;
import mx.santander.fiduciario.gestion.exception.PersistenDataException;
import mx.santander.fiduciario.gestion.exception.catalog.PersistenDataCatalog;
import mx.santander.fiduciario.gestion.model.service.IUser;


@RestController
@RequestMapping("user_management/v1")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST}, allowedHeaders = "*")
public class GestionController {
	
	/** La Constante LOGGER. Obtiene el Logger de la clase */
	private static final Logger LOGGER = LoggerFactory.getLogger(GestionController.class);
    

	
	@Autowired
	IUser userService;

	/**
	 * Este servicio permite consultar datos asociados a un buc, permitiendo saber el estatus del usuario
	 * @param buc a buscar
	 * @return Datos asociados al BUC y objeto JSON obtenido
	 */
	@GetMapping(value = "/users/{buc}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> consultarAuthControlPorId(@PathVariable String buc) {


		UserDataDto dataResponse = userService.findByUser(buc);
		return new ResponseEntity<>(dataResponse, HttpStatus.OK);

	}
	
	
	/**
	 * Servicio que implementa el login ingresando usuario y contraseña, validando datos y cambiando estatus de no. de intentos
	 * @param user JSON de entrada con los datos de buc y password
	 * @return Codigo de la operacion, y JSON asociado.
	 */
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> crearAuthControl(@Valid @RequestBody UserDto user) {
		UserDataLoginDto userResponse = userService.findByUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(userResponse);

	}
	
	
	/**
	 * Servicio que realiza en enrolamiento de un usuario nuevo, agrega nuevos datos asocidados al usuario y cambia el estatus en activo.
	 * @param user JSON de entrada con datos a agregar
	 * @return Codigo de operacion y cambio de status, JSON asociado.
	 */	
	@PutMapping(value = "/enroll", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> enrroll(@Valid @RequestBody UserDto user) {		
		LOGGER.info("Entra a controller PATCH, enroll: {}",user); //Encode.forJava(user.toString())
		UserDataLoginDto userDtoRes = userService.enroll(user);
		return ResponseEntity.status(HttpStatus.OK).body(userDtoRes);
	}

	
	
	/**
	 * Servicio que permite cambiar la contraseña de un usuario activo
	 * @param user JSON de entrada con datos ascoiados a la operacion
	 * @return Codigo de operacion y JSON asociado.
	 */
	@PutMapping(value = "/change-password", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> changePaswword(@Valid @RequestBody UserCPaswordReq user) {
		LOGGER.info("Entra a controller PUT, change-password: {}",user); //Encode.forJava(user.toString())
		UserResPassDto userResponse = userService.changePassword(user);
		return ResponseEntity.status(HttpStatus.OK).body(userResponse);

	}
	
	
	
	/**
	 * Servicio que implementa el login ingresando usuario y contraseña, validando datos y cambiando estatus de no. de intentos
	 * @param user JSON de entrada con los datos de buc y password
	 * @return Codigo de la operacion, y JSON asociado.
	 */
	@PostMapping(value = "/validate-otp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> crearAuthControl(@Valid @RequestBody RequestOtp otp) {
		System.out.println(otp);
		List<String> otps = new ArrayList<String>();
		otps.add("1323");
		otps.add("1882");
		otps.add("1432");
		
		for(String alias:otps)
		{if(otp.getOtp().equals(alias))
		{	System.out.println("existe");
		     return ResponseEntity.status(HttpStatus.OK).body(ResponseOtp.builder().status("OK").msg("validacion correcta").validation(true).build());
		}
		
		}
		
		throw new PersistenDataException(PersistenDataCatalog.PSID003,"OTP no valido");
		
		
		

	}
	
	
	
	
	
	
	
	

	
}
