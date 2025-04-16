package Market.model;

import Market.exceptions.*;


import java.time.LocalDateTime;
import java.util.List;

public class Receipt {

    private Market market;
    private long id;
    private static long counter = 1000;
    Cashier cashier;
    LocalDateTime issuingTheReceipt;
    private List<Goods> goodsList;
    private  double totalAmount;

    public Receipt(Market market,Cashier cashier, List<Goods> goodsList, double totalAmount) {
        counter++;
        this.id = counter;
        this.cashier = cashier;
        this.issuingTheReceipt = LocalDateTime.now();
        this.goodsList = goodsList;
        this.totalAmount = totalAmount;
        this.market = market;
    }

    public long getId() {
        return id;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public LocalDateTime getIssuingTheReceipt() {
        return issuingTheReceipt;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {

        StringBuilder receiptDetails = new StringBuilder();
        receiptDetails.append("Market.model.Receipt ID: ").append(id).append("\n")
                .append("Market.model.Cashier: ").append(cashier.getName()).append("\n")
                .append("Date: ").append(issuingTheReceipt).append("\n")
                .append("Items:\n");
        for (Goods good : goodsList) {
            try{
                double sellingPrice = market.getPricingServiceForMarket().calculatePrice(good);
                receiptDetails.append("- ").append(good.getGoodsCategory())
                        .append(": ").append(good.getQuantity())
                        .append(" @ ").append(sellingPrice)
                        .append(" each\n");
            } catch (GoodHasExpiredException e) {
                System.out.println(e.getMessage());
            }
        }
        receiptDetails.append("Total Amount: ").append(totalAmount).append("\n");
        return receiptDetails.toString();
    }
}
