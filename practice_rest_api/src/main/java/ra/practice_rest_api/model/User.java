package ra.practice_rest_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Entity
@Table(name = "Users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    private String id;
    @Column(name = "user_name")
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created;
    private String address;
    @Column(name = "user_status",columnDefinition = "bit default 0")
    private boolean status;
}
