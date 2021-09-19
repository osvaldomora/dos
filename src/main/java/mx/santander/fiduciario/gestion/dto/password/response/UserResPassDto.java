
package mx.santander.fiduciario.gestion.dto.password.response;



import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.santander.fiduciario.gestion.dto.usuarios.UserDataDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
//response que se le va a regresar al front
public class UserResPassDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserPasswordDto data;

}
