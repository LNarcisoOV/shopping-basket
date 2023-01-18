package com.interview.shoppingbasket;

import java.util.LinkedList;
import java.util.List;

public class Promotion implements PromotionsService {

	private static final int FIFTY_PERCENT_DISCOUNT = 50;
	private static final int TEN_PERCENT_DISCOUNT = 10;

	private BasketItem basketItem = new BasketItem();
	private Integer discount = 0;

	public BasketItem getBasketItem() {
		return basketItem;
	}

	public Integer getDiscount() {
		return discount;
	}

	@Override
	public List<Promotion> getPromotions(Basket basket) {
		List<Promotion> promotions = new LinkedList<>();

		for (var mapItem : basket.countProductCode().entrySet()) {
			Promotion promotion = new Promotion();
			promotion.basketItem = mapItem.getKey();

			if (mapItem.getValue() > 1) {
				promotion.discount = FIFTY_PERCENT_DISCOUNT;
			} else {
				promotion.discount = TEN_PERCENT_DISCOUNT;
			}

			promotions.add(promotion);
		}

		return promotions;
	}

}
