package by.epam.task05.entity;

import java.io.Serializable;

public class ContractData implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private float all_price;
    private String manager;
    private String client;
    private String car;
    private String state;

    private String date_start;
    private String date_finish;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAll_price() {
        return all_price;
    }

    public void setAll_price(float all_price) {
        this.all_price = all_price;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_finish() {
        return date_finish;
    }

    public void setDate_finish(String date_finish) {
        this.date_finish = date_finish;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
