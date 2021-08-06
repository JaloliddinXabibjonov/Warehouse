package uz.pdp.warehouse.entity;

import lombok.*;
import uz.pdp.warehouse.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsEntity {
    @ManyToOne
    Category category;
    @OneToOne
    Attachment photo;
    String code;
    @ManyToOne
    Measurement measurement;

    public Product(Integer id, String name, boolean active, Category category, Attachment photo, Measurement measurement) {
        super(id, name, active);
        this.category = category;
        this.photo = photo;
        this.measurement = measurement;
    }

    public Product(Category category, Attachment photo, Measurement measurement) {
        this.category = category;
        this.photo = photo;
        this.measurement = measurement;
    }
}
