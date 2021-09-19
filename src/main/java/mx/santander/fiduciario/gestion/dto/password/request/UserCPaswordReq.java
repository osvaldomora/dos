
package mx.santander.fiduciario.gestion.dto.password.request;



import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCPaswordReq implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private String buc;
	@NotNull
	private String password;
	private String oldPassword;
	@NotNull
	private Boolean locked;
	
}
