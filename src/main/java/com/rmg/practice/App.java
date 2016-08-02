package com.rmg.practice;

/**
 * Created by skumar76 on 7/20/2016.
 */
public class App {

    public static void main(String[] args) throws Exception {
        PurchaseOrderBindyTest pobindy = new PurchaseOrderBindyTest();

        try {
            pobindy.testBindy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
