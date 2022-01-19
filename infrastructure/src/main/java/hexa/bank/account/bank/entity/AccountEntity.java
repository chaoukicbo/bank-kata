package hexa.bank.account.bank.entity;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private Integer number;
    @Column(nullable = false)
    private Double balance;
    @ManyToOne
    private ClientEntity client;

    public AccountEntity() {
        super();
    }

    public AccountEntity(Long id, Integer number, Double balance, ClientEntity client) {
        this.id = id;
        this.number = number;
        this.balance = balance;
        this.client = client;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public Integer getNumber() {
        return number;
    }

    public ClientEntity getClient() {
        return client;
    }
}
