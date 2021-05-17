package sample;

public class Order {
    private String date;
    private int number;
    private double sum;
    private String nameProducts;
    private String address;
    private int idClient;

    public  Order(){}
    public Order(String date, double sum, String nameProducts, String address) {
        this.date = date;
        this.sum = sum;
        this.nameProducts = nameProducts;
        this.address = address;
    }

    public Order(String date, int number, double sum, String nameProducts,  String address, int idClient) {
        this.date = date;
        this.number = number;
        this.sum = sum;
        this.nameProducts = nameProducts;
        this.address = address;
        this.idClient = idClient;
    }

    public Order(String date, double sum, String nameProducts, String address, int idClient) {
        this.date = date;
        this.sum = sum;
        this.nameProducts = nameProducts;
        this.address = address;
        this.idClient = idClient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getNameProducts() {
        return nameProducts;
    }

    public void setNameProducts(String nameProducts) {
        this.nameProducts = nameProducts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "Order{" +
                "date='" + date + '\'' +
                ", number=" + number +
                ", sum=" + sum +
                ", nameProducts='" + nameProducts + '\'' +
                ", address='" + address + '\'' +
                ", idClient=" + idClient +
                '}'+'\n';
    }
}
