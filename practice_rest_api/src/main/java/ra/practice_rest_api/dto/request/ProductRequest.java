package ra.practice_rest_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequest {
    private String id;
    private String name;
    private float price;
    private String title;
    private String descriptions;
    private long catalog;
    private boolean status;
}
