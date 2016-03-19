package com.cs442.shash5259.assignment2;

/**
 * Created by shash on 25-02-2016.
 */
public class Order {

    public String id;
    public String items;
    public String cost;

   public Order(String id, String items, String cost)
    {
        this.id = id;
        this.cost = cost;
        this.items = items;
    }

    public String getId()
    {
        return id;
    }

    public String getItems()
    {
        return items;
    }

    public String getCost()
    {
        return cost;
    }

}
