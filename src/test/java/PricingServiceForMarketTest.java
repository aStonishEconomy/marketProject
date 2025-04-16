import Market.exceptions.GoodHasExpiredException;
import Market.service.PricingServiceForMarket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Market.model.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PricingServiceForMarketTest {

     Market market;
    PricingServiceForMarket pricingServiceForMarket;
    Goods good;
    Goods NonFoodGood;
    Goods expiredGood;
    Goods goodWithDiscount;

    @BeforeEach
    void setUp(){
        market =new Market(50, 25, 10, 25);
        good = new Goods("milk",2, GoodsCategory.FOOD, LocalDate.now().plusDays(20));
        NonFoodGood = new Goods("shampoo",5, GoodsCategory.NON_FOOD, LocalDate.now().plusDays(20));
        expiredGood = new Goods("shampoo",5, GoodsCategory.NON_FOOD, LocalDate.now().minusDays(20));
        goodWithDiscount = new Goods("milk",2, GoodsCategory.FOOD, LocalDate.now().plusDays(1));
        market.deliverGoods(good, 5);
        pricingServiceForMarket = new PricingServiceForMarket(market);

    }

    @Test
    void calculatePrice_ForFood() {
        double expected = 2 + (2 * (market.getFoodMarkUp() / 100));

        try{
            double actual = pricingServiceForMarket.calculatePrice(good);
            assertEquals(expected, actual);
        } catch (GoodHasExpiredException e) {
            System.err.println(e.getMessage());
        }
    }
    @Test
    void calculatePrice_ForNonFood() {
        double expected = 5 + (5 * (market.getNonFoodMarkUp() / 100));

        try{
            double actual = pricingServiceForMarket.calculatePrice(NonFoodGood);
            assertEquals(expected, actual);
        } catch (GoodHasExpiredException e) {
            System.err.println(e.getMessage());
        }
    }
    @Test
    void testCalculatePriceWithExpiryDiscount(){
        double totalPrice = goodWithDiscount.getUnitDeliveryCost()+(goodWithDiscount.getUnitDeliveryCost() * (market.getFoodMarkUp() / 100));
        double expected = totalPrice - (totalPrice * (market.getDaysToExpireForDiscount() / 100));
        try{
            double actual = market.getPricingServiceForMarket().calculatePrice(goodWithDiscount);
            assertEquals(expected, actual);
        } catch (GoodHasExpiredException e) {
            System.err.println(e.getMessage());
        }
    }

@Test
void calculatePrice_WithExpiredGood() {
    Exception exception = assertThrows(GoodHasExpiredException.class, () -> pricingServiceForMarket.calculatePrice(expiredGood));
    assertEquals("Good has expired! Can not sell!", exception.getMessage(), "Expired goods should throw exception.");
}


}