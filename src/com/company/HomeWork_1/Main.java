package com.company.HomeWork_1;

import java.io.*;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        FileReader reader = new FileReader(fileName);
        try {
            BufferedReader buff = new BufferedReader(reader);
            buff.readLine();
            String type = buff.readLine().replace(":", " ").replace(",", "").split("\\s+")[1];
            int price = Integer.parseInt(buff.readLine().replace(":", " ").split("\\s+")[1]);
            Object object = DealName.valueOf(type).createDeal(price);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }
}

