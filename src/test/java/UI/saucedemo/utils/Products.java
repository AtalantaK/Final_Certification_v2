package UI.saucedemo.utils;

public enum Products {
    item1("Sauce Labs Backpack"),
    item2("Sauce Labs Bike Light"),
    item3("Sauce Labs Bolt T-Shirt"),
    item4("Sauce Labs Fleece Jacket"),
    item5("Sauce Labs Onesie"),
    item6("Test.allTheThings() T-Shirt (Red)");

    private String itemName;

    Products(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
