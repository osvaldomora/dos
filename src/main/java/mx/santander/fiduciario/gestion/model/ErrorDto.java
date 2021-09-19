
package mx.santander.fiduciario.gestion.model;

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
public class ErrorDto implements Serializable
{

    public String code;
    public String message;
    public String level;
    public String description;
    public String moreInfo;
    private final static long serialVersionUID = -3365212127534543305L;

}
