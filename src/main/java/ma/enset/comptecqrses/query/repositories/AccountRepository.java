package ma.enset.comptecqrses.query.repositories;

import ma.enset.comptecqrses.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface AccountRepository extends JpaRepository<Account,String> {
}
