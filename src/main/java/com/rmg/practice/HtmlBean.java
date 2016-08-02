package com.rmg.practice;

/**
 * Created by skumar76 on 7/7/2016.
 */
public class HtmlBean {

    public static String toHTML(String body) {

        body = body.replaceAll("\\s" , "</b>");
        body = "<body>" + body + "</body>";
        return body;
    }
}
