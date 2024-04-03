package dto;

public record UserDto(
        String id,
        String pw,
        String name,
        String tel,
        int money) {



    public static UserDto allOf(String id, String pw, String name, String tel, int money){
        return new UserDto(id, pw, name, tel, money);
    }

    public UserDto(String id, String pw, String name, String tel){
        this(id, pw, name, tel, 0);
    }

    public static UserDto of(String id, String pw, String name, String tel){
        return new UserDto(id, pw, name, tel);
    }

    public UserDto(String id, String pw) {
        this(id, pw, null, null, 0);
    }

    public UserDto(String id, int money){
        this(id, null ,null,null, money);
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String pw() {
        return pw;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String tel() {
        return tel;
    }

    @Override
    public int money() {
        return money;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", pw=" + pw +
                ", name=" + name +
                ", tel=" + tel +
                ", money=" + money ;
    }
}
