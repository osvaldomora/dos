
package mx.santander.fiduciario.gestion.dto.usuarios;



import java.io.Serializable;

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
public class UserStatusDto implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String description;
	private String dateOperation;
	private Integer attempts;

}
