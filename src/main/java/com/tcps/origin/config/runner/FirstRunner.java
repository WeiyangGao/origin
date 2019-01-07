package com.tcps.origin.config.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
public class FirstRunner{

//@Slf4j
//@Order(1)
//@Component
//public class FirstRunner implements ApplicationRunner {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//    @Autowired
//    private ClientRepository clientRepository;
//
//    /**
//     * 创建管理员
//     */
//    private static final String DEFAULT_USRENAME = "root";
//    private static final String DEFAULT_PASSWORD = BCrypt.hashpw("root", BCrypt.gensalt());
//    private static final String COUNT_ROOT_USER_SQL = "select count(1) from t_user where username = 'root' ";
//    private final String INIT_ROOT_USER_SQL = "insert into t_user " +
//            "(id,username,password,real_name,birthday,create_date)" +
//            "values(?,?,?,?,?,?)";
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        int result = jdbcTemplate.queryForObject(COUNT_ROOT_USER_SQL, Integer.class);
//        if (result == 0) {
//            jdbcTemplate.update(INIT_ROOT_USER_SQL,
//                    new Object[]{1, DEFAULT_USRENAME, DEFAULT_PASSWORD, DEFAULT_USRENAME, new Date(), new Date()});
//            log.info("t_user表没有root用户，系统将自动创建默认root用户");
//        }
//        // 插入虚拟客户端。
//        Client client = new Client();
//        client.setId(GetSnowFlake.getId());
//        client.setClicenState(ClientState.ENABLE);
//        client.setClientName("democlient");
//        client.setClientPassword(BCrypt.hashpw("123456", BCrypt.gensalt()));
//        client.setCreateDate(new Date());
//        clientRepository.simpleSave(client);
//    }


}




