package v.crypto.informer.model;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.StringJoiner;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "notifications")
    private Boolean notifications;

    @Column(name = "list")
    @Type(ListArrayType.class)
    private List<String> list;

    public String displayList() {
        String result;
        if (list.isEmpty())
            result = "your token list is empty";
        else {
            StringJoiner joiner = new StringJoiner(", ");
            for (String tokenName : list)
                joiner.add(tokenName);
            result = joiner.toString();
        }
        return result;
    }
}