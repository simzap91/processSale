package se.gows.processsale.integration.discount;

import se.gows.processsale.model.Amount;
import se.gows.processsale.DTO.DiscountRequestDTO;

interface DiscountCalculator {
    Amount getDiscount(DiscountRequestDTO discountRequest);
}
