package com.interview.shoppingbasket;

import java.util.Objects;

public class BasketItem {
	private String productCode;
	private String productName;
	private int quantity;
	private double productRetailPrice;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getProductRetailPrice() {
		return productRetailPrice;
	}

	public void setProductRetailPrice(double productRetailPrice) {
		this.productRetailPrice = productRetailPrice;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasketItem other = (BasketItem) obj;
		return Objects.equals(productCode, other.productCode) && Objects.equals(productName, other.productName)
				&& Double.doubleToLongBits(productRetailPrice) == Double.doubleToLongBits(other.productRetailPrice)
				&& quantity == other.quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productCode, productName, productRetailPrice, quantity);
	}

}
