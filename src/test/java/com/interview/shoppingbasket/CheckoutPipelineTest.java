package com.interview.shoppingbasket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CheckoutPipelineTest {

	CheckoutPipeline checkoutPipeline;

	CheckoutPromotion checkoutPromotion;

	RetailPriceCheckoutStep retailPriceCheckoutStep;

	PricingService pricingService;

	@Mock
	Basket basket;

	@Mock
	CheckoutStep checkoutStep1;

	@Mock
	CheckoutStep checkoutStep2;

	@BeforeEach
	void setup() {
		checkoutPipeline = new CheckoutPipeline();
		checkoutPromotion = new CheckoutPromotion();
		pricingService = Mockito.mock(PricingService.class);
		retailPriceCheckoutStep = new RetailPriceCheckoutStep(pricingService);

		basket = new Basket();
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
	}

	@Test
	void returnZeroPaymentForEmptyPipeline() {
		PaymentSummary paymentSummary = checkoutPipeline.checkout(basket);

		assertEquals(paymentSummary.getRetailTotal(), 0.0);
	}

	@Test
	void executeAllPassedCheckoutSteps() {

		when(pricingService.getPrice("productCode")).thenReturn(10.0);
		when(pricingService.getPrice("productCode2")).thenReturn(10.0);
		when(pricingService.getPrice("productCode3")).thenReturn(10.0);
		when(pricingService.getPrice("productCode4")).thenReturn(10.0);

		checkoutPipeline.addStep(checkoutPromotion);
		checkoutPipeline.addStep(retailPriceCheckoutStep);

		PaymentSummary paymentSummary = checkoutPipeline.checkout(basket);

		assertEquals(paymentSummary.getRetailTotal(), 615.0);
	}

}
