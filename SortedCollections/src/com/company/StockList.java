package com.company;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//StockList with Maps
//We're going to use a string as a primary key
public class StockList {

private final Map<String, StockItem> list;

    //no parameters
    public StockList() {
        this.list = new LinkedHashMap<>();
        //The output goes to alphabetical order once you changed
        //something from a HashMap to a LinkedHashMap
    }

    public int addStock(StockItem item) {
        if(item != null) {
            StockItem inStock = list.getOrDefault(item.getName(), item);
            //In this piece of code. We'll either get the item from the list, or the item passed into our parameter as a default
            //Below: If there are already stocks o this item, adjust quantity
            if(inStock != item) {
                item.adjustStock(inStock.quantityInStock());

            }

            list.put(item.getName(), item);
            return item.quantityInStock();
        }
        return 0;
    }



    public int sellStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);

        if((inStock != null) && (inStock.quantityInStock() >= quantity) && (quantity>0)) {
            inStock.adjustStock(-quantity);
            return quantity; //to indicate that's how many items we've taken out of stock
        }

        return 0;
    }

    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, Double> PriceList() {
        Map<String, Double> prices = new LinkedHashMap<>();
        for(Map.Entry<String, StockItem> item : list.entrySet()) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    public Map<String, StockItem> Items() {
        return Collections.unmodifiableMap(list);
    }

    //toString is actually intended for debugging
    //so the following is kind of bad practice..
    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            StockItem stockItem = item.getValue();

            double itemValue = stockItem.getPrice() * stockItem.quantityInStock();

            s = s + stockItem + ", There are " + stockItem.quantityInStock() + " in stock, Value of items: ";
            s =s + String.format("%.2f", itemValue) + "\n";
            totalCost += itemValue;
        }

        return s + "Total stock value " + String.format("%.2f", totalCost);
    }

}
