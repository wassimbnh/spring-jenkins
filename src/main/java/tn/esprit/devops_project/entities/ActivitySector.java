package tn.esprit.devops_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivitySector  implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long idSecteurActivite;
        String codeSecteurActivite;
        String libelleSecteurActivite;


        public ActivitySector(Long idSecteurActivite, String codeSecteurActivite, String libelleSecteurActivite) {
                this.idSecteurActivite = idSecteurActivite;
                this.libelleSecteurActivite = libelleSecteurActivite;
                this.codeSecteurActivite = codeSecteurActivite;
        }

        @ManyToMany(mappedBy="activitySectors")
        @JsonIgnore
        private Set<Supplier> suppliers;

}
