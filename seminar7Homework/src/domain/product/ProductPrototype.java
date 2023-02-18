package domain.product;

public class ProductPrototype {
    private static int counter;
    private int id;
    private String nameProduct;
    private double costPrice;

    {
        id = ++counter;
    }

    public int getId() {
        return id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }
}
