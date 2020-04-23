package com.practise.litepaltest;

import org.litepal.crud.LitePalSupport;

/**
 * @author 29003
 * @description
 * @date 2020/4/7
 */
public class Book extends LitePalSupport {
    private int id;
    private String author;
    private String name;
    private int price;
    private int pages;
    private String press;

    public String getPress() {
        return press == null ? "" : press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author == null ? "" : author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
