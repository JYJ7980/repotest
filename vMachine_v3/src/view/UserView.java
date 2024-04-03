package view;

import dto.UserDto;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserView {
    Scanner sc = new Scanner(System.in);
    UserService userService = new UserService();

    String id = null;
    public void createUser() {
        String pw = null;
        String name = null;
        String tel = null;
        System.out.println("신규 회원정보를 입력하십시오.");
        System.out.println("회원 아이디 : ");
        id = sc.next();
        System.out.println("비밀번호 : ");
        pw = sc.next();
        System.out.println("회원명 : ");
        name = sc.next();
        System.out.println("전화번호 : ");
        tel = sc.next();
        UserDto dto = UserDto.of(id, pw, name, tel);
        int result = userService.insertData(dto);
        if(result != 0){
            System.out.println("회원가입에 성공하셨습니다.");

        } else {
            System.out.println("회원가입에 실패하였습니다.");

        }


    }

    public int loginUser() {
        String pw = null;
        System.out.println("로그인 정보를 입력하십시오");
        System.out.println("회원 ID : ");
        id = sc.next();
        System.out.println("비밀번호 : ");
        pw = sc.next();
        int result = userService.loginData(id,pw);
        if(!(id.equals("root"))) {
            if (result == 1) {
                System.out.println("로그인 성공");
                System.out.println("남은 잔액은 "+userService.money+"원 입니다.");
                return 1;
            } else if (result == 2) {
                System.out.println("비밀번호가 일치하지 않습니다.");
            } else if (result == 3) {
                System.out.println("아이디가 존재하지 않습니다.");
            }
        }else {
            if(result==1){
                System.out.println("관리자 로그인 성공");
                return 2;
            }
        }
        return 0;
    }

    public void chargeMoney() {
        int money = 0;
        System.out.println("투입할 금액을 입력하세요.(1000원 단위)");
        money = sc.nextInt();
        if(money%1000 != 0){
            System.out.println(money-(money%1000)+"원을 충전합니다.");
            System.out.println("잔여금 "+ money%1000 + "원을 반환합니다.");
            money=money-(money%1000);
        }else{
            System.out.println(money +"원을 충전합니다.");
        }
        userService.updateMoney(money,id);
    }

    public void updateUser() {
        UserDto oldDto = null;
        String yesOrNo;
        String pw = null;
        String name = null;
        String tel = null;
        int money = 0;
        System.out.println("정보를 수정할 유저의 ID를 입력하세요");
        id = sc.next();
        oldDto = userService.searchOne(id);
        System.out.println(oldDto);
        if(oldDto == null){
            System.out.println("해당하는 ID가 없습니다");
            return;
        }
        System.out.println("기존 비밀번호 : "+oldDto.pw());
        do{
            System.out.println("수정하겠습니까?(Y/N)");
            yesOrNo = sc.next();
        }while(!(yesOrNo.toUpperCase().equals("Y") || yesOrNo.toUpperCase().equals("N")));
        if(yesOrNo.toUpperCase().equals("Y")) {
            System.out.println("수정할 비밀번호 : ");
            pw = sc.next();
        }else {
            pw = oldDto.pw();
        }
        System.out.println("기존 회원명 : "+oldDto.name());
        do{
            System.out.println("수정하겠습니까?(Y/N)");
            yesOrNo = sc.next();
        }while(!(yesOrNo.toUpperCase().equals("Y") || yesOrNo.toUpperCase().equals("N")));
        if(yesOrNo.toUpperCase().equals("Y")) {
            System.out.println("수정할 회원명 : ");
            name = sc.next();
        } else {
            name = oldDto.name();
        }
        System.out.println("기존 전화번호 : "+oldDto.tel());
        do{
            System.out.println("수정하겠습니까?(Y/N)");
            yesOrNo = sc.next();
        }while(!(yesOrNo.toUpperCase().equals("Y") || yesOrNo.toUpperCase().equals("N")));
        if(yesOrNo.toUpperCase().equals("Y")) {
            System.out.println("수정할 전화번호 : ");
            tel = sc.next();
        } else {
            tel = oldDto.tel();
        }
        System.out.println("기존 금액 : "+oldDto.money());
        do{
            System.out.println("수정하겠습니까?(Y/N)");
            yesOrNo = sc.next();
        }while(!(yesOrNo.toUpperCase().equals("Y") || yesOrNo.toUpperCase().equals("N")));
        if(yesOrNo.toUpperCase().equals("Y")) {
            System.out.println("수정할 금액 : ");
            money = sc.nextInt();
        }else{
            money=oldDto.money();
        }
        UserDto dto = UserDto.allOf(id,pw,name,tel,money);
        userService.updateUserData(dto, id);
    }

    public void deleteUser() {
        String yesOrNo;
        System.out.println("삭제할 ID 입력");
        id = sc.next();
        UserDto dto = userService.searchOne(id);
        if(dto==null){
            System.out.println("해당 ID는 존재하지 않습니다.");
            return;
        }else {
            do{
                System.out.println("정말로 삭제하시겠습니까?(Y/N)");
                yesOrNo = sc.next();
            }while (!(yesOrNo.toUpperCase().equals("Y") || yesOrNo.toUpperCase().equals("N")));
            if(yesOrNo.toUpperCase().equals("Y")){
                userService.deleteData(id);
                System.out.println("데이터가 삭제되었습니다.");
            }else {
                System.out.println("데이터 삭제를 취소하였습니다.");
                return;
            }
        }
    }

    public void selectUser() {
        System.out.println("검색할 ID 입력");
        id = sc.next();
        UserDto dto = userService.searchOne(id);
        if(dto==null){
            System.out.println("찾는 데이터가 없습니다.");
        }else{
            System.out.println(dto);
        }
    }
}
