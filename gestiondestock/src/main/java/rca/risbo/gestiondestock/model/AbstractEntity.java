package rca.risbo.gestiondestock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // Delegue a spring la creation des dates
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // updatable=false permet d'éviter de mettre à jour creationDate.
    @CreatedDate
    @Column(name = "creationDate", nullable = false, updatable = false)
    private Instant creationDate;

    @LastModifiedDate
    @Column(name = "lastModifieDate")
    private Instant lastUpdateDate;
}
