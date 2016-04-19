package com.ckgj;

import com.ckgj.config.WeChatConfig;
import com.ckgj.models.company.Company;
import com.ckgj.models.user.Role;
import com.ckgj.models.user.User;
import com.ckgj.services.company.CompanyRepository;
import com.ckgj.services.statement.StatementService;
import com.ckgj.services.user.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

//http://kielczewski.eu/2014/12/spring-boot-security-application/
// http://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index
@SpringBootApplication
@EnableConfigurationProperties(WeChatConfig.class)
public class Application {

    private static void init(ConfigurableApplicationContext cax) throws IOException {
        System.out.println("==start init==");
        CompanyRepository companyRepository = (CompanyRepository)cax.getBean("companyRepository");
        Company company = new Company();
        company.setDateCreated(new Date());
        company.setEmployeeCnt(1);
        company.setName("GAME-CXC");
        company.setInstitutionCode("112904182016");
        companyRepository.save(company);
        UserRepository userRepository = (UserRepository)cax.getBean("userRepository");
        User user = new User();
        user.setCompany(company);
        user.setDateCreated(new Date());
        user.setName("Calvin");
        user.setPhone("123");
        user.setPasswordHash(new BCryptPasswordEncoder().encode("111"));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        StatementService statementService = (StatementService)cax.getBean("statementService");
        int year = 2016;
        int month = 4;
        String filename = "C:\\Users\\zhaok_000\\Desktop\\a.xlsx";
        File file = new File(filename);
        FileInputStream fis = new FileInputStream(file);
        statementService.saveStatement(fis, company, year, month);

        System.out.println("==finish init==");
    }
	public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext cax = SpringApplication.run(Application.class, args);
        // init(cax);
	}
}
