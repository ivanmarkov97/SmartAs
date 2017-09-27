package com.example.ivan.smartas.RecyclerViewListOrder;

/**
 * Created by Ivan on 13.09.2017.
 */

public class Order {

    private int id;
    private String subject;
    private String type;
    private Integer category;
    private String create_date;
    private String end_date;
    private Integer cost;
    private String description;
    private Integer client;
    private Integer executor;
    private Integer status;
    private String review;
    private Boolean like;
    private String date;

    public Order() {
    }

    public Order(int id, String subject, String type, String create_date, String end_date, Integer cost, String description){
        this.id = id;
        this.subject = subject;
        this.type = type;
        this.create_date = create_date;
        this.end_date = end_date;
        this.cost = cost;
        this.description = description;
    }

    public Order(Integer id, String subject, String type, Integer category, String create_date, String end_date, Integer cost, String description, Integer client, Integer executor, Integer status, String review, Boolean like, String date) {
        this.subject = subject;
        this.type = type;
        this.category = category;
        this.create_date = create_date;
        this.end_date = end_date;
        this.cost = cost;
        this.description = description;
        this.client = client;
        this.executor = executor;
        this.status = status;
        this.id = id;
        this.review = review;
        this.like = like;
        this.date = date;

    }
    public Integer getId() { return id; }

    public String getSubject() {
        return subject;
    }

    public String getType() {
        return type;
    }

    public Integer getCategory() {
        return category;
    }

    public String getCreate_date() {
        return create_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public Integer getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public Integer getClient() { return client; }

    public Integer getExecutor() { return executor; }

    public Integer getStatus() { return status; }

    public String getDate() { return date; }

    public void setId(Integer id) { this.id = id; }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public void setExecutor(Integer executor) {
        this.executor = executor;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setDate(String date) { this.date = date; }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

}
