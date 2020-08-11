package karolh95.classicmodels.dto.projection.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WithManager {

	private String manager;
	private String reporter;
}
