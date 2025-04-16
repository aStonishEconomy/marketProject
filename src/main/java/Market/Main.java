package Market;

import Market.exceptions.GoodHasExpiredException;
import Market.exceptions.GoodNotAvailableException;
import Market.exceptions.InsufficientStockException;
import Market.exceptions.NotEnoughBudgetException;
import Market.model.*;
import Market.service.BillingService;
import Market.service.InventoryManagerForMarket;
import Market.service.PricingServiceForMarket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


//        Market market = new Market(50, 50, 10, 25);
//        Market secondMarket = new Market(25, 25, 10, 50);
//
//
//        PricingServiceForMarket pricingServiceForMarket = new PricingServiceForMarket(market);
//        InventoryManagerForMarket inventoryManagerForMarket = new InventoryManagerForMarket(market);
//
//        PricingServiceForMarket pricingServiceForSecondMarket = new PricingServiceForMarket(secondMarket);
//        InventoryManagerForMarket inventoryManagerForSecondMarket = new InventoryManagerForMarket(secondMarket);
//
//
//        Cashier cashier1 = new Cashier("Ani Banani", 123421123, 10);
//        Goods milk = new Goods("Milk", 1.0, GoodsCategory.FOOD, LocalDate.now().plusDays(10));
//        Goods shampoo = new Goods("Shampoo", 10.0, GoodsCategory.NON_FOOD, LocalDate.now().plusDays(10));
//
//        Goods bread = new Goods("Bread", 1, GoodsCategory.FOOD,LocalDate.now().plusDays(10));
//        Goods tires = new Goods("Tires", 200, GoodsCategory.NON_FOOD, LocalDate.now().plusDays(10));
//
//
//
//        Client client1 = new Client(50, new ArrayList<>());
//
//
//
//        Checkout checkout1 = new Checkout(cashier1, client1, market);
//
//
//        BillingService billingService = new BillingService(market, checkout1); // Initialize after checkout is created
//
//        market.addCheckout(checkout1);
//        client1.addToShopingList(milk, 2);
//        client1.addToShopingList(shampoo, 1);
//
//
//        market.deliverGoods(milk, 10);
//        market.deliverGoods(shampoo, 20);
//
//        try {
//            checkout1.sellGoods();
//        } catch (InsufficientStockException | NotEnoughBudgetException | GoodNotAvailableException |
//                 GoodHasExpiredException e) {
//            System.out.println(e.getMessage());
//        }
//        Cashier cashier2 = new Cashier("Sami Mami", 123421, 200);
//
//        // Create clients
//        Client client3 = new Client(100000, new ArrayList<>());
//
//
//
//        Checkout checkout2 = new Checkout(cashier2, client3, secondMarket);
//
//
//        BillingService billingService2 = new BillingService(secondMarket, checkout2); // Initialize after checkout is created
//
//        secondMarket.addCheckout(checkout2);
//        client3.addToShopingList(bread, 9);
//        client3.addToShopingList(tires, 9);
//
//        // Deliver goods to the market
//        secondMarket.deliverGoods(bread, 10);
//        secondMarket.deliverGoods(tires, 10);
//
//        try {
//            checkout2.sellGoods();
//        } catch (InsufficientStockException | NotEnoughBudgetException | GoodNotAvailableException |
//                 GoodHasExpiredException e) {
//            System.out.println(e.getMessage());
//        }
//
//
//        System.out.println("First Market.model.Market--------------");
//        System.out.println("SalaryCosts: " + market.totalSalaryCosts());
//        System.out.println("DeliveryCosts: " + market.getTotalDeliveryCosts());
//        System.out.println("Costs for salaries and delivery: " + market.totalCosts());
//        System.out.println("Market.interfaces.Income: " + market.income());
//        System.out.println("Market.interfaces.Profit: " + market.profit());
//        System.out.println(market.getAvailableGoodsList());
//       System.out.println(market.getReceiptList());
//
//        System.out.println("-----------------------------------");
//
//        System.out.println("Second Market.model.Market--------------");
//        System.out.println("SalaryCosts: " + secondMarket.totalSalaryCosts());
//        System.out.println("DeliveryCosts: " + secondMarket.getTotalDeliveryCosts());
//        System.out.println("Costs for salaries and delivery: " + secondMarket.totalCosts());
//        System.out.println("Market.interfaces.Income: " + secondMarket.income());
//        System.out.println("Market.interfaces.Profit: " + secondMarket.profit());
//        System.out.println(secondMarket.getAvailableGoodsList());
//        System.out.println(secondMarket.getReceiptList());

        Scanner scanner = new Scanner(System.in);

        // Initialize the markets, pricing services, inventory, and goods as in your code
        Market market = new Market(50, 50, 10, 25);
        Market secondMarket = new Market(25, 25, 10, 50);

        PricingServiceForMarket pricingServiceForMarket = new PricingServiceForMarket(market);
        InventoryManagerForMarket inventoryManagerForMarket = new InventoryManagerForMarket(market);

        PricingServiceForMarket pricingServiceForSecondMarket = new PricingServiceForMarket(secondMarket);
        InventoryManagerForMarket inventoryManagerForSecondMarket = new InventoryManagerForMarket(secondMarket);

        Cashier cashier1 = new Cashier("Ani Banani", 123421123, 10);
        Goods milk = new Goods("Milk", 1.0, GoodsCategory.FOOD, LocalDate.now().plusDays(10));
        Goods shampoo = new Goods("Shampoo", 10.0, GoodsCategory.NON_FOOD, LocalDate.now().plusDays(10));

        Goods bread = new Goods("Bread", 1, GoodsCategory.FOOD, LocalDate.now().plusDays(10));
        Goods tires = new Goods("Tires", 200, GoodsCategory.NON_FOOD, LocalDate.now().plusDays(10));

        Client client1 = new Client(50, new ArrayList<>());
        Checkout checkout1 = new Checkout(cashier1, client1, market);
        BillingService billingService = new BillingService(market, checkout1);

        // Setting up the first market
        market.addCheckout(checkout1);
        client1.addToShopingList(milk, 2);
        client1.addToShopingList(shampoo, 1);
        market.deliverGoods(milk, 10);
        market.deliverGoods(shampoo, 20);

        // Setting up the second market
        Cashier cashier2 = new Cashier("Sami Mami", 123421, 200);
        Client client3 = new Client(100000, new ArrayList<>());
        Checkout checkout2 = new Checkout(cashier2, client3, secondMarket);
        BillingService billingService2 = new BillingService(secondMarket, checkout2);

        secondMarket.addCheckout(checkout2);
        client3.addToShopingList(bread, 9);
        client3.addToShopingList(tires, 9);
        secondMarket.deliverGoods(bread, 10);
        secondMarket.deliverGoods(tires, 10);

        // Main menu
        while (true) {
            System.out.println("===== Market Menu =====");
            System.out.println("1. View First Market Info");
            System.out.println("2. View Second Market Info");
            System.out.println("3. Process Sale for First Market");
            System.out.println("4. Process Sale for Second Market");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("First Market Info:");
                    displayMarketInfo(market);
                    break;

                case 2:
                    System.out.println("Second Market Info:");
                    displayMarketInfo(secondMarket);
                    break;

                case 3:
                    try {
                        checkout1.sellGoods();
                    } catch (InsufficientStockException | NotEnoughBudgetException | GoodNotAvailableException | GoodHasExpiredException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        checkout2.sellGoods();
                    } catch (InsufficientStockException | NotEnoughBudgetException | GoodNotAvailableException | GoodHasExpiredException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMarketInfo(Market market) {
        System.out.println("SalaryCosts: " + market.totalSalaryCosts());
        System.out.println("DeliveryCosts: " + market.getTotalDeliveryCosts());
        System.out.println("Costs for salaries and delivery: " + market.totalCosts());
        System.out.println("Income: " + market.income());
        System.out.println("Profit: " + market.profit());
        System.out.println("Available Goods: " + market.getAvailableGoodsList());
        System.out.println("Receipts: " + market.getReceiptList());



    }

    }

