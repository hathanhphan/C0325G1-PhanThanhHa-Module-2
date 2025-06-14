package ss19_string_and_regex.thuc_hanh.validate_account;

public class AccountExampleTest {

    public static final String[] validAccount = new String[]{"123abc_", "_abc123", "______", "123456", "abcdefgh"};
    public static final String[] invalidAccount = new String[]{".@", "12345", "1234_", "abcde"};

    public static void main(String[] args) {
        AccountExample accountExample = new AccountExample();
        boolean isValid;
        for (String account : validAccount) {
            isValid = accountExample.validate(account);
            System.out.println("Account is " + account + " is valid: " + isValid);
        }
        for (String account : invalidAccount) {
            isValid = accountExample.validate(account);
            System.out.println("Account is " + account + " is valid: " + isValid);
        }
    }
}
