package com.example.webapplication.entity.product;

public enum Category {
    //Vegetables and Fruits
    VEGETABLES("Vegetables"),
    FRUITS("Fruits"),
    GREENS("Greens"),
    DRIED_FRUITS("Dried_fruits"),
    //Beverages
    JUICES("Juices"),
    WATER("Water"),
    CARBONATED_DRINKS("Carbonated_drinks"),
    COLD_DRINKS("Cold_drinks"),
    ENERGETIC_DRINKS("Energetic_drinks"),
    //Milk and dairy products
    MILK("Milk"),
    YOGURT("Yogurt"),
    SOUR_CREAM("Sour_cream"),
    CHEESE_CURDS("Cheese_curds"),
    BUTTER("Butter"),
    CHEESE("Cheese"),
    EGGS("Eggs"),
    //Meat and meat products
    BEEF("Beef"),
    LAMB("Lamb"),
    POULTRY_MEAT("Poultry_meat"),
    RABBIT_MEAT("Rabbit_meat"),
    FISH("Fish"),
    SAUSAGE("Sausage"),
    //Grocery
    CEREALS("Cereals"),
    FLOUR("Flour"),
    SUGAR("Sugar"),
    SALT("Salt"),
    NOODLE("Noodle"),
    HONEY("Honey"),
    MARGARINE("Margarine"),
    SAUCES("Sauces"),
    SPICES("Spices"),
    //Tea and Coffee
    BLACK_TEA("Black_tea"),
    GREEN_TEA("Green_tea"),
    COFFEE("Coffee"),
    CREAM("Cream"),
    //Cakes and sweets
    CAKES("Cakes"),
    PASTRY("Pastry"),
    CHOCOLATE("Chocolate");

    private final String name;

    Category(String category) {
        this.name = category;
    }

    public String getName() {
        return name;
    }
}