package ra.practice_rest_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriesResponse {
    private long id;
    private String name;
    private int priority;
    private String descriptions;
    private boolean status;
}
