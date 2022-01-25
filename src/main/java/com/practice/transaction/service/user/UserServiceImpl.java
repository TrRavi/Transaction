package com.practice.transaction.service.user;

import com.practice.transaction.entity.User;
import com.practice.transaction.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;


    private Random random = new Random();


    @Override
    public Map<String, String> getUserUniqueNumber(String userId) {
        synchronized (this){
            Map<String,String> result = new LinkedHashMap<>();
            User user = userRepository.getById(1);
            LocalDateTime now = LocalDateTime.now();
            long minutes = Duration.between(user.getUpdatedtimestamp(),now).toMinutes();
            if(minutes < 1){
                result.put("userId", userId);
                result.put("uniqueNumber", String.valueOf(user.getUniqueNumber()));
                result.put("timestamp", String.valueOf(user.getUpdatedtimestamp()));
                log.info("returning old unique number = {}",user.getUniqueNumber());
                return result;
            }
            int uniqueNumber = random.nextInt();
            user.setUniqueNumber(uniqueNumber);
            user.setUpdatedtimestamp(LocalDateTime.now());
            log.info("returning new unique number = {}",uniqueNumber);
            userRepository.saveAndFlush(user);

            result.put("userId", userId);
            result.put("uniqueNumber", String.valueOf(user.getUniqueNumber()));
            result.put("timestamp", String.valueOf(user.getUpdatedtimestamp()));
            return result;
        }
    }

}
