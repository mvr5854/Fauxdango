package Model;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AdvertisementBank {
    private List<Advertisement> ads = Datastore.getAllAdvertisements();
    int currentAdIndex = 0;

    public void addAd(Advertisement ad) {
        ads.add(ad);
    }

    public void clearAds() {
        ads.clear();
    }

    public Advertisement getNextAd() {
        currentAdIndex++;
        if (currentAdIndex >= ads.size()) {
            currentAdIndex = 0;
        }

        return ads.get(currentAdIndex);
    }

    public Advertisement getRandomAd() {
        int randomIndex = ThreadLocalRandom.current().nextInt(ads.size());

        return ads.get(randomIndex);
    }
}
