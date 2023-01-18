package com.interview.shoppingbasket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class PromotionTest {
	@Test
	void emptyBasket() {
		Promotion promotion = new Promotion();

		Basket basket = new Basket();

		assertEquals(promotion.getPromotions(basket).size(), 0);
	}

	@Test
	void checkPromotionsWithMultipleBasketItems() {
		Promotion promotion = new Promotion();

		Basket basket = new Basket();
		basket.add("productCode", "myProduct", 10);
		basket.add("productCode2", "myProduct2", 5);
		basket.add("productCode3", "myProduct3", 20);

		basket.add("productCode", "myProduct", 10);
		basket.add("productCode2", "myProduct2", 5);
		basket.add("productCode3", "myProduct3", 20);

		basket.add("productCode", "myProduct", 10);
		basket.add("productCode2", "myProduct2", 5);
		basket.add("productCode3", "myProduct3", 20);

		basket.add("productCode4", "myProduct4", 10);

		List<Promotion> promotions = promotion.getPromotions(basket);
		assertEquals(promotions.size(), 4);
	}
}
