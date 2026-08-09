package assignments.assignment09;

class LibraryBook {
    private final String isbn;
    private final String title;

    LibraryBook(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    final void display() {
        System.out.println("ISBN: " + isbn);
        System.out.println("Title: " + title);
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        new LibraryBook("978-0135166307", "Core Java").display();
    }
}
