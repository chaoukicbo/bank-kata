package hexa.bank.account.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import hexa.bank.account.bank.entity.AccountEntity;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByNumber(Integer number);

    @Query("from AccountEntity a where a.number = :accountNumber and a.client.number = :clientNumber")
    Optional<AccountEntity> findByAccountNumberAndClientNumber(@Param("accountNumber") Integer accountNumber, @Param("clientNumber") Integer clientNumber);
}
