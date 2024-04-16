package uz.atm_v_3.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum Banknotes {

    BANKNOTE_1000("1000"),
    BANKNOTE_5000("5000"),
    BANKNOTE_10000("10000"),
    BANKNOTE_20000("20000"),
    BANKNOTE_50000("50000"),
    BANKNOTE_100000("100000"),
    BANKNOTE_200000("200000");

    private final String banknoteType;

    Banknotes(String banknoteType) {
        this.banknoteType = banknoteType;
    }
}
