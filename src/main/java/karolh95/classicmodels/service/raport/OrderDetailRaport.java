package karolh95.classicmodels.service.raport;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import karolh95.classicmodels.dto.query.OrderDetailQuery;
import karolh95.classicmodels.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailRaport {

	private final OrderDetailRepository repository;

	public List<OrderDetailQuery.NumberOrderLineSubtotal> summary() {

		return repository.findAllSummary();
	}

	public List<Long> findByTotalGreaterThan(BigDecimal total) {

		return repository.findByTotalGreaterThan(total);
	}

}
