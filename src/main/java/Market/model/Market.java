package Market.model;

import Market.interfaces.CostsCalculation;
import Market.interfaces.Income;
import Market.interfaces.Profit;
import Market.service.BillingService;
import Market.service.InventoryManagerForMarket;
import Market.service.PricingServiceForMarket;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Market implements CostsCalculation, Profit, Income {

    private List<Checkout> checkoutList;
    private List<Cashier> cashierList;
    private List<Goods> DeliveredGoodsList;
    private List<Goods> AvailableGoodsList;
    private List<Goods> SoldGoodsList;
    private List<Receipt> receiptList;

    private PricingServiceForMarket pricingServiceForMarket;
    private InventoryManagerForMarket inventoryManagerForMarket;
    private BillingService billingService;

    private double income;

    private double FoodMarkUp;
    private double NonFoodMarkUp;
    private int daysToExpireForDiscount;
    private double ifToExprieMarkUp;
    private double currentDeliveryCost;
    private double totalDeliveryCosts;


    public List<Goods> getSoldGoodsList() {
        return SoldGoodsList;
    }

    public BillingService getBillingService() {
        return billingService;
    }

    public PricingServiceForMarket getPricingServiceForMarket() {
        return pricingServiceForMarket;
    }

    public void setPricingServiceForMarket(PricingServiceForMarket pricingServiceForMarket) {
        this.pricingServiceForMarket = pricingServiceForMarket;
    }

    public InventoryManagerForMarket getInventoryManagerForMarket() {
        return inventoryManagerForMarket;
    }

    public void setInventoryManagerForMarket(InventoryManagerForMarket inventoryManagerForMarket) {
        this.inventoryManagerForMarket = inventoryManagerForMarket;
    }

    public Market(double foodMarkUp, double nonFoodMarkUp, int dayToExpire, double ifToExprieMarkUp) {
        this.totalDeliveryCosts = 0;
        this.currentDeliveryCost = 0;
        this.ifToExprieMarkUp = ifToExprieMarkUp;
        this.daysToExpireForDiscount = dayToExpire;
        this.FoodMarkUp = foodMarkUp;
        this.NonFoodMarkUp = nonFoodMarkUp;
        this.cashierList = new ArrayList<>();
        this.AvailableGoodsList = new ArrayList<>();
        this.SoldGoodsList = new ArrayList<>();
        this.receiptList = new ArrayList<>();
        this.checkoutList = new ArrayList<>();
        this.DeliveredGoodsList = new ArrayList<>();
        this.income = 0;
        this.pricingServiceForMarket = new PricingServiceForMarket(this);
        this.inventoryManagerForMarket = new InventoryManagerForMarket(this);
        this.billingService = new BillingService(this,null);
    }

    public double getTotalDeliveryCosts() {
        return totalDeliveryCosts;
    }

    public double getCurrentDeliveryCost() {
        return currentDeliveryCost;
    }

    public void setCurrentDeliveryCost(double currentDeliveryCost) {
        this.currentDeliveryCost = currentDeliveryCost;
    }

    public List<Cashier> getCashierList() {
        return cashierList;
    }

    public void addCheckout(Checkout checkout){
        checkoutList.add(checkout);
    }

    public List<Receipt> getReceiptList() {
        return receiptList;
    }

    public List<Goods> getAvailableGoodsList() {
        return AvailableGoodsList;
    }

    public double getFoodMarkUp() {
        return FoodMarkUp;
    }

    public double getNonFoodMarkUp() {
        return NonFoodMarkUp;
    }

    public int getDaysToExpireForDiscount() {
        return daysToExpireForDiscount;
    }

    public void deliverGoods(Goods good, int quantity){


        good.setQuantity(good.getQuantity() + quantity);  // Only affects this market's goods
        this.AvailableGoodsList.add(good);

        Goods deliveredGood = new Goods(good.getName(), good.getUnitDeliveryCost(), good.getGoodsCategory(), good.getExpirationDate());
        deliveredGood.setQuantity(quantity);
        this.DeliveredGoodsList.add(deliveredGood);

        double deliveryCost = quantity * good.getUnitDeliveryCost();
        this.currentDeliveryCost = deliveryCost;
        this.totalDeliveryCosts += currentDeliveryCost;
    }

    public boolean isGoodExpired(Goods good) {

        return LocalDate.now().isAfter(good.getExpirationDate());
    }

    public boolean isGoodToExpireSoon(Goods good) {

        long dayDiff = ChronoUnit.DAYS.between(LocalDate.now(), good.getExpirationDate());
        return dayDiff < daysToExpireForDiscount;
    }


    public double totalSalaryCosts(){
        double totalSalaryCosts = 0;
        for(Cashier cashier : this.cashierList){
            totalSalaryCosts += cashier.getSalary();
        }
        return totalSalaryCosts;
    }

    public void showCurrentAvailableGoods(){
        for (Goods good : AvailableGoodsList) {
            System.out.println(good);
        }
    }
    @Override
    public double totalCosts() {
        return this.totalDeliveryCosts + totalSalaryCosts();
    }
    @Override
    public double profit() {

        return income() - totalCosts();
    }
    @Override
    public double income() {
        double income = 0;
        for(Checkout checkout : checkoutList){
            income += checkout.getTurnover();
        }
        return income;
    }
}
