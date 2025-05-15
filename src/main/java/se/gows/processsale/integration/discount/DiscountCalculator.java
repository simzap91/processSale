package se.gows.processsale.integration.discount;

import se.gows.processsale.DTO.RegisteredItemDTO;
import se.gows.processsale.model.Amount;


interface DiscountCalculator {
    Amount getDiscount(int customerID, RegisteredItemDTO[] purchasedItems, Amount totalPrice);
}
