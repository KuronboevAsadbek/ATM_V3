package uz.atm_v_3.service.checkAndInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.atm_v_3.dto.request.CashingRequestDTO;
import uz.atm_v_3.dto.request.FillOutRequestDTO;
import uz.atm_v_3.dto.request.TransferRequestDTO;
import uz.atm_v_3.exception.CardException;
import uz.atm_v_3.exception.CardTypeException;
import uz.atm_v_3.exception.CheckPinException;
import uz.atm_v_3.model.Card;
import uz.atm_v_3.repository.CardRepository;
import uz.atm_v_3.repository.CurrencyTypeRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class CheckCard {

    private final CurrencyTypeRepository currencyTypeRepository;
    private final CardRepository cardRepository;

    public void checkCardForCashing(CashingRequestDTO cashingRequestDTO, Card card) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expireDate = LocalDate.parse(card.getCardExpireDate(), formatter);



        if (!card.getIsActive()) {
            throw new CardException("Card is blocked");
        }
        if (expireDate.isBefore(LocalDate.now())) {
            throw new CardException("Card is expired");
        }

    }



    public void checkingForFillOut(FillOutRequestDTO fillOutRequestDTO, Card card) {


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


    }

    public void checkTransferCard(TransferRequestDTO transferRequestDTO, Card cardFrom, Card cardTo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expireDateFrom = LocalDate.parse(cardFrom.getCardExpireDate(), formatter);
        LocalDate expireDateTo = LocalDate.parse(cardTo.getCardExpireDate(), formatter);

        if (cardFrom.getCardType().getCurrencyType().getName().equals("USD") && !cardTo.getCardType().getCurrencyType().getName().equals("USD")) {
            throw new CardException("You can't transfer money to other card types");
        }

        if (cardFrom.getCardType().getCurrencyType().getName().equals("UZS") && !cardTo.getCardType().getCurrencyType()
                .getName().equals("UZS")) {
            throw new CardException("You can't transfer money to other card types");

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

    public boolean checkPin(String pin, Card card) {
        int pinCount = card.getCheckCardQuantity();
        if (card.getCardPin().equals(pin)) {
            card.setCheckCardQuantity(0);
            cardRepository.save(card);
            return true;
        } else if (pinCount <= 1) {
            card.setCheckCardQuantity(pinCount + 1);
            cardRepository.save(card);
            throw new CheckPinException("Pin is incorrect attempts left: " + (2 - pinCount));
        }else {
            card.setIsActive(false);
            cardRepository.save(card);
            throw new CheckPinException("Card is blocked");
        }
    }
}
