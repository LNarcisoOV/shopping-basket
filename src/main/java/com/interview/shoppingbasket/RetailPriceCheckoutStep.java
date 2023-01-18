package com.interview.shoppingbasket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class RetailPriceCheckoutStep implements CheckoutStep {
	private PricingService pricingService;
	private double retailTotal;

	public RetailPriceCheckoutStep(PricingService pricingService) {
		this.pricingService = pricingService;
	}

	@Override
	public void execute(CheckoutContext checkoutContext) {
		Basket basket = checkoutContext.getBasket();
		retailTotal = 0.0;

		for (BasketItem basketItem : basket.getItems()) {
			int quantity = basketItem.getQuantity();
			double price = pricingService.getPrice(basketItem.getProductCode());
			basketItem.setProductRetailPrice(price);
			retailTotal += quantity * price;
		}

		for (Promotion promotion : basket.getPromotions()) {
			int quantity = promotion.getBasketItem().getQuantity();
			double price = pricingService.getPrice(promotion.getBasketItem().getProductCode());

			Map<BasketItem, Integer> mapBasketItem = basket.countProductCode();
			Integer repeatedNumberOfProductCode = mapBasketItem.get(promotion.getBasketItem()).intValue();

			applyPromotion(promotion, promotion.getBasketItem(), quantity * price * repeatedNumberOfProductCode);
		}

		checkoutContext.setRetailPriceTotal(retailTotal);
	}

	public double applyPromotion(Promotion promotion, BasketItem item, double price) {
		retailTotal -= price * (promotion.getDiscount() / 100);
		retailTotal = new BigDecimal(retailTotal).setScale(2, RoundingMode.HALF_UP).doubleValue();
		return retailTotal;
	}
}
