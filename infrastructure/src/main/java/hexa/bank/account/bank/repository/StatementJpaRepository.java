package hexa.bank.account.bank.repository;

import hexa.bank.account.bank.entity.StatementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatementJpaRepository extends JpaRepository<StatementEntity, Long> {
}
