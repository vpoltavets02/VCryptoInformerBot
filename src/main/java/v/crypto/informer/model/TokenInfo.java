package v.crypto.informer.model;

import lombok.Data;

@Data
public class TokenInfo {
    private String symbol;
    private String name;
    private String priceUsd;

    public void setName(String name) {
        if (name.contains("com"))
            name = name.replaceAll(".com", "");
        if (name.endsWith(".ai"))
            name = name.replaceAll(".ai", "");
        this.name = name;
    }

    public void setPriceUsd(String priceUsd) {
        this.priceUsd = String.format("%.6f", Double.valueOf(priceUsd));
    }

    @Override
    public String toString() {
        return String.format("<b>%s</b> (%s) — %s<b>$</b>\n", symbol, name, priceUsd);
    }
}