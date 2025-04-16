package Market.service;

import Market.model.Receipt;

import java.io.*;

public class ReceiptService {

    public static void saveReceiptToFile(Receipt receipt){
        try(FileWriter fout = new FileWriter("Receipts/Receipt_" + receipt.getId() + ".txt")) {
            if(receipt != null){
                fout.append(receipt.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StringBuilder readReceiptFromFile(long id){
        StringBuilder content = new StringBuilder();
        try(FileReader fis = new FileReader("Receipts/Receipt_" + id + ".txt")) {
            BufferedReader bufferedReader = new BufferedReader(fis);
            String line;
            while((line = bufferedReader.readLine()) != null){
                content.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
