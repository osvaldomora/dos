
package mx.santander.fiduciario.gestion.dto.password.response;



import java.io.Serializable;
import java.util.Date;

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
public class UserDataPasswordDto implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String buc;	//employeeNumber
	private Boolean changed;
	private String description;
	private Date dateOperation;
}
