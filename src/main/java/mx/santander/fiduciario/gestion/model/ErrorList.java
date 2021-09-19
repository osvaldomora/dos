
package mx.santander.fiduciario.gestion.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class ErrorList implements Serializable
{

	@Builder.Default
    public List<ErrorDto> errors = new ArrayList<>();
    private final static long serialVersionUID = 5330560245132989925L;

}
