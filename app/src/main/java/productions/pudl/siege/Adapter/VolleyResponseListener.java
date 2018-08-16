package productions.pudl.siege.Adapter;

public interface VolleyResponseListener {
    void onError(String message);

    void onResponse();
}