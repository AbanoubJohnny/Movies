package abanoub.johnny.development.moviesapp.utils.events;

import java.util.Observable;

public class NetworkSpeedObserver extends Observable {

    @Override
    public boolean hasChanged() {
        return true;
    }

    public void setAvailable(boolean isAvailable){
        if (isAvailable){
            this.setChanged();
            this.notifyObservers(true);
        }
    }
}
