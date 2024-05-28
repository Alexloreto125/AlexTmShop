package AlexSpring.AlexTmShop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemRicambio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String descrizione;
    private BigDecimal prezzo;
    private String image;
    private String codice;



    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fatture_id")
    private Fatture fatture;

    public ItemRicambio(String name, String descrizione, BigDecimal prezzo, String image, Category category, String codice) {
        this.name = name;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.image = image;
//        this.categoryId= categoryId;
        this.category = category;
        this.codice= codice;
    }

}
