package v.crypto.informer.callback.enums;

public enum CallbackName {
    ADD("add"),
    REMOVE("remove");
    private String callbackName;

    CallbackName(String callbackName) {
        this.callbackName = callbackName;
    }

    public String getCallbackName() {
        return callbackName;
    }
}