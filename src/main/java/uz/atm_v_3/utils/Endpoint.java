package uz.atm_v_3.utils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Endpoint {


    public static final String CARD_HOLDER = "/cardholder";
    public static final String CARD = "/card";
    public static final String CREATE = "/create";
    public static final String UPDATE = "/update";
    public static final String GET_CARD_BY_ID = "/get/{id}";
    public static final String GET_ALL_CARDS = "/get/all";
    public static final String GET_ALL_CARDS_BY_CARD_HOLDER_PINFL = "/get/all/pinfl/";
    public static final String FILL_OUT_BALANCE = "/fillout/balance";
}
