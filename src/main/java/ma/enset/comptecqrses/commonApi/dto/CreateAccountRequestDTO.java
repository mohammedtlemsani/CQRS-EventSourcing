package ma.enset.comptecqrses.commonApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor
public class CreateAccountRequestDTO {
    public Double initialBalance;
    private String currency;


}
