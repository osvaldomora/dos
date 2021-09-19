package mx.santander.fiduciario.gestion.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusUser {

	ACTIVO ("AC"),
	BAJA ("BA"),
	ENROLAMIENTO ("EN"),
	BLOQUEADO ("BL");
	
	private String id;
	
}
