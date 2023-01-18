package com.interview.shoppingbasket;

import java.util.List;

public class CheckoutPromotion implements CheckoutStep {
	
	private PromotionsService promotionsService = new Promotion();

	@Override
	public void execute(CheckoutContext checkoutContext) {
		List<Promotion> promotions = promotionsService.getPromotions(checkoutContext.getBasket());
		checkoutContext.getBasket().setPromotions(promotions);
	}

}
