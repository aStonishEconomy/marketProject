import Market.model.*;
import Market.service.BillingService;
import Market.service.InventoryManagerForMarket;
import Market.service.PricingServiceForMarket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MarketTest {

    Market market;
    Cashier cashier;
    Client client;
    Goods good;
    Checkout checkout;
    PricingServiceForMarket pricingServiceForMarket;
    InventoryManagerForMarket inventoryManagerForMarket;
    BillingService billingService;

    @BeforeEach
    void setup(){

        market =new Market(50, 50, 10, 25);
        pricingServiceForMarket = new PricingServiceForMarket(market);
        InventoryManagerForMarket inventoryManagerForMarket = new InventoryManagerForMarket(market);

        Cashier cashier = new Cashier("Ani Banani", 123421123, 10);
        client = new Client(50, new ArrayList<>());

         good = new Goods("milk",2, GoodsCategory.FOOD, LocalDate.now().plusDays(20));
        checkout = new Checkout(cashier, client, market);
        billingService = new BillingService(market, checkout);
        client.addToShopingList(good, 3);
        market.addCheckout(checkout);

    }



    @Test
    void deliverGoods() {
    int quantity = 10;
    market.deliverGoods(good, quantity);
    assertTrue(market.getAvailableGoodsList().contains(good));
    assertEquals(quantity, good.getQuantity());

    }

    @Test
    void isGoodExpired() {

        Goods expiredGood = new Goods("milk",2, GoodsCategory.FOOD, LocalDate.now().minusDays(20));

        assertTrue(market.isGoodExpired(expiredGood));
        assertFalse(market.isGoodExpired(good));

    }

    @Test
    void isGoodToExpireSoon() {

        Goods expireSoonGood = new Goods("milk",2, GoodsCategory.FOOD, LocalDate.now().plusDays(1));
        assertTrue(market.isGoodToExpireSoon(expireSoonGood));
        assertFalse(market.isGoodToExpireSoon(good));

    }

    @Test
    void totalSalaryCosts() {
        double actual = market.totalSalaryCosts();
        assertEquals(10, actual);
    }

    @Test
    void totalCosts() {

        market.deliverGoods(good, 10);

        double expected = market.getTotalDeliveryCosts() + market.totalSalaryCosts();
        assertEquals(expected, market.totalCosts());

    }

    @Test
    void profit() {
    }

    @Test
    void income() {
    }
}