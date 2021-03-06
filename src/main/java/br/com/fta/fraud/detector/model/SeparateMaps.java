package br.com.fta.fraud.detector.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class SeparateMaps<T> {
	
	private final Map<T, BigDecimal> deposits = new HashMap<>();
	private final Map<T, BigDecimal> transfers = new HashMap<>();
	
	private final Map<T, BigDecimal> fraudDeposits = new HashMap<>();
	private final Map<T, BigDecimal> fraudTransfers = new HashMap<>();
	
	private final BigDecimal limit;
	
	SeparateMaps(BigDecimal limit) {
		this.limit = limit;
	}

	void byTransferAndDeposits(BigDecimal value, T origin, T destination) {
		
		BigDecimal totalValueOrigin = transfers.putIfAbsent(origin, value);
		BigDecimal totalValueDestination = deposits.putIfAbsent(destination, value);
		if (totalValueOrigin != null) {
			BigDecimal oldValue = transfers.get(origin);
			transfers.put(origin, oldValue.add(value));
		}
		
		if (totalValueDestination != null) {
			BigDecimal oldValue = deposits.get(destination);
			deposits.put(destination, oldValue.add(value));
		}
	}
	
	public Map<T, BigDecimal> getFraudDeposits() {
		deposits.forEach((k, v) -> {
			if (isValueGreater(v, this.limit)) {
				fraudDeposits.put(k, v);
			}
		});
		
		return fraudDeposits;
	}
	
	public Map<T, BigDecimal> getFraudTransfers() {
		transfers.forEach((k, v) -> {
			if (isValueGreater(v, this.limit)) {
				fraudTransfers.put(k, v);
			}
		});
		
		return fraudTransfers;
	}

	private boolean isValueGreater(BigDecimal value, BigDecimal limit) {
		return limit.compareTo(value) < 0;
	}

}
