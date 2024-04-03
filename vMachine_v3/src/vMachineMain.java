import view.MenuView;
import view.UserView;

import java.util.Scanner;

public class vMachineMain {
    public static void main(String[] args) {
        UserView userView = new UserView();
        MenuView menuView = new MenuView();
        Scanner sc = new Scanner(System.in);
        int ch = 0;
        int login = 0;
        while(true){
            do{
                System.out.println("1.회원가입  2.로그인");
                System.out.println("===================================");
                ch=sc.nextInt();
            }while (ch<0 || ch>2);
            switch (ch){
                case 1:
                    // 완료
                    userView.createUser();
                    break;
                case 2:
                    // 완료
                    login = userView.loginUser();
                    break;
            }
            if(login==1){
                while (true) {
                    do {
                        System.out.println("1.금액충전  2.제품구매");
                        ch = sc.nextInt();
                    } while (ch < 0 || ch > 2);
                    switch (ch) {
                        case 1:
                            // 완료
                            userView.chargeMoney();
                            break;
                        case 2:
                            menuView.buyMenu();
                            break;
                    }
                }
            } else if (login==2) {
                while(true) {
                    do {
                        System.out.println("1.회원관리 2.자판기관리 3.판매관리");
                        ch = sc.nextInt();
                    } while (ch < 0 || ch > 3);
                    switch (ch) {
                        case 1:
                            while(true) {
                                do {
                                    System.out.println("1.회원정보입력  2.회원정보수정  3.회원삭제  4.회원정보조회");
                                    ch = sc.nextInt();
                                } while (ch < 0 || ch > 4);
                                switch (ch) {
                                    case 1:
                                        // 완료
                                        userView.createUser();
                                        break;
                                    case 2:
                                        // 완료
                                        userView.updateUser();
                                        break;
                                    case 3:
                                        // 완료
                                        userView.deleteUser();
                                        break;
                                    case 4:
                                        // 완료
                                        userView.selectUser();
                                        break;
                                }
                                break;
                            }
                        case 2:
                            while (true) {
                                do {
                                    System.out.println("1.신규메뉴입력  2.메뉴수정  3.메뉴삭제  4.전체메뉴조회");
                                    ch = sc.nextInt();
                                } while (ch < 0 || ch > 4);
                                switch (ch) {
                                    case 1:
                                        // 완료
                                        menuView.insertMenu();
                                        break;
                                    case 2:
                                        // 완료
                                        menuView.updateMenu();
                                        break;
                                    case 3:
                                        // 완료
                                        menuView.deleteMenu();
                                        break;
                                    case 4:
                                        // 완료
                                        menuView.allMenu();
                                        break;

                                }
                            }
                        case 3:
                            while (true) {
                                do {
                                    System.out.println("1.제품별 판매현황  2.회원별 판매현황");
                                    ch = sc.nextInt();
                                } while (ch > 0 || ch > 2);
                                switch (ch) {
                                    case 1:

                                    case 2:
                                }
                            }
                    }
                }
            }
        }


    }
}
