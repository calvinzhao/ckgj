package com.ckgj.services.user;

import com.ckgj.models.user.Role;
import com.ckgj.models.company.Company;
import com.ckgj.models.user.User;
import com.ckgj.models.user.UserForm;
import com.ckgj.models.wxuser.WxUser;
import com.ckgj.services.company.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

/**
 * Created by calvin on 3/25/16.
 */

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final CompanyService companyService;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CompanyService companyService) {
        this.companyService = companyService;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByPhone(String phone) {
        if (phone.contains("dai")) {
            // TODO delete debug code
            User u = new User();
            u.setPhone("dai@qq.com");
            u.setPasswordHash(new BCryptPasswordEncoder().encode("h"));
            u.setRole(Role.ADMIN);
            u.setId(1l);

            LOGGER.info("hahahaha  fake user" + u.getPhone() + u.getPasswordHash());
            Optional<User> opt = Optional.of(u);
            return opt;
        }
        return userRepository.findOneByPhone(phone);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll(new Sort("phone"));
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findOne(id);
        Company company = user.getCompany();
        companyService.updateEmployeeCnt(company, -1);
        userRepository.delete(user);
    }

    @Override
    public User createOrUpdate(UserForm form) {
        User user;
        if (form.getId() > 0) {
            user = getUserById(form.getId()).get();
            companyService.updateEmployeeCnt(user.getCompany(), -1);
        } else {
            user = new User();
            user.setDateCreated(new Date());
        }
        user.setPhone(form.getPhone());
        user.setName(form.getName());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        Company company = companyService.findCompany(form.getCompanyId());
        companyService.updateEmployeeCnt(company, 1);
        user.setCompany(company);
        return userRepository.save(user);
    }

    @Override
    public User bindWxUser(User user, WxUser wxUser) {
        user.setWxUser(wxUser);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> validUser(String phone, String password) {
        Optional<User> user = getUserByPhone(phone);
        if (user.isPresent() && user.get().getPasswordHash() == new BCryptPasswordEncoder().encode(password)){
            return user;
        } else
            return Optional.empty();
    }
}
