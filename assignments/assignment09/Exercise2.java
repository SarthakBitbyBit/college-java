package assignments.assignment09;

final class LibraryBook {
    private final String isbn;
    private final String title;
    private final String author;
    private final double price;

    LibraryBook(String isbn, String title, String author, double price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    final void display() {
        System.out.println("ISBN: " + isbn);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: Rs. " + price);
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        new LibraryBook("978-0135166307", "Core Java", "Cay S. Horstmann", 899).display();
    }
}
