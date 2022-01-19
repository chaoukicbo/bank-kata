package hexa.bank.account.bank.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "client")
@Getter
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private Integer number;
    @Column(unique = true, nullable = false)
    private String firstName;
    @Column(unique = true, nullable = false)
    private String lastName;

    public ClientEntity() {
        super();
    }

    public ClientEntity(Integer number, String firstName, String lastName) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
