package domain.warehouse;

import domain.product.Product;

import java.util.ArrayList;
import java.util.Collection;

public class ProductCategory implements IWorkWithData<Product>{
    private Collection<Product> products = new ArrayList<>();
    private String category;

    public ProductCategory(String category, Collection<Product> products) {
        this.category = category;
        addAll(products);
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public boolean add(Product product) {
        return products.add(product);
    }

    public boolean addAll(Collection<Product> products) {
        for (Product p : products) {
            if(!add(p)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean remove(Product product) {
        return products.remove(product);
    }
}
