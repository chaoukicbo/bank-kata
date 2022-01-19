package hexa.bank.account.bank.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "statement")
@Getter
@Builder
public class StatementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OperationType operation;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private Double balance;
    @ManyToOne
    private AccountEntity account;

    public StatementEntity() {
        super();
    }

    public StatementEntity(Long id, OperationType operation, LocalDate date, Double amount, Double balance, AccountEntity account) {
        this.id = id;
        this.operation = operation;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
        this.account = account;
    }

    public StatementEntity(OperationType operation, LocalDate date, Double amount, Double balance, AccountEntity account) {
        this.operation = operation;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
        this.account = account;
    }
}
