package com.jimmie.domain.items;

import java.io.Serializable;

public class Price implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public CoinType getCoinType() {
		return coinType;
	}

	public void setCoinType(CoinType coinType) {
		this.coinType = coinType;
	}

	private int amount;
	
	private CoinType coinType;
	
	public Price(int amount, CoinType coinType) {
		this.amount = amount;
		this.coinType = coinType;
	}
}
