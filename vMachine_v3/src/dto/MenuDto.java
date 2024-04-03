package dto;

public record MenuDto(
        int id,
        String name,
        int price,
        int stock
) {
    public static MenuDto allOf(int id, String name, int price, int stock){
        return new MenuDto(id, name, price, stock);
    }

    public MenuDto(int id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public MenuDto(String name, int price, int stock){
        this(0,name,price,stock);
    }
    public MenuDto(String name){
        this(0,name,0,0);
    }
    public static MenuDto of(String name, int price, int stock){
        return new MenuDto(name,price,stock);
    }

    public static MenuDto of(String name){
        return new MenuDto(name);
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int price() {
        return price;
    }

    @Override
    public int stock() {
        return stock;
    }

    @Override
    public String toString() {
        return
                "id : " + id +
                ", name : " + name +
                ", price : " + price +
                ", stock : " + stock;
    }
}
