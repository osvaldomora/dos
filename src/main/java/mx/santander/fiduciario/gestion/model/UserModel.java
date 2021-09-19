package mx.santander.fiduciario.gestion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserModel {
	
	
    private String buc;
    private String name;
    private String surnames;
    private Integer attempts;
    private String description;
    private String password;
    private String status;
    private String mail;
    private Integer count;
    private String message;

	
	
	
	

}
