package ra.practice_rest_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriesResponseActive {
    private long id;
    private String name;
}
