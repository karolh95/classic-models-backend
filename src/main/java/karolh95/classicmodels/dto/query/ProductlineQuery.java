package karolh95.classicmodels.dto.query;

public class ProductlineQuery {

	public interface Productline {
		String getProductline();
	}

	public interface Text {
		String getTextDescription();
	}

	public interface Html {
		String getHtmlDescription();
	}

	public interface Image {
		byte[] getImage();
	}
}
