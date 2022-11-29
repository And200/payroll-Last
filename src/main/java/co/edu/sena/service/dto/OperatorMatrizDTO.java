package co.edu.sena.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.edu.sena.domain.OperatorMatriz} entity.
 */
public class OperatorMatrizDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 100)
    private String address;

    @NotNull
    @Size(max = 100)
    private String city;

    @NotNull
    @Size(max = 100)
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperatorMatrizDTO)) {
            return false;
        }

        OperatorMatrizDTO operatorMatrizDTO = (OperatorMatrizDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, operatorMatrizDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperatorMatrizDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
