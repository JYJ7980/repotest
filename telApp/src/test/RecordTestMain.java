package test;

public class RecordTestMain {
    public static void main(String[] args) {
        System.out.println("bookEntitiy======================================");
        BookEntity book = new BookEntity(1,"Adam", 10, "서울");
        System.out.println(book.toString());

        BookDto dto = BookDto.of(
                book.getId(),
                book.getName(),
                book.getAge(),
                book.getAddr()
        );
        System.out.println("dto======================================");
        System.out.println(dto);
        System.out.println("dto2======================================");
        BookDto dto2 = BookDto.from(book);
        System.out.println(dto2);
        System.out.println("entity======================================");
        BookEntity entity = dto2.toEntity();
        System.out.println(entity);
//        Person person = new Person("김형민", 30);
//        System.out.println(person);
//        Person person1 = new Person("김형민", 30);
//        Person person2 = new Person("김형민", 25);
//        if(person.equals(person2)){
//            System.out.println("같다.");
//        } else {
//            System.out.println("같지않다.");
//        }
    }
}
