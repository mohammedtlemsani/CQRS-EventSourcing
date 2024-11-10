package ma.enset.comptecqrses.commonApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor@NoArgsConstructor
public class DebitAccountDTO {
    private String accountID;
    private Double amount;
    private String currency;
}
