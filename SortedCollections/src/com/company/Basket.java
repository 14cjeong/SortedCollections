package com.company;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> list; //<Key, value>

    public Basket(String name) {
        this.name = name;
        this.list = new HashMap<>();
        //Changing HashMap to TreeMap, makes
        //outputs in alphabetical order
        //Putting in alphabetical order slows things down
        //
    }

    public int addToBasket (StockItem item, int quantity) {
        if ((item != null) && (quantity>0)) {
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }

    public Map<StockItem, Integer> Items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " + list.size() + ((list.size() == 1) ? " item" : " items") +  "\n";
        //if the list.size() is equal to 1, print out item, otherwise print out items
        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> item : list.entrySet()) {
            s = s + item.getKey() + ". " + item.getValue() + " purchased\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }

        return s + "Total cost " + totalCost;
    }
}
