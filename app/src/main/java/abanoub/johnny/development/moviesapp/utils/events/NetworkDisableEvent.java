package abanoub.johnny.development.moviesapp.utils.events;

/**
 * Created by Abanoub Maher on 1/4/2017.
 */

public class NetworkDisableEvent extends BaseEvent<String> {
    public NetworkDisableEvent(String key, String object) {
        super(key, object);
        setKey(EventsContract.NETWORK_DISABLED);
    }
}
