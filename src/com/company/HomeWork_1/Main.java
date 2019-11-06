package com.company.HomeWork_1;

import java.io.*;
import java.util.Scanner;


public class Main {

    public static Object createDeal(int price, DealName type) {
        if (type == DealName.FX_SPOT)
            return  new FxSpot(price);
        if (type == DealName.COMMODITY_SPOT)
            return new CommoditySpot(price);
        if (type == DealName.BOND)
            return new Bond(price);
        if (type == DealName.IR_SWAP)
            return new IrSwap(price);
        return null;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        FileReader reader = new FileReader(fileName);
        try {
            BufferedReader buff = new BufferedReader(reader);
            buff.readLine();
            String type = buff.readLine().replace(":", " ").replace(",", "").split("\\s+")[1];
            int price = Integer.parseInt(buff.readLine().replace(":", " ").split("\\s+")[1]);
            Object object = createDeal(price, DealName.selectDealName(type));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }
}
