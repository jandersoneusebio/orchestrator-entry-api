package io.github.jandersoneusebio.model.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorTO {
	
	private Integer code;
	
	private String message;

}
