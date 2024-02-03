package ra.practice_rest_api.mapper;

import org.springframework.stereotype.Component;
import ra.practice_rest_api.dto.response.UserResponse;
import ra.practice_rest_api.model.User;
@Component
public class UserMapper implements GenericMapper<User,User, UserResponse>{
    @Override
    public User mapperToEntity(User user) {
        return null;
    }

    @Override
    public UserResponse mapperToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getCreated(),
                user.isStatus()
        );
    }
}
