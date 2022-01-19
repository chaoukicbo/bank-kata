package hexa.bank.account.bank.repository;

import hexa.bank.account.bank.entity.StatementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatementJpaRepository extends JpaRepository<StatementEntity, Long> {

    /**
     * find all transactions in an account
     * to check history
     *
     * @param clientNumber the client number
     * @param accountNumber the account number
     * @return a list of all Statement related to @param clientNumber and @param accountNumber
     */
    @Query("from StatementEntity s where s.account.number = :accountNumber and s.account.client.number = :clientNumber order by id desc")
    List<StatementEntity> findAllByAccountOrderByIdDesc(@Param("clientNumber") Integer clientNumber, @Param("accountNumber") Integer accountNumber);
}
