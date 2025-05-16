package se.gows.processsale.integration.discount;

import se.gows.processsale.model.Amount;
import se.gows.processsale.DTO.SaleDTO;

interface DiscountCalculator {
    Amount getDiscount(SaleDTO sale);
}
