package ra.practice_rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.practice_rest_api.dto.response.UserResponse;
import ra.practice_rest_api.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("ecommerce/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/findUser")
    public ResponseEntity<Map<String,Object>> findByNameOrCreatedOrder(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "") String userName,
            @RequestParam(defaultValue = "2024-01-20") String fromCreated,
            @RequestParam(defaultValue = "2024-01-31") String toCreated,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "name") String orderBy
            ) {
        Date fromDate =parseDateString(fromCreated);
        Date toDate = parseDateString(toCreated);
        return new ResponseEntity<>(userService.findByNameOrCreatedSort(page, size, userName, fromDate, toDate, direction, orderBy), HttpStatus.OK
        );
    }
    private Date parseDateString(String dateString) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.parse(dateString);
            } catch (Exception ex) {
               return null;
            }
    }
    @PatchMapping("/updatedStatus/{id}")
    public ResponseEntity<UserResponse> updateStatus(@PathVariable String id) {
        UserResponse userResponse = userService.updateStatus(id);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }
}
