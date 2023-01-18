package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Basket {
	private List<BasketItem> items = new ArrayList<>();
	private List<BasketItem> consolidateItems = new ArrayList<>();

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
	
	public List<BasketItem> getConsolidateItems() {
		return consolidateItems;
	}

	public void consolidateItems() {
		List<BasketItem> filteredItems = getDistinctBasketItems();
		final Map<BasketItem, Integer> basketItemCountMap = getSumOfQuantityPerProductCode();

		filteredItems.forEach(item -> item.setQuantity(basketItemCountMap.get(item).intValue()));

		consolidateItems = filteredItems;
	}

	private List<BasketItem> getDistinctBasketItems() {
		return items.stream().distinct().collect(Collectors.toList());
	}

	private Map<BasketItem, Integer> getSumOfQuantityPerProductCode() {
		return items.stream()
				.collect(Collectors.toMap(Function.identity(), item -> item.getQuantity(), Integer::sum));
	}
	
	public Map<BasketItem, Integer> countProductCode() {
		return items.stream()
				.collect(Collectors.toMap(Function.identity(), item -> 1, Integer::sum));
	}

}
