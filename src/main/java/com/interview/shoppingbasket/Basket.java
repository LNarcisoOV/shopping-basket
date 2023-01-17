package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Basket {
	private List<BasketItem> items = new ArrayList<>();

	public void add(String productCode, String productName, int quantity) {
		BasketItem basketItem = new BasketItem();
		basketItem.setProductCode(productCode);
		basketItem.setProductName(productName);
		basketItem.setQuantity(quantity);

		items.add(basketItem);
	}

	public List<BasketItem> getItems() {
		return items;
	}

	public void consolidateItems() {
		List<BasketItem> filteredItems = new ArrayList<>(items.size());
		final Map<BasketItem, Integer> elementCountMap = items.stream()
				.collect(Collectors.toMap(Function.identity(), item -> item.getQuantity(), Integer::sum));

		filteredItems = items.stream().distinct().collect(Collectors.toList());

		filteredItems.forEach(item -> item.setQuantity(elementCountMap.get(item).intValue()));

		items = filteredItems;
	}

}
