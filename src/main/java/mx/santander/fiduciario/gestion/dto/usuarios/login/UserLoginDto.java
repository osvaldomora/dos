
package mx.santander.fiduciario.gestion.dto.usuarios.login;



import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.santander.fiduciario.gestion.dto.usuarios.UserStatusDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLoginDto implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserLogin user;		
//	private UserStatusDto status;	//Estatus usuario /tittle

}
