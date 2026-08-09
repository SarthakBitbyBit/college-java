package assignments.assignment12;

class Book {
    private final int bookId;
    private final String title;
    private final String author;
    private final double price;

    Book(int bookId, String title, String author, double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    void display() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: Rs. " + price);
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        Book book = new Book(1001, "Core Java", "Cay S. Horstmann", 899.00);
        book.display();
    }
}
