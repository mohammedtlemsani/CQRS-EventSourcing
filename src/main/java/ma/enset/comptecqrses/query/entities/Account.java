package ma.enset.comptecqrses.query.entities;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.comptecqrses.commonApi.enums.AccountStatus;

import java.util.Collection;
@Entity
@AllArgsConstructor@NoArgsConstructor@Data
public class Account {
    @Id
    private String id;
    private Double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @OneToMany(mappedBy = "account")
    private Collection<Operation> operations;


}
