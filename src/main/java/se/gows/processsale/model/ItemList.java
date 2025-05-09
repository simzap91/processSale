package se.gows.processsale.model;

import java.util.ArrayList;

import se.gows.processsale.DTO.RegisteredItemDTO;
import se.gows.processsale.utils.Utility;

public class ItemList {
    private ArrayList<RegisteredItemDTO> itemList;
    
    public ItemList(){this.itemList = new ArrayList<>();}

    public void addItem(RegisteredItemDTO regItem){itemList.add(regItem);}
    public RegisteredItemDTO[] getListAsArray(){
        return this.itemList.toArray(new RegisteredItemDTO[0]);
    }

    public RegisteredItemDTO getItemFromList(int itemID){
        return Utility.getRegItemDTOFromList(itemID, getListAsArray());
    }

}
