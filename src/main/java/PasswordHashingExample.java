import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashingExample {
    public static void main(String[] args) {
        String originalPassword = "mySecretPassword";
        String hashedPassword = BCrypt.hashpw(originalPassword, BCrypt.gensalt());

        System.out.println("Original Password: " + originalPassword);
        System.out.println("Hashed Password: " + hashedPassword);
    }
}
