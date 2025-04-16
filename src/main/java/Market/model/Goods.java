package Market.model;

import Market.interfaces.CostsCalculation;

import java.time.LocalDate;

public class Goods implements CostsCalculation {

    private long id;
    private String name;
    private double unitDeliveryCost;
    private GoodsCategory goodsCategory;
    private LocalDate expirationDate;
    private int quantity;

    private static long counter;

    public Goods(String name, double unitDeliveryCost, GoodsCategory goodsCategory, LocalDate expirationDate) {
        counter++;
        this.id = counter;
        this.name = name;
        this.unitDeliveryCost = unitDeliveryCost;
        this.goodsCategory = goodsCategory;
        this.expirationDate = expirationDate;
        this.quantity = 0;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getUnitDeliveryCost() {
        return unitDeliveryCost;
    }

    public GoodsCategory getGoodsCategory() {
        return goodsCategory;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setQuantity(int quantity) {
        if(quantity > 0) {
            this.quantity = quantity;
        }
        else this.quantity = 0;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Market.model.Goods{" +
                "name='" + name + '\'' +
                ", goodsCategory=" + goodsCategory +
                ", quantity=" + quantity +
                '}';
    }


    @Override
    public double totalCosts() {
        return this.unitDeliveryCost * this.quantity;
    }
}
