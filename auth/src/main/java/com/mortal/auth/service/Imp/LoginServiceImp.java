package com.mortal.auth.service.Imp;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.auth.mapper.UserMapper;
import com.mortal.auth.pojo.LoginUser;
import com.mortal.auth.pojo.UserPojo;
import com.mortal.auth.service.LoginService;
import com.mortal.auth.vo.UserLoginVo;
import com.mortal.common.utils.JwtUtil;
import com.mortal.common.utils.R;
import com.mortal.common.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
@Service
public class LoginServiceImp extends ServiceImpl<UserMapper, UserPojo> implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public String loginIng(UserLoginVo userLoginVo) {
        List<UserPojo> result = userMapper.selectList(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getPhoneNumber,userLoginVo.getPhoneNumber()));
        if (result.size()==0){
            return "";
        }else{
            for (UserPojo userPojo : result) {
                String password = userPojo.getUserPassword();
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                boolean  judge = encoder.matches(userLoginVo.getUserPassword(),password);
                if (judge){
                    return "????????????";
                }
            }
        }
        return "????????????";
    }

    @Override
    public R login(UserLoginVo userLoginVo) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginVo.getPhoneNumber(),userLoginVo.getUserPassword());
        Authentication authentication = null;
        String msg = null;
        try {
            authentication =  authenticationManager.authenticate(authenticationToken);
        }catch (Exception e){
            msg  = e.toString();
            System.out.println(msg);
        }
        if (ObjectUtil.isNull(authentication)){
            assert msg != null;
            String[] list = msg.split(":");
            return R.failed(401,list[1].trim());
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String id = loginUser.getUserPojo().getId().toString();
        Long ttlMillis = 24 * 60 * 60 * 1000L;
        String Jwt = JwtUtil.createJWT(id, ttlMillis);
        Map<String,String> map = new HashMap<>();
        map.put("token",Jwt);
        redisCache.setCacheObject("login:"+id,loginUser);
        return R.ok(map);
    }

    @Override
    public R loginOut() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        Integer id = loginUser.getUserPojo().getId();
        redisCache.deleteObject("login:"+id);
        return R.ok("??????????????????");
    }

    @Override
    public R logOff(UserLoginVo userLoginVo) {
        userMapper.update(null,new LambdaUpdateWrapper<UserPojo>().eq(UserPojo::getPhoneNumber,userLoginVo.getPhoneNumber()).set(UserPojo::getIsDelete,1));
        //?????????????????????token
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        //??????loginUser
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        //??????redisKey
        Integer id = loginUser.getUserPojo().getId();
        //??????redis
        redisCache.deleteObject("login:"+id);
        return R.ok("????????????");
    }


}
