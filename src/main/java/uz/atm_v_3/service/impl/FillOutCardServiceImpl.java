package uz.atm_v_3.service.impl;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uz.atm_v_3.dto.request.FillOutRequestDTO;
import uz.atm_v_3.dto.response.FillOutResponseDTO;
import uz.atm_v_3.exception.CardException;
import uz.atm_v_3.model.Card;
import uz.atm_v_3.repository.CardRepository;
import uz.atm_v_3.service.FillOutCardService;
import uz.atm_v_3.service.checkAndInfo.CheckCard;
import uz.atm_v_3.service.checkAndInfo.ClientInfoService;

@Service
@RequiredArgsConstructor
@Slf4j
public class FillOutCardServiceImpl implements FillOutCardService {

    private static final Logger LOG = LoggerFactory.getLogger(CardServiceImpl.class);
    private final Gson gson;
    private final ClientInfoService clientInfoService;
    private final CardRepository cardRepository;
    private final CheckCard cardCheck;


    @Override
    public FillOutResponseDTO fillCardBalance(FillOutRequestDTO fillOutRequestDTO, HttpServletRequest request) {
        Card card = cardRepository.findCardByCardNumber(fillOutRequestDTO.getCardNumber());
        if (card == null) {
            throw new CardException("Card blocked or not found");
        }

        if (!cardCheck.checkPin(fillOutRequestDTO.getCardPin(), card.getCardPin())) {
            card.setIsActive(false);
            cardRepository.save(card);
            throw new CardException("Card is blocked or not found");
        }
        cardCheck.checkingForFillOut(fillOutRequestDTO, card);
        double amountDouble = Double.parseDouble(fillOutRequestDTO.getAmount());
        String balanceWithoutCommas = card.getBalance().replaceAll(",", ".");
        double cardBalance = Double.parseDouble(balanceWithoutCommas);
        String cardBalance2;


        try {
            clientInfoService.getLogger(request);
            double commission = amountDouble * 0.01;
            cardBalance += amountDouble - commission;
            cardBalance2 = String.format("%.2f", cardBalance);
            card.setBalance(String.valueOf(cardBalance2));
            cardRepository.save(card);
            LOG.info("Card balance filled: {}", gson.toJson(card));
            return FillOutResponseDTO.builder()
                    .message("Card balance filled")
                    .balance(card.getBalance())
                    .filledAmount(String.valueOf(amountDouble))
                    .commission(String.valueOf(commission))
                    .build();
        } catch (Exception e) {
            LOG.error("Card balance Not Filled: {}", e.getMessage());
            throw new CardException("Error fillCardBalance card balance: " + e.getMessage());
        }
    }
}
