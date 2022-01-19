package hexa.bank.account.bank.repository;

import hexa.bank.account.bank.entity.StatementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatementJpaRepository extends JpaRepository<StatementEntity, Long> {
}
