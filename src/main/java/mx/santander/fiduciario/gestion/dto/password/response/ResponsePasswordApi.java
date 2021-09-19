package mx.santander.fiduciario.gestion.dto.password.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.santander.fiduciario.gestion.dto.usuarios.UserDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
//Respuesta de la APi auth-controller para el cambio de contraseña
public class ResponsePasswordApi implements Serializable{

	private static final long serialVersionUID = 1L;
//	private UserDto data;
	private String buc;
	private Date dateOperation;
}
