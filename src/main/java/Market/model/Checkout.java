package Market.model;

import Market.exceptions.GoodHasExpiredException;
import Market.exceptions.GoodNotAvailableException;
import Market.exceptions.InsufficientStockException;
import Market.exceptions.NotEnoughBudgetException;

import Market.service.*;

public class Checkout {

    private Market market;
    private long id;
    private Cashier cashier;
    private Client client;
    private double turnover;
    private long counter = 111;
    private ReceiptService receiptService;

    public Checkout(Cashier cashier, Client client, Market market) {
        counter *= 2;
        this.id = counter;
        this.cashier = cashier;
        market.getCashierList().add(cashier);
        this.turnover = 0;
        this.client = client;
        this.market = market;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public boolean sellGoods() throws InsufficientStockException, GoodNotAvailableException, GoodHasExpiredException, NotEnoughBudgetException {
        if(market.getInventoryManagerForMarket().checkQuantity(client)){
            double storeBill = market.getBillingService().calculateTotal(client, market.getPricingServiceForMarket());
            if(client.getBuget() >= storeBill) {
                setTurnover(turnover + storeBill);
                Receipt receipt = new Receipt(market, cashier, client.getShopingList(), storeBill);
                market.getSoldGoodsList().addAll(client.getShopingList());
                market.getReceiptList().add(receipt);
                System.out.println(receipt);
                ReceiptService.saveReceiptToFile(receipt);
                return true;
            } else throw new NotEnoughBudgetException("Not enough budget!");
        }
        return false;
    }
}

