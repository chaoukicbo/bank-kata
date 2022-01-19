package hexa.bank.account.bank.model;


public class Client {

    private Integer number;
    private String firstName;
    private String lastName;

    public Client() {
        super();
    }

    public Client(Integer number, String firstName, String lastName) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getNumber() {
        return number;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
