package miranda.cordeiro.escola.gradecurricular.model;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorMapResponse {

	private int httpResponse ;
	private Map<String , String> mapa;
	private Long timeStamp ;
	
	
	
}
