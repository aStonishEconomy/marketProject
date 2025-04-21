    import Market.exceptions.GoodHasExpiredException;
    import Market.model.*;

    import Market.service.BillingService;
    import Market.service.InventoryManagerForMarket;
    import Market.service.PricingServiceForMarket;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;

    import java.time.LocalDate;
    import java.util.ArrayList;

    import static org.junit.jupiter.api.Assertions.*;

    class BillingServiceTest {

        private BillingService billingService;
        private Client client;
        private PricingServiceForMarket pricingServiceForMarket;
        private Market market;

        @BeforeEach
        public void setupTestData() {

            market =new Market(50, 50, 10, 25);
             pricingServiceForMarket = new PricingServiceForMarket(market);
            InventoryManagerForMarket inventoryManagerForMarket = new InventoryManagerForMarket(market);

            Cashier cashier = new Cashier("Ani Banani", 123421123, 10);
             client = new Client(50, new ArrayList<>());

            Goods goods1 = new Goods("milk",2, GoodsCategory.FOOD, LocalDate.now().plusDays(20)); // 5 items at price 10
            Goods goods2 = new Goods("Shampoo", 10.0, GoodsCategory.NON_FOOD, LocalDate.now().plusDays(10)); // 3 items at price 5
            client.addToShopingList(goods1, 3);
            client.addToShopingList(goods2, 3);

            Checkout checkout1 = new Checkout(cashier, client, market);
             billingService = new BillingService(market, checkout1);

            market.addCheckout(checkout1);

            market.deliverGoods(goods1, 5);
            market.deliverGoods(goods2,5);
        }





        @Test
        void calculateTotal() {
            try{
                double actual = billingService.calculateTotal(client, market.getPricingServiceForMarket());
                double expectedTotal = 54.;
                assertEquals(expectedTotal, actual);
            } catch (GoodHasExpiredException e) {
                System.err.println(e.getMessage());
            }

        }


    }
