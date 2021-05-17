package sample;

public class Production {
    private  int num;
    private  String name;
    private  double price;
    private int quantity;
    private String manufactured;
    private String producingCountry;

    public Production(int num, String name, double price, int quantity, String manufactured, String producingCountry) {
        this.num = num;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.manufactured = manufactured;
        this.producingCountry = producingCountry;
    }

    public Production(String name, double price, int quantity, String manufactured, String producingCountry) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.manufactured = manufactured;
        this.producingCountry = producingCountry;
    }
    public Production() {}

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void  setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getManufactured() {
        return manufactured;
    }

    public void setManufactured(String manufactured) {
        this.manufactured = manufactured;
    }

    public String getProducingCountry() {
        return producingCountry;
    }

    public void setProducingCountry(String producingCountry) {
        this.producingCountry = producingCountry;
    }

    @Override
    public String toString() {
        return "Production{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", manufactured='" + manufactured + '\'' +
                ", producingCountry='" + producingCountry + '\'' +
                '}';
    }
}
