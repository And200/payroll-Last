package co.edu.sena.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A SocialPayment.
 */
@Entity
@Table(name = "social_payment")
public class SocialPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "social_payment_name", length = 50, nullable = false)
    private String socialPaymentName;

    @OneToMany(mappedBy = "socialPayment")
    @JsonIgnoreProperties(value = { "employee", "socialPayment" }, allowSetters = true)
    private Set<DetailEmployeeSocialPayment> detailEmployeeSocialPayments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SocialPayment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocialPaymentName() {
        return this.socialPaymentName;
    }

    public SocialPayment socialPaymentName(String socialPaymentName) {
        this.setSocialPaymentName(socialPaymentName);
        return this;
    }

    public void setSocialPaymentName(String socialPaymentName) {
        this.socialPaymentName = socialPaymentName;
    }

    public Set<DetailEmployeeSocialPayment> getDetailEmployeeSocialPayments() {
        return this.detailEmployeeSocialPayments;
    }

    public void setDetailEmployeeSocialPayments(Set<DetailEmployeeSocialPayment> detailEmployeeSocialPayments) {
        if (this.detailEmployeeSocialPayments != null) {
            this.detailEmployeeSocialPayments.forEach(i -> i.setSocialPayment(null));
        }
        if (detailEmployeeSocialPayments != null) {
            detailEmployeeSocialPayments.forEach(i -> i.setSocialPayment(this));
        }
        this.detailEmployeeSocialPayments = detailEmployeeSocialPayments;
    }

    public SocialPayment detailEmployeeSocialPayments(Set<DetailEmployeeSocialPayment> detailEmployeeSocialPayments) {
        this.setDetailEmployeeSocialPayments(detailEmployeeSocialPayments);
        return this;
    }

    public SocialPayment addDetailEmployeeSocialPayment(DetailEmployeeSocialPayment detailEmployeeSocialPayment) {
        this.detailEmployeeSocialPayments.add(detailEmployeeSocialPayment);
        detailEmployeeSocialPayment.setSocialPayment(this);
        return this;
    }

    public SocialPayment removeDetailEmployeeSocialPayment(DetailEmployeeSocialPayment detailEmployeeSocialPayment) {
        this.detailEmployeeSocialPayments.remove(detailEmployeeSocialPayment);
        detailEmployeeSocialPayment.setSocialPayment(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SocialPayment)) {
            return false;
        }
        return id != null && id.equals(((SocialPayment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SocialPayment{" +
            "id=" + getId() +
            ", socialPaymentName='" + getSocialPaymentName() + "'" +
            "}";
    }
}
