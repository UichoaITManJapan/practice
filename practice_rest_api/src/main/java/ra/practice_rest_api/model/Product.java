package ra.practice_rest_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "Product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @Column(name = "product_id")
    private String id;
    @Column(name = "product_name",nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private float price;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String descriptions;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    private Date updated;
    @Column(name = "product_status")
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "catalog_id",referencedColumnName = "catalog_id")
    private Categories catalog;
}
