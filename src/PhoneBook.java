import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PhoneBook {
    static Scanner sc = new Scanner(System.in);
    static Map<String, PhoneInfo> map = new HashMap<>();
    static Set<String> ks = map.keySet();

    public static void main(String[] args) {
        readInfo();
        int choice;
        while (true) {
            showMenu();
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addNumber();
                    break;
                case 2:
                    selNumber();
                    break;
                case 3:
                    editInfo();
                    break;
                case 4:
                    delNumber();
                    break;
                case 5:
                    listInfo();
                    break;
                case 6:
                    saveInfo();
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다. ");
                    break;
            }
        }
    }
    public static void showMenu() {
        System.out.println("[메뉴 선택]");
        System.out.println("1. 전화번호 입력");
        System.out.println("2. 전화번호 조회");
        System.out.println("3. 전화번호 수정");
        System.out.println("4. 전화번호 삭제");
        System.out.println("5. 나의 전화번호부");
        System.out.println("6. 종료");
        System.out.print("선택 : ");
    }

    public static void addNumber() {
        sc.nextLine();
        System.out.print("이름 : ");
        String name = sc.nextLine();
        System.out.print("전화번호 : ");
        String phoneNumber = sc.nextLine();
        System.out.print("이메일 : ");
        String email = sc.nextLine();

        PhoneInfo pInfo;
        if (email != null) {
            pInfo = new PhoneInfo(name, phoneNumber, email);
        } else {
            pInfo = new PhoneInfo(name, phoneNumber);
        }
        pInfo.showPhoneInfo();
        map.put(name, pInfo);
        System.out.println("맵의 크기 : " + map.size());
    }

    public static void selNumber() {
        System.out.print("조회할 이름 : ");
        sc.nextLine();
        String name = sc.nextLine();
        PhoneInfo pInfo = map.get(name);
        pInfo.showPhoneInfo();
    }

    public static void delNumber() {
        System.out.print("삭제할 이름 : ");
        sc.nextLine();
        String name = sc.nextLine();

        PhoneInfo pInfo = map.remove(name);
        if (pInfo != null) {
            System.out.println("삭제되었습니다. ");
        } else {
            System.out.println("해당 값이 없습니다. ");
        }
    }
    public static void editInfo() {
        System.out.print("수정할 이름 : ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("전화번호 : ");
        String EphoneNumber = sc.nextLine();
        System.out.print("이메일 : ");
        String Eemail = sc.nextLine();
        map.put(name, new PhoneInfo(name, EphoneNumber, Eemail));
    }
    public static void listInfo() {
        System.out.println("----나의 전화번호부----");
        for(String key : map.keySet()) {
         PhoneInfo Info = map.get(key);
         System.out.println(key + " =>");
         Info.showPhoneInfo();
        }
        System.out.println("-----------------");
    }
    public static void saveInfo() {
        try (ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream("./src/Object.bin"))) {
            Set<String> ks = map.keySet();
            for (String s : ks) {
                PhoneInfo pInfo = map.get(s);
                oo.writeObject(pInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readInfo() {
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream("./src/Object.bin"))) {
            while(true) {
                PhoneInfo pInfo = (PhoneInfo) oi.readObject();
                if(pInfo == null)
                    break;
                map.put(pInfo.name, pInfo);
            }
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            //e.printStackTrace();
        }
    }
}