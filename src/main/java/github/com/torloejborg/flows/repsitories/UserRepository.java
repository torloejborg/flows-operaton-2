package github.com.torloejborg.flows.repsitories;

import github.com.torloejborg.flows.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository {

    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        users.add(new User(UserName.USER_ADMIN.name, List.of("admin","signer","customer","supplier")));
        users.add(new User(UserName.USER_SUPPLIER.name, List.of("supplier")));
        users.add(new User(UserName.USER_CUSTOMER.name, List.of("customer")));

        return users;
    }


}
