package ss12_java_collection_framework.bai_tap.product_management;

import java.util.ArrayList;

public class ProductManagerArrayList {
    private ArrayList<Product> productsList;

    public ProductManagerArrayList() {
        this.productsList = new ArrayList<>();
    }

    public void add(Product product) {
        productsList.add(product);
    }

    public void edit(long id, Product product) {
        int index = productsList.indexOf(product);
        if (index != -1) {

        }
        productsList.add(product);
    }



}
