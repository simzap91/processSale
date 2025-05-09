package se.gows.processsale.model;

import java.util.ArrayList;

import se.gows.processsale.DTO.RegisteredItemDTO;
import se.gows.processsale.utils.Utility;

public class ItemList {
    private ArrayList<RegisteredItemDTO> itemList;
    
    public ItemList(){this.itemList = new ArrayList<>();}

    private boolean idsAreEqual(int itemID1, int itemID2){return itemID1 == itemID2;}

    public boolean isItemInList(int itemID){
        for (RegisteredItemDTO regItem : itemList){
            if (idsAreEqual(regItem.getItemID(), itemID)){
                return true;
            }
        }
        return false;
    }

    public void addItem(RegisteredItemDTO regItem){itemList.add(regItem);}

    public void updateItem(int itemID, int quantity){
        for (RegisteredItemDTO regItem : this.itemList) {
            if (idsAreEqual(regItem.getItemID(), itemID)){
                RegisteredItemDTO updatedRegItem = new RegisteredItemDTO(
                    itemID, regItem.getItemDescription(), regItem.getPrice(), regItem.getVatRate(), regItem.getQuantity() + quantity);
                regItem = updatedRegItem;
            }
        }
    }

    public double getTotalPriceOfItemsInList(){
        double totalPrice = 0;
        for (RegisteredItemDTO regItem : this.itemList){
            totalPrice += regItem.getTotalPriceOfItemQuantity();
        }
        return totalPrice;
    }

    public double getTotalVatOfItemsInList(){
        double totalVat = 0;
        for (RegisteredItemDTO regItem : this.itemList){
            totalVat += regItem.getTotalVatOfItemQuantity();
        }
        return totalVat;
    }

    public RegisteredItemDTO[] getListAsArray(){
        return this.itemList.toArray(new RegisteredItemDTO[0]);
    }

    public RegisteredItemDTO getItemFromList(int itemID){
        return Utility.getRegItemDTOFromList(itemID, getListAsArray());
    }

}
