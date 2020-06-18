package karolh95.classicmodels.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DtoProductline {

	private String productline;
	private String textDescription;
	private String htmlDescription;
	private byte[] image;
}
