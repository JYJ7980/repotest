package view;

import dto.MenuDto;
import dto.UserDto;
import service.MenuService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuView {
    Scanner sc = new Scanner(System.in);
    MenuService menuService = new MenuService();
    UserService userService = new UserService();
    UserView userView = new UserView();

    public void buyMenu() {
        String menu = null;
        List<MenuDto> dtoList = new ArrayList<>();
        dtoList = menuService.getListAll();
        if(dtoList.isEmpty()){
            System.out.println("메뉴가 존재하지 않습니다.");
            return;
        }else{
            for(MenuDto dto : dtoList) {
                for(MenuDto dto1 : dtoList) {
                    System.out.println("메뉴명 : " + dto1.name() + " 가격 : " + dto1.price() + "원 재고 : " + dto1.stock() + "개");
                }
                menu = sc.next();
                if(menu.equals(dto.name())){
                    System.out.println(dto.name() +"을 1개 배출합니다.");
                    userService.buyMenu(dto.price(),dto.id());
                } else if (userService.money<dto.price()) {
                    System.out.println("잔액이 부족합니다.");
                } else{
                    System.out.println("입력한 메뉴가 존재하지 않습니다.");
                }
                break;
            }
        }
    }

    public void insertMenu() {
        String name = null;
        int price = 0;
        int stock =0;
        System.out.println("신규 메뉴를 입력하십시오");
        System.out.println("메뉴이름 : ");
        name = sc.next();
        System.out.println("메뉴가격 : ");
        price = sc.nextInt();
        System.out.println("재고 수량 : ");
        stock = sc.nextInt();
        MenuDto dto = MenuDto.of(name,price,stock);
        int result = menuService.insertMenu(dto);
        if(result !=0){
            System.out.println("메뉴 입력 성공");
        } else {
            System.out.println("메뉴입력에 실패했습니다.");
        }

    }

    public void updateMenu() {
        int id;
        String yesOrNo;
        String name = null;
        int price = 0;
        int stock = 0;
        MenuDto oldDto = null;
        System.out.println("수정할 메뉴의 ID를 입력하세요.");
        id = sc.nextInt();
        oldDto = menuService.searchOne(id);
        if(oldDto == null){
            System.out.println("해당하는 ID가 없습니다");
            return;
        }
        System.out.println("기존 메뉴이름 : "+oldDto.name());
        do{
            System.out.println("수정하겠습니까?(Y/N)");
            yesOrNo = sc.next();
        }while(!(yesOrNo.toUpperCase().equals("Y") || yesOrNo.toUpperCase().equals("N")));
        if(yesOrNo.toUpperCase().equals("Y")) {
            System.out.println("수정할 메뉴이름 : ");
            name = sc.next();
        }else {
            name = oldDto.name();
        }
        System.out.println("기존 가격 : "+oldDto.price());
        do{
            System.out.println("수정하겠습니까?(Y/N)");
            yesOrNo = sc.next();
        }while(!(yesOrNo.toUpperCase().equals("Y") || yesOrNo.toUpperCase().equals("N")));
        if(yesOrNo.toUpperCase().equals("Y")) {
            System.out.println("수정할 가격 : ");
            price= sc.nextInt();
        }else {
            price = oldDto.price();
        }
        System.out.println("기존 재고수량 : "+oldDto.stock());
        do{
            System.out.println("수정하겠습니까?(Y/N)");
            yesOrNo = sc.next();
        }while(!(yesOrNo.toUpperCase().equals("Y") || yesOrNo.toUpperCase().equals("N")));
        if(yesOrNo.toUpperCase().equals("Y")) {
            System.out.println("수정할 재고수량 : ");
            stock= sc.nextInt();
        }else {
            stock = oldDto.price();
        }
        MenuDto dto = MenuDto.allOf(id, name, price, stock);
        menuService.updateMenu(dto,id);
    }

    public void deleteMenu() {
        int id;
        System.out.println("삭제할 메뉴의 ID를 입력하세요.");
        id = sc.nextInt();
        int result = menuService.deleteMenu(id);
        if (result !=0){
            System.out.println("ID : "+id+"의 자료가 삭제 되었습니다.");
        }else {
            System.out.println("자료 삭제에 실패하였습니다.");
        }
    }

    public void allMenu() {
        List<MenuDto> dtoList = new ArrayList<>();
        dtoList = menuService.getListAll();
        if(dtoList.isEmpty()){
            System.out.println("메뉴가 존재하지 않습니다.");
            return;
        }else {
            for(MenuDto dto : dtoList){
                System.out.println(dto);

            }
        }
    }
}
