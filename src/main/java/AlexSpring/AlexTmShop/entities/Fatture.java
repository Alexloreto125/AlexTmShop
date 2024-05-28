package AlexSpring.AlexTmShop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="fatture")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Fatture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long numero;

    private LocalDate data;

    private double importo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fatture_id") // Specifica la colonna nella tabella degli item che fa riferimento alla fattura
    private List<ItemRicambio> items;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Fatture(LocalDate data, double importo,  User user, List<ItemRicambio> items) {
        this.data = data;
        this.importo = importo;
        this.items= items;
        this.user = user;
    }

}

