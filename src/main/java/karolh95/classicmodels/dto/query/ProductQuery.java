package karolh95.classicmodels.dto.query;

import java.math.BigDecimal;

public class ProductQuery {

	public interface Code {
		String getProductCode();
	}

	public interface Name {
		String getProductName();
	}

	public interface Scale {
		String getProductScale();
	}

	public interface Vendor {
		String getProductVendor();
	}

	public interface Description {
		String getProductDescription();
	}

	public interface Quantity {
		int getQuantityInStock();
	}

	public interface BuyPrice {
		BigDecimal getBuyPrice();
	}

	public interface Msrp {
		BigDecimal getMSRP();
	}

	public interface Productline {
		String getProductline();
	}

	public interface CodeNameBuyPrice extends Name, Code, BuyPrice {
	}

	public interface CodeNameDescription extends Code, Name, ProductlineQuery.Text {
	}

	public interface WithOrderNumberMsrpPrice
			extends Msrp, OrderQuery.Number, ProductQuery.Name, OrderDetailQuery.PriceEach {
	}
}
