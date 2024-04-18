package uz.atm_v_3.service.checkAndInfo;

import org.springframework.stereotype.Component;
import uz.atm_v_3.dto.request.CashingRequestDTO;
import uz.atm_v_3.dto.request.FillOutRequestDTO;
import uz.atm_v_3.dto.request.TransferRequestDTO;
import uz.atm_v_3.exception.CardException;
import uz.atm_v_3.model.Card;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CheckCard {

    public void checkCardForCashing(CashingRequestDTO cashingRequestDTO, Card card) {

        double amountDouble = Double.parseDouble(cashingRequestDTO.getAmount());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expireDate = LocalDate.parse(card.getCardExpireDate(), formatter);

        if (!card.getIsActive()) {
            throw new CardException("Card is blocked");
        }
        if (expireDate.isBefore(LocalDate.now())) {
            throw new CardException("Card is expired");
        }
        if (!card.getCardPin().equals(cashingRequestDTO.getCardPin())) {
            throw new CardException("Card pin is incorrect");
        }
        if (!card.getCardType().getName().equals("VISA")) {
            if (amountDouble < 11000) {
                throw new CardException("Amount must be greater than 11000");
            }
            if (amountDouble % 1000 != 0) {
                throw new CardException("Amount must be multiple of 1000");
            }
        } else {
            if (amountDouble < 10) {
                throw new CardException("Amount must be greater than 10");
            }
            if (amountDouble % 5 != 0) {
                throw new CardException("Amount must be multiple of 5");
            }
        }

    }

    private double amountDouble(FillOutRequestDTO fillOutRequestDTO) {
        return Double.parseDouble(fillOutRequestDTO.getAmount());
    }

    public void checkingForFillOut(FillOutRequestDTO fillOutRequestDTO, Card card) {
        double amountDouble = Double.parseDouble(fillOutRequestDTO.getAmount());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expireDate = LocalDate.parse(card.getCardExpireDate(), formatter);

        if (!card.getIsActive()) {
            throw new CardException("Card is blocked");
        }
        if (expireDate.isBefore(LocalDate.now())) {
            throw new CardException("Card is expired");
        }
        if (!card.getCardPin().equals(fillOutRequestDTO.getCardPin())) {
            throw new CardException("Card pin is incorrect");
        }
        if (!card.getCardType().getName().equals("VISA")) {
            if (amountDouble < 11000) {
                throw new CardException("Amount must be greater than 11000");
            }
            if (amountDouble % 1000 != 0) {
                throw new CardException("Amount must be multiple of 1000");
            }
        } else {
            if (amountDouble < 10) {
                throw new CardException("Amount must be greater than 10");
            }
            if (amountDouble % 5 != 0) {
                throw new CardException("Amount must be multiple of 5");
            }
        }

    }

    public void checkTransferCard(TransferRequestDTO transferRequestDTO, Card cardFrom, Card cardTo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expireDateFrom = LocalDate.parse(cardFrom.getCardExpireDate(), formatter);
        LocalDate expireDateTo = LocalDate.parse(cardTo.getCardExpireDate(), formatter);

        if (cardFrom.getCardType().getName().equals("VISA") && !cardTo.getCardType().getName().equals("VISA")) {
            throw new CardException("You can't transfer money to other card types");
        }

        if ((cardFrom.getCardType().getName().equals("UZCARD") || cardFrom.getCardType().getName().equals("HUMO") &&
                cardTo.getCardType().getName().equals("VISA"))) {
            throw new CardException("You can't transfer money to VISA card");

        }
        if (!cardFrom.getIsActive()) {
            throw new CardException("Your card is blocked: " + cardFrom.getCardNumber());
        }
        if (!cardTo.getIsActive()) {
            throw new CardException("Receiver card is blocked: " + cardTo.getCardNumber());
        }
        if (expireDateFrom.isBefore(LocalDate.now())) {
            throw new CardException("Your card is expired");
        }
        if (expireDateTo.isBefore(LocalDate.now())) {
            throw new CardException("Receiver card is expired");
        }
        if (!cardFrom.getCardPin().equals(transferRequestDTO.getPin())) {
            throw new CardException("Card pin is incorrect");
        }

        if (cardFrom.getCardNumber().equals(cardTo.getCardNumber())) {
            throw new CardException("You can't transfer money to yourself");
        }

        String balanceWithoutCommasCardFrom = cardFrom.getBalance().replaceAll(",", ".");
        double amountDouble = Double.parseDouble(transferRequestDTO.getAmount());
        double commission = amountDouble * 0.01;
        double cardBalance = Double.parseDouble(balanceWithoutCommasCardFrom);

        if (cardBalance < amountDouble + commission) {
            throw new CardException("Not enough money in the card: " + cardFrom.getCardNumber());
        }

    }

    public boolean checkPin(String pin, String cardPin) {
        // check pin 3 times and block card
        for (int i = 0; i < 3; i++) {
            if (pin.equals(cardPin)) {
                return true;
            }
        }
        return false;
    }

}
