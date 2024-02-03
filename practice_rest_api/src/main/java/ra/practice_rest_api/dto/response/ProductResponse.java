package ra.practice_rest_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponse {
    private String id;
    private String name;
    private float price;
    private String title;
    private String descriptions;
    private long catalog_id;
    private String catalog_name;
    private boolean status;
}
