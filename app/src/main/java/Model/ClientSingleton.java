package Model;

import com.google.android.gms.common.api.GoogleApiClient;


public class ClientSingleton {
    private static ClientSingleton ourInstance = new ClientSingleton();
    private GoogleApiClient client;
    public static ClientSingleton getInstance() {
        return ourInstance;
    }


    private ClientSingleton() {
    }

    public GoogleApiClient getClient() {
        return client;
    }

    public void setClient(GoogleApiClient client) {
        this.client = client;
    }
}
