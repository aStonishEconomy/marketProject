import Market.exceptions.GoodNotAvailableException;
import Market.exceptions.InsufficientStockException;
import Market.model.Client;
import Market.model.Goods;
import Market.model.GoodsCategory;
import Market.model.Market;
import Market.service.InventoryManagerForMarket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryManagerForMarketTest {

    Market market;
    Goods unavailableGood;
    Goods availableGood;
    Client client;
    Client clientWithUnavailableGood;
    InventoryManagerForMarket inventoryManager;

    @BeforeEach
    void setUp(){

        market =new Market(50, 50, 10, 25);
        availableGood = new Goods("milk",2, GoodsCategory.FOOD, LocalDate.now().plusDays(20));
        unavailableGood = new Goods("shampoo",2, GoodsCategory.NON_FOOD, LocalDate.now().plusDays(20));
        market.deliverGoods(availableGood, 6);
        client = new Client(50, new ArrayList<>());
        client.addToShopingList(availableGood, 5);
        clientWithUnavailableGood = new Client(100, new ArrayList<>());
        clientWithUnavailableGood.addToShopingList(unavailableGood, 5);
        inventoryManager = new InventoryManagerForMarket(market);


    }



    @Test
    void checkAvailability_WhenGoodsIsAvailable() {


       try{
           boolean result = market.getInventoryManagerForMarket().checkAvailability(client);
           assertTrue(result);
       } catch (GoodNotAvailableException e) {
           System.err.println(e.getMessage());
       }
    }

    @Test
    void checkAvailability_WhenGoodsAreUnAvailable() {


        try{
            boolean result = market.getInventoryManagerForMarket().checkAvailability(clientWithUnavailableGood);
            assertFalse(result);
        } catch (GoodNotAvailableException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    void checkQuantity_whenStockIsSufficient() {

        try{
            boolean result = inventoryManager.checkQuantity(client);
            assertTrue(result);
        } catch (GoodNotAvailableException | InsufficientStockException e) {
            System.err.println(e.getMessage());
        }
    }
    @Test
    void checkQuantity_whenStockIsInsufficient() {

        client.addToShopingList(availableGood, 10);
        try{
            boolean result = inventoryManager.checkQuantity(client);
            assertFalse(result);
        } catch (GoodNotAvailableException | InsufficientStockException e) {
            System.err.println(e.getMessage());
        }
    }
}