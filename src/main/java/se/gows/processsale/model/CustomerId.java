package se.gows.processsale.model;
/**
 * Class that stores a customers ID.
 * @param id the customers ID.
 */
public class CustomerId {
    int customerId;
    public CustomerId(int id){this.customerId = id;}
    public int getId(){return this.customerId;}
    public boolean isEqual(CustomerId custId){return this.customerId == custId.getId();}
}
