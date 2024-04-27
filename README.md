# ATM -service

### Automation of ATM operation systems

This system includes:

* Reformed a cardHolder details
* Chance to perform actions on Banknote
* Chance to perform actions on card
* The ability to withdraw cash from the card through an ATM
* Possibility to top up the card through ATM
* The ability to make transfers between cards
* Chance to perform actions on card type
* The ability to get all transactions performed through the card in a sorted order

@SpringBootApplication
public class AtmCardHolderApplication {
```java
    public static void main(String[] args) {
        SpringApplication.run(AtmCardHolderApplication.class, args);
    }

}
```

***You can get the services listed above in this table through the links***

| No |                                                                    Services                                                                     | Status |
|:--:|:-----------------------------------------------------------------------------------------------------------------------------------------------:|:------:|
| 1  |   [Card Holder Service](https://github.com/KuronboevAsadbek/ATM_V3/blob/master/src/main/java/uz/atm_v_3/controller/CardHolderController.java)   |   ✅    |
| 2  |   [BankNote  Service](https://github.com/KuronboevAsadbek/ATM_V3/blob/master/src/main/java/uz/atm_v_3/controller/BanknoteTypeController.java)   |   ✅    |
| 3  |         [Card Service](https://github.com/KuronboevAsadbek/ATM_V3/blob/master/src/main/java/uz/atm_v_3/controller/CardController.java)          |   ✅    |
| 4  |      [Cashing Service](https://github.com/KuronboevAsadbek/ATM_V3/blob/master/src/main/java/uz/atm_v_3/controller/CashingController.java)       |   ✅    |
| 5  | [Fill Out  Card Service](https://github.com/KuronboevAsadbek/ATM_V3/blob/master/src/main/java/uz/atm_v_3/controller/FillOutCardController.java) |   ✅    |
| 6  |     [Transfer Service ](https://github.com/KuronboevAsadbek/ATM_V3/blob/master/src/main/java/uz/atm_v_3/controller/TransferController.java)     |   ✅    |
| 7  |     [Card Type Service](https://github.com/KuronboevAsadbek/ATM_V3/blob/master/src/main/java/uz/atm_v_3/controller/CardTypeController.java)     |   ✅    |
| 8  |      [Get Card History](https://github.com/KuronboevAsadbek/ATM_V3/blob/master/src/main/java/uz/atm_v_3/controller/GetCardController.java)      |   ✅    |
