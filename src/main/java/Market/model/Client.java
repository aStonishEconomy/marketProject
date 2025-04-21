package Market.model;

import java.util.List;

public class Client {

    private double buget;
    private List<Goods> shopingList;

    public Client(double buget, List<Goods> shopingList) {
        this.buget = buget;
        this.shopingList = shopingList;
    }

    public double getBuget() {
        return buget;
    }

    public List<Goods> getShopingList() {
        return shopingList;
    }

    public void addToShopingList(Goods good, int quantity){
        Goods goodWithQuantity = new Goods(good.getName(), good.getUnitDeliveryCost(), good.getGoodsCategory(), good.getExpirationDate());
        goodWithQuantity.setQuantity(quantity);
        shopingList.add(goodWithQuantity); // Add a new instance with the specified quantity
    }
}
