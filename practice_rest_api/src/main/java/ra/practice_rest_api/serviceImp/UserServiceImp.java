package ra.practice_rest_api.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.practice_rest_api.dto.response.UserResponse;
import ra.practice_rest_api.mapper.UserMapper;
import ra.practice_rest_api.model.User;
import ra.practice_rest_api.repository.UserRepository;
import ra.practice_rest_api.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String,Object> findByNameOrCreatedSort(int page, int size, String userName,
                                                       Date fromCreated, Date toCreated,
                                                       String direction, String orderBy) {
        // sử lý sắp xếp
        List<Sort.Order> listOrder = new ArrayList<>();
        Sort.Order orderName;
        Sort.Order orderCreated;
        if(orderBy.equals("name")) {
            if (direction.equals("ASC")) {
                orderName = new Sort.Order(Sort.Direction.ASC,"name");
            } else {
                orderName = new Sort.Order(Sort.Direction.DESC,"name");
            }
            listOrder.add((orderName));
        } else {
            if (direction.equals("ASC")) {
                orderCreated = new Sort.Order(Sort.Direction.ASC,"created");
            } else {
                orderCreated = new Sort.Order(Sort.Direction.DESC,"created");
            }
            listOrder.add(orderCreated);
        }

        Pageable pageable = PageRequest.of(page,size,Sort.by(listOrder));
        Page<User> pageUser = userRepository.findByNameContainsAndCreatedBetween(userName,fromCreated,toCreated,pageable);

        // lấy tổng số trang trong kết quả
        int totalPage = pageUser.getTotalPages();
        List<User> listUSer = pageUser.getContent();
        List<UserResponse> listUserResponse = listUSer.stream().map(user -> userMapper.mapperToResponse(user))
                .collect(Collectors.toList());
        Map<String,Object> data = new HashMap<>();
        data.put("totalPage",totalPage);
        data.put("users",listUserResponse);
        return data;
    }

    @Override
    public UserResponse updateStatus(String id) {
        Optional<User> otpUser = userRepository.findById(id);
        if (otpUser.isPresent()) {
            User userUpdateStatus = otpUser.get();
            // cập nhật trạng thái
            userUpdateStatus.setStatus(false);
            userRepository.save(userUpdateStatus);
            return userMapper.mapperToResponse(userUpdateStatus);
        }
        return null;
    }
}
