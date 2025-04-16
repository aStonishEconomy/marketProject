package Market.service;

import Market.exceptions.GoodHasExpiredException;
import Market.model.Checkout;
import Market.model.Client;
import Market.model.Goods;
import Market.model.Market;

public class BillingService {

    private Market market;
    private Checkout checkout;

    public BillingService(Market market, Checkout checkout) {
        this.market = market;
        this.checkout = checkout;
    }

    public double calculateTotal(Client client, PricingServiceForMarket pricingServiceForMarket) throws GoodHasExpiredException {
        double total = 0;
        for(Goods goodFromClient : client.getShopingList()){
            for(Goods availableGoods : market.getAvailableGoodsList()){
                if(availableGoods.getGoodsCategory() == goodFromClient.getGoodsCategory()){
                    double price = market.getPricingServiceForMarket().calculatePrice(availableGoods);
                    total += price * goodFromClient.getQuantity();
                    int newQuantity = availableGoods.getQuantity() - goodFromClient.getQuantity();
                    availableGoods.setQuantity(newQuantity);
                    break;
                }
            }
        }
        return total;
    }
}
