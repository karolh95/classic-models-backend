package karolh95.classicmodels.criteria;

import java.math.BigDecimal;
import java.util.List;
import karolh95.classicmodels.dto.projection.orderdetail.NumberOrderLineSubtotal;

public interface OrderDetailRepositoryCustom {

	List<Long> findByTotalMin(BigDecimal min);

	List<NumberOrderLineSubtotal> findAllSummary();
}
