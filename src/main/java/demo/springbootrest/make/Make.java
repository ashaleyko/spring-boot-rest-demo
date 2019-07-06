package demo.springbootrest.make;

import demo.springbootrest.core.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class Make extends BaseEntity {

    @NotNull
    private String name;

    @NotNull
    private String originCountry;

    @Temporal(TemporalType.DATE)
    private Date foundedDate;

    protected Make() {
    }

    public Make(String name, String originCountry, Date foundedDate) {
        this.name = name;
        this.originCountry = originCountry;
        this.foundedDate = foundedDate;
    }
}
