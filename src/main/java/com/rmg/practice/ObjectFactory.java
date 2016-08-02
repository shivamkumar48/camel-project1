package com.rmg.practice;

/**
 * Created by skumar76 on 7/14/2016.
 */
public class ObjectFactory {

    public ObjectFactory() {
    }

    public Purchase createPurchase(){
        return new Purchase();
    }
}
