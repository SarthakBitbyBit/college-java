package assignments.assignment06;

interface Notification {
    void send(String recipient);
}

class UserAccount {
    private final String username;

    UserAccount(String username) {
        this.username = username;
    }

    class Profile {
        void display() {
            System.out.println("Username: " + username);
        }
    }

    void notifyUser() {
        Notification notification = new Notification() {
            @Override
            public void send(String recipient) {
                System.out.println("Notification sent to " + recipient);
            }
        };
        notification.send(username);
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        UserAccount account = new UserAccount("student@example.com");
        account.new Profile().display();
        account.notifyUser();
    }
}
