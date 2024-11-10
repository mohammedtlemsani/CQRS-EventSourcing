package ma.enset.comptecqrses.query.entities;

import jakarta.persistence.*;
import ma.enset.comptecqrses.commonApi.enums.OperationType;

import java.util.Date;

@Entity
public class Operation {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    @ManyToOne
    private Account account;
}
