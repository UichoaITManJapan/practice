package ra.practice_rest_api.service;

import ra.practice_rest_api.dto.response.UserResponse;

import java.util.Date;
import java.util.Map;

public interface UserService {
    Map<String,Object> findByNameOrCreatedSort(int page, int size,
                                               String userName, Date fromCreated, Date toCreated,
                                               String direction, String orderBy);
    UserResponse updateStatus(String id);
}
