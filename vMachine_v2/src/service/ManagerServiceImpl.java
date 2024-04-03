package service;

import myInterface.ManagerInterface;
import vo.ProductVO;

import java.util.Scanner;

public class ManagerServiceImpl implements ManagerInterface {
    Scanner sc = new Scanner(System.in);

    @Override
    public void enterMenu() {
        System.out.println("자판기 메뉴 등록");
        System.out.println("제품 이름을 입력하세요");
        String item = sc.next();
        for(ProductVO product : InitClazz.lists){
            if(item.equals(product.getDrinkName())){
                System.out.println("이미 존재하는 제품입니다.");
                return;
            }
        }
        System.out.println("제품의 가격?(숫자만 입력)");
        int price = sc.nextInt();
        System.out.println("재고 등록(채울 개수)");
        int stock = sc.nextInt();
        ProductVO product = new ProductVO(item,price,stock);
        InitClazz.lists.add(product);

    }

    @Override
    public void deleteMenu() {
        InitClazz.printMenu();
        System.out.println("삭제할 제품명 입력");
        String item = sc.next();
        boolean flag = true;
        for(ProductVO product : InitClazz.lists){
            if(item.equals(product.getDrinkName())){
                InitClazz.lists.remove(product);
                System.out.println(item+" 삭제완료");
                flag = false;
                break;
            }
        }
        if(flag == true){
            System.out.println("입력한 메뉴가 존재하지 않습니다.");
        }

    }

    @Override
    public void updateMenu() {
        InitClazz.printMenu();
        System.out.println("수정할 제품명 입력 : ");
        String item = sc.next();
        if(InitClazz.lists.isEmpty()){
            System.out.println("수정할 상품이 존재하지 않습니다.");
            return;
        }else{
            boolean flag =true;
            for(ProductVO product : InitClazz.lists) {
                if(item.equals(product.getDrinkName())){
                    System.out.println("(수정) 메뉴 이름? ");
                    product.setDrinkName(sc.next());
                    System.out.println("(수정) 메뉴 가격? (숫자만 입력)");
                    product.setDrinkPrice(sc.nextInt());
                    System.out.println("(수정) 재고 수량?(숫자만 입력)");
                    product.setDrinkStock(sc.nextInt());
                    flag = false;
                    break;
                }
            }
            if(flag==true){
                System.out.println("입력한 메뉴가 존재하지않습니다.");
            }
            System.out.println("");
        }
    }

    @Override
    public void enterStock() {
        System.out.println("재고 수정할 제품명 입력");
        String item = sc.next();
        boolean flag = true;
        for(ProductVO product : InitClazz.lists){
            if(item.equals(product.getDrinkName())){
                System.out.println("(수정) 재고 수량?(숫자만 입력)");
                int updateStock = sc.nextInt();
                product.setDrinkStock(product.getDrinkStock()+updateStock);
                flag = false;
                break;
            }
        }
        if(flag == true){
            System.out.println("입력한 메뉴가 존재하지 않습니다.");
        }
    }
}
