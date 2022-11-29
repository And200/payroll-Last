package co.edu.sena.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.edu.sena.domain.SocialPayment} entity.
 */
public class SocialPaymentDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String socialPaymentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocialPaymentName() {
        return socialPaymentName;
    }

    public void setSocialPaymentName(String socialPaymentName) {
        this.socialPaymentName = socialPaymentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SocialPaymentDTO)) {
            return false;
        }

        SocialPaymentDTO socialPaymentDTO = (SocialPaymentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, socialPaymentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SocialPaymentDTO{" +
            "id=" + getId() +
            ", socialPaymentName='" + getSocialPaymentName() + "'" +
            "}";
    }
}
