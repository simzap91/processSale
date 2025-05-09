package se.gows.processsale.utils;

import se.gows.processsale.DTO.RegisteredItemDTO;

public class Utility {
    public static RegisteredItemDTO getRegItemDTOFromList(int itemID, RegisteredItemDTO[] list){
        for (RegisteredItemDTO regItem : list) {
            if (regItem.getItemID() == itemID) {
                return regItem;
            }
        }
        return null;
    }
}
