package karolh95.classicmodels.service;

import java.util.Comparator;

public enum OrderStatus {

	// @formatter:off
	In_PROCESS("In Process"), 
	ON_HOLD("On Hold"), 
	CANCELLED("Cancelled"), 
	RESOLVED("Resolved"),
	DISPUTED("Disputed"), 
	SHIPPED("Shipped");
	// @formatter:on

	public static final StatusComparator COMPARATOR = new StatusComparator();

	private String status;

	OrderStatus(String status) {
		this.status = status;
	}

	public static OrderStatus fromString(String text) {

		for (OrderStatus value : values()) {
			if (value.status.equalsIgnoreCase(text)) {
				return value;
			}
		}
		throw new IllegalArgumentException(
				String.format("Cannot cast '%s' to OrderStatus enum", text));
	}

	public String getStatus() {
		return status;
	}

	private static class StatusComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {

			OrderStatus s1, s2;

			try {

				s1 = OrderStatus.fromString(o1);
				s2 = OrderStatus.fromString(o2);

			} catch (IllegalArgumentException e) {
				return 0;
			}

			return s1.compareTo(s2);
		}
	}
}
