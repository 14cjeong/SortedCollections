package com.company;

public class StockItem implements Comparable<StockItem> {

    private final String name;
    private double price;
    private int quantityStock = 0; //Can be initialized later
    //Tim referred to the above as the declaration line
    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock = 0; // or here (but you normally wouldn't do both.)
    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int quantityInStock() {
        return quantityStock;
    }

    //If you tried to make a setter for name, intelliJ wouldn't
    //allow it. Remember! It's a final

    public void setPrice(double price) {
        if(price > 0.0) {
            this.price = price;
        }

    }

    public void adjustStock(int quantity) {
        int newQuantity = this.quantityStock + quantity;
        if(newQuantity >= 0) {
            this.quantityStock = newQuantity;
        }
    }

//I don't understand this override step particularly well
    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering StockItem.equals");
        if(obj == this) {
            return true;
        }

        if((obj == null) || (obj.getClass() !=this.getClass())) {
            return false;
        } //If its null or if the classes don't match

        String objName = ((StockItem) obj).getName(); // -- notice the (StockItem) casting
        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31; //Number can be anything. We're just adding it to make the hashcode different. Useless
                                            //to have the same hashcode because otherwise, you should have gone with a List...I think...that's the
                                            //the right explanation


    }

    @Override
    public int compareTo(StockItem o) {
        System.out.println("Entering StockItem.compareTo");
        if (this == o) {
            return 0;
        }

        if (o != null) {
            return this.name.compareTo(o.getName());
        }

        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name + " : price " + this.price;
    }
}

//R