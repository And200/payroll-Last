package co.edu.sena.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.edu.sena.domain.SocialSecurity} entity.
 */
public class SocialSecurityDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String eps;

    @NotNull
    @Size(max = 100)
    private String afp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getAfp() {
        return afp;
    }

    public void setAfp(String afp) {
        this.afp = afp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SocialSecurityDTO)) {
            return false;
        }

        SocialSecurityDTO socialSecurityDTO = (SocialSecurityDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, socialSecurityDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SocialSecurityDTO{" +
            "id=" + getId() +
            ", eps='" + getEps() + "'" +
            ", afp='" + getAfp() + "'" +
            "}";
    }
}
