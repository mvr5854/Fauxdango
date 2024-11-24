package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdvertisementBankTest {
    private AdvertisementBank adBank;

    @BeforeEach
    void setUp() {
        adBank = new AdvertisementBank();
        adBank.clearAds();

        adBank.addAd(new Advertisement("Drink Pepsi"));
        adBank.addAd(new Advertisement("Buy Candy"));
    }

    @Test
    void getNextAd__call_once__return_secondAd() {
        Advertisement ad = adBank.getNextAd();
        assertEquals("Buy Candy", ad.getText());
    }

    @Test
    void getNextAd__call_twice__return_firstAdAfterSecondAd() {
        Advertisement ad1 = adBank.getNextAd();
        Advertisement ad2 = adBank.getNextAd();

        assertEquals("Buy Candy", ad1.getText());
        assertEquals("Drink Pepsi", ad2.getText());
    }
}