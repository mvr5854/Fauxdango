package edu.psu.fauxdango.View.ConsoleDisplay;

import edu.psu.fauxdango.Model.Advertisement;
import edu.psu.fauxdango.Model.AdvertisementBank;


public class AdvertisementDisplay {
    private AdvertisementBank adBank;

    public AdvertisementDisplay(AdvertisementBank adBank) {
        this.adBank = adBank;
    }

    private void displayAd(Advertisement ad) {
        System.out.println();
        System.out.println("=== " + ad.getText() + " ===");
    }

    public void displayNextAd() {
        displayAd(adBank.getNextAd());
    }

    public void displayRandomAd() {
        displayAd(adBank.getRandomAd());
    }
}
