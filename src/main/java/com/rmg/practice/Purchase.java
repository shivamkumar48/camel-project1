package com.rmg.practice;

import org.apache.tools.ant.taskdefs.Javadoc;

import javax.xml.bind.annotation.*;

/**
 * Created by skumar76 on 7/14/2016.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Purchase {



    @XmlAttribute
    public String name;

   @XmlAttribute
   public String price;

    @XmlAttribute
    public int amount;

 /*   public Purchase(int amount, String name, String price) {
        this.amount = amount;
        this.name = name;
        this.price = price;
    }*/

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
