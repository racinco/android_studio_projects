package com.example.booklibrary;


// a book class has all the characteristics of a book
public class Book {
    // these are all data in the book

    public final static String BOOK_ID_KEY = "bookID";
    private int id;
    private String name;
    private String author;
    private int pages;
    private String imageUrl;
    private  String shortDesc;
    private String longDesc;
    private boolean isExpanded; // is expanded tells whther the book is expanded or not

    public Book() {
    }

    public Book(int id, String name, String author, int pages, String imageUrl, String shortDesc, String longDesc, boolean isExpanded) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.imageUrl = imageUrl;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.isExpanded = isExpanded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    // this finction helps us gather the data in one go
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", imageUrl='" + imageUrl + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", longDesc='" + longDesc + '\'' +
                ", isExpanded=" + isExpanded +
                '}';
    }
}
