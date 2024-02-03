package ra.practice_rest_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriesRequest {
    private long id;
    private String name;
    private int priority;
    private String descriptions;
    private boolean status;
}
