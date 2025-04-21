package Market.service;

import Market.exceptions.GoodHasExpiredException;
import Market.model.Goods;
import Market.model.GoodsCategory;
import Market.model.Market;

public class PricingServiceForMarket{
    private Market market;

    public PricingServiceForMarket(Market market) {
        this.market = market;
    }

    public double calculatePrice(Goods good) throws GoodHasExpiredException {
        if(market.isGoodExpired(good)){
            throw  new GoodHasExpiredException("Good has expired! Can not sell!");
        }
        double markup = (good.getGoodsCategory() == GoodsCategory.FOOD) ? market.getFoodMarkUp() : market.getNonFoodMarkUp();
        double totalPrice = good.getUnitDeliveryCost()+(good.getUnitDeliveryCost() * (markup / 100));
        if(market.isGoodToExpireSoon(good)){
            totalPrice -= totalPrice * (market.getDaysToExpireForDiscount() / 100);
        }
        return totalPrice;
    }
}
