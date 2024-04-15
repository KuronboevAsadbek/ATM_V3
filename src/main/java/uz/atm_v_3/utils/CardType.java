package uz.atm_v_3.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum CardType {

    UZCARD("UzCard"),
    HUMO("Humo"),
    VISA("Visa"),
    MASTER_CARD("MasterCard");
    private String value;

    CardType(String value) {
        this.value = value;
    }
}
