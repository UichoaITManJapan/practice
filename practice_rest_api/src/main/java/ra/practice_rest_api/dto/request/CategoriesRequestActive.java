package ra.practice_rest_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriesRequestActive {
    private long id;
    private String name;
}
