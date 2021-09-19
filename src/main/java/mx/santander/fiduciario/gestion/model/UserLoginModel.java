package mx.santander.fiduciario.gestion.model;

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
public class UserLoginModel {
	private String buc;
	private String name;	//cn
	private String surnames;
	private Integer attempts;
	private Date lastConecction;
	private boolean session;
	private String status;
	private String message;
}
