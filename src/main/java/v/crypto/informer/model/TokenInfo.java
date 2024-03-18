package v.crypto.informer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tokens")
public class TokenInfo {
    @Id
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    @JsonProperty("price_usd")
    private Double priceUsd;

    public void setName(String name) {
        if (name.contains("com"))
            name = name.replaceAll(".com", "");
        if (name.endsWith(".ai"))
            name = name.replaceAll(".ai", "");
        this.name = name;
    }

    public void setPriceUsd(String priceUsd) {
        this.priceUsd = Double.valueOf(priceUsd);
    }

    @Override
    public String toString() {
        return String.format("<b>%s</b> (%s) — %s<b>$</b>\n", symbol, name, String.format("%.6f", priceUsd));
    }
}