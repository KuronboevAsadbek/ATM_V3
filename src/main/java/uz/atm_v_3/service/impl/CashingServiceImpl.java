package uz.atm_v_3.service.impl;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uz.atm_v_3.dto.request.CashingRequestDTO;
import uz.atm_v_3.dto.response.CashingResponseDTO;
import uz.atm_v_3.exception.CardException;
import uz.atm_v_3.model.Atm;
import uz.atm_v_3.model.Card;
import uz.atm_v_3.repository.AtmRepository;
import uz.atm_v_3.repository.CardRepository;
import uz.atm_v_3.service.CashingService;
import uz.atm_v_3.service.checkAndInfo.CheckCard;
import uz.atm_v_3.service.checkAndInfo.ClientInfoService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CashingServiceImpl implements CashingService {

    private static final Logger LOG = LoggerFactory.getLogger(CardServiceImpl.class);
    private final Gson gson;
    private final CheckCard checkCard;
    private final CardRepository cardRepository;
    private final AtmRepository atmRepository;
    private final ClientInfoService clientInfoService;

    @Override
    public CashingResponseDTO cashingCardBalanceWithBanknote(CashingRequestDTO cashingRequestDTO, HttpServletRequest request) {
        Card card = cardRepository.findCardByCardNumber(cashingRequestDTO.getCardNumber());

        clientInfoService.getLogger(request);
        if (card == null) {
            throw new CardException("Card blocked or not found");
        }
        checkCard.checkCardForCashing(cashingRequestDTO, card);
        double amountDouble = Double.parseDouble(cashingRequestDTO.getAmount());
        double commission = amountDouble * 0.01;
        double cashingAmount = amountDouble;
        String balanceWithoutCommas = card.getBalance().replaceAll(",", ".");
        double cardBalance = Double.parseDouble(balanceWithoutCommas);
        String cardBalance2;


        if (cardBalance < amountDouble + commission) {
            throw new CardException("Not enough money in the card: " + card.getCardNumber());
        }

        if (amountDouble % 5000 != 0) {
            throw new CardException("Amount must be multiple of 5000");
        }
        if (cashingRequestDTO.getCashingBanknoteType().equals("SMALL")) {
            Atm atm20000 = atmRepository.findBanknoteByType("TWENTY_THOUSAND");
            Atm atm10000 = atmRepository.findBanknoteByType("TEN_THOUSAND");
            Atm atm5000 = atmRepository.findBanknoteByType("FIVE_THOUSAND");
            int sym20 = atm20000.getBanknoteCount();
            int sym10 = atm10000.getBanknoteCount();
            int sym5 = atm5000.getBanknoteCount();
            int mycount20 = 0;
            int mycount10 = 0;
            int mycount5 = 0;
            while (amountDouble >= 20000 && sym20 > 0) {
                amountDouble -= 20000;
                mycount20++;
                sym20 = sym20 - 1;

            }

            while (amountDouble >= 10000 && sym10 > 0) {
                amountDouble -= 10000;
                mycount10++;
                sym10 = sym10 - 1;

            }

            while (amountDouble >= 5000 && sym5 > 0) {
                amountDouble -= 5000;
                mycount5++;
                sym5 = sym5 - 1;

            }

            if (amountDouble > 0) {
                LOG.error("Choose another banknote type or amount is not enough");
                throw new CardException("Choose another banknote type or amount is not enough");
            }
            if (mycount20 > 0) {
                atm20000.setBanknoteCount(atm20000.getBanknoteCount() - mycount20);
            }
            if (mycount10 > 0) {
                atm10000.setBanknoteCount(atm10000.getBanknoteCount() - mycount10);
            }
            if (mycount5 > 0) {
                atm5000.setBanknoteCount(atm5000.getBanknoteCount() - mycount5);
            }
            List<Atm> atmList = new ArrayList<>();
            atmList.add(atm20000);
            atmList.add(atm10000);
            atmList.add(atm5000);
            atmRepository.saveAll(atmList);
            cardBalance -= cashingAmount + commission;
            cardBalance2 = String.format("%.2f", cardBalance);
            card.setBalance(String.valueOf(cardBalance2));
            cardRepository.save(card);

        } else if (cashingRequestDTO.getCashingBanknoteType().

                equals("MIXED")) {
            Atm atm200000 = atmRepository.findBanknoteByType("TWO_HUNDRED_THOUSAND");
            Atm atm100000 = atmRepository.findBanknoteByType("HUNDRED_THOUSAND");
            Atm atm50000 = atmRepository.findBanknoteByType("FIFTY_THOUSAND");
            Atm atm20000 = atmRepository.findBanknoteByType("TWENTY_THOUSAND");
            Atm atm10000 = atmRepository.findBanknoteByType("TEN_THOUSAND");
            Atm atm5000 = atmRepository.findBanknoteByType("FIVE_THOUSAND");
            int sym200 = atm200000.getBanknoteCount();
            int sym100 = atm100000.getBanknoteCount();
            int sym50 = atm50000.getBanknoteCount();
            int sym20 = atm20000.getBanknoteCount();
            int sym10 = atm10000.getBanknoteCount();
            int sym5 = atm5000.getBanknoteCount();
            int count200 = 0;
            int count100 = 0;
            int count50 = 0;
            int count20 = 0;
            int count10 = 0;
            int count5 = 0;

            while (amountDouble >= 200000 && sym200 > 0) {
                amountDouble -= 200000;
                count200++;
                sym200 -= 1;
            }

            while (amountDouble >= 100000 && sym100 > 0) {

                amountDouble -= 100000;
                count100++;
                sym100 -= 1;
            }
            while (amountDouble >= 50000 && sym50 > 0) {
                amountDouble -= 50000;
                count50++;
                sym50 -= 1;

            }
            while (amountDouble >= 20000 && sym20 > 0) {

                amountDouble -= 20000;
                count20++;
                sym20 -= 1;
            }
            while (amountDouble >= 10000 && sym10 > 0) {

                amountDouble -= 10000;
                count10++;
                sym10 -= 1;

            }
            while (amountDouble >= 5000 && sym5 > 0) {

                amountDouble -= 5000;
                count5++;
                sym5 -= 1;

            }
            if (amountDouble > 0) {
                LOG.error("Choose another banknote type or amount is not enough");
                throw new CardException("ATM is empty");
            }
            if (count200 > 0) {
                atm200000.setBanknoteCount(atm200000.getBanknoteCount() - count200);
            }
            if (count100 > 0) {
                atm100000.setBanknoteCount(atm100000.getBanknoteCount() - count100);
            }
            if (count50 > 0) {
                atm50000.setBanknoteCount(atm50000.getBanknoteCount() - count50);
            }
            if (count20 > 0) {
                atm20000.setBanknoteCount(atm20000.getBanknoteCount() - count20);
            }
            if (count10 > 0) {
                atm10000.setBanknoteCount(atm10000.getBanknoteCount() - count10);
            }
            if (count5 > 0) {
                atm5000.setBanknoteCount(atm5000.getBanknoteCount() - count5);
            }
            List<Atm> atmList = new ArrayList<>();
            atmList.add(atm200000);
            atmList.add(atm100000);
            atmList.add(atm50000);
            atmList.add(atm20000);
            atmList.add(atm10000);
            atmList.add(atm5000);
            atmRepository.saveAll(atmList);
            cardBalance -= cashingAmount + commission;
            cardBalance2 = String.format("%.2f", cardBalance);
            card.setBalance(String.valueOf(cardBalance2));
            cardRepository.save(card);


        } else {
            LOG.error("ATM is empty");
            throw new CardException("ATM is empty");
        }
        return CashingResponseDTO.builder()
                .message("Cashing is successful")
                .cashedAmount(cashingRequestDTO.getAmount())
                .commission(String.valueOf(commission))
                .balance(card.getBalance())
                .build();
    }
}


