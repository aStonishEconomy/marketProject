package Market.service;

import Market.exceptions.GoodNotAvailableException;
import Market.exceptions.InsufficientStockException;
import Market.model.Client;
import Market.model.Goods;
import Market.model.Market;

public class InventoryManagerForMarket {

    private Market market;

    public InventoryManagerForMarket(Market market) {
        this.market = market;
    }

    public InventoryManagerForMarket() {
    }

    public boolean checkAvailability(Client client) throws GoodNotAvailableException {
        for(Goods goodForSale: client.getShopingList()){
            boolean matchFound = false;
            for(Goods good : market.getAvailableGoodsList()){
                if (good.getGoodsCategory() == goodForSale.getGoodsCategory() && good.getName() == goodForSale.getName()){
                    matchFound = true;
                    break;
                }
            }
            if(!matchFound){
                throw new GoodNotAvailableException("Good not available of type : " + goodForSale.getGoodsCategory() + " " + goodForSale.getName());
            }
        }
        return true;
    }

    public boolean checkQuantity(Client client) throws GoodNotAvailableException, InsufficientStockException {
        checkAvailability(client);

        for(Goods goodForSale : client.getShopingList()){
            for(Goods goodsInStock : market.getAvailableGoodsList()){
                if(goodsInStock.getGoodsCategory() == goodForSale.getGoodsCategory() && goodsInStock.getName() == goodForSale.getName()){
                    if(goodsInStock.getQuantity() < goodForSale.getQuantity()){
                        int shortage = goodForSale.getQuantity() - goodsInStock.getQuantity();
                        throw new InsufficientStockException("Insufficient stock for " + goodsInStock.toString() +
                                " : short by " + shortage + " units.");
                    }
                    break;
                }
            }
        }
        return true;
    }
}
