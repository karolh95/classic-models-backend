package karolh95.classicmodels.criteria.util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;

public class CriteriaUtil {

	public static Order asc(CriteriaBuilder builder, Expression<?> x) {
		return builder.asc(x);
	}

	public static Order desc(CriteriaBuilder builder, Expression<?> x) {
		return builder.desc(x);
	}
}
