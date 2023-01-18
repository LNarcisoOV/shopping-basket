package com.interview.shoppingbasket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckoutPromotionTest {

	CheckoutPromotion checkoutPromotion;

	@BeforeEach
	void setup() {
		checkoutPromotion = new CheckoutPromotion();
	}

	@Test
	void checkSetPromotionListOnCheckoutContext() {

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

		basket.add("productCode5", "myProduct5", 100);
		basket.add("productCode5", "myProduct5", 100);

		CheckoutContext checkoutContext = new CheckoutContext(basket);

		checkoutPromotion.execute(checkoutContext);

		assertNotNull(checkoutContext.getBasket().getPromotions());

		assertEquals(checkoutContext.getBasket().getPromotions().size(), 5);
	}
}
