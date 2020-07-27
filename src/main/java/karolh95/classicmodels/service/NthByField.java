package karolh95.classicmodels.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class NthByField<T> {

	private static final int ONE = 1;
	private static final int OFFSET = 1;

	public abstract Find<T> getRepository();

	public abstract Sort getSortMethod();

	public List<T> findNthLowest(int n) {

		return findNth(n, Sort::ascending);
	}

	public List<T> findNthHighest(int n) {

		return findNth(n, Sort::descending);
	}

	private List<T> findNth(int n, Function<Sort, Sort> order) {

		Sort orderedSort = order.apply(getSortMethod());

		Pageable pageRequest = PageRequest.of(n - OFFSET, ONE, orderedSort);

		return getRepository().findBy(pageRequest);

	}

	public interface Find<T> {

		List<T> findBy(Pageable pageRequest);
	}

}