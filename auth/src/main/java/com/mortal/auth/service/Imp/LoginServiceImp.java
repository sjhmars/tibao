package com.mortal.auth.service.Imp;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.auth.feign.SaveHeadImg;
import com.mortal.auth.mapper.UserMapper;
import com.mortal.auth.pojo.LoginUser;
import com.mortal.auth.pojo.UserPojo;
import com.mortal.auth.service.LoginService;
import com.mortal.auth.vo.UserLoginVo;
import com.mortal.auth.vo.UserRegistVo;
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
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private SaveHeadImg saveHeadImg;

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
                    return "登录成功";
                }
            }
        }
        return "密码错误";
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
        Long ttlMillis = 24 * 60 * 60 * 1000L;//设置token有效期一天
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
        return R.ok("退出登录成功");
    }

    @Override
    public R logOff(UserLoginVo userLoginVo) {
        userMapper.update(null,new LambdaUpdateWrapper<UserPojo>().eq(UserPojo::getPhoneNumber,userLoginVo.getPhoneNumber()).set(UserPojo::getIsDelete,1));
        //取出请求头中的token
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        //取出loginUser
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        //获取redisKey
        Integer id = loginUser.getUserPojo().getId();
        //清楚redis
        redisCache.deleteObject("login:"+id);
        return R.ok("注销成功");
    }

    @Override
    public R changeUser(UserRegistVo userRegistVo) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        UserPojo userPojo = loginUser.getUserPojo();
        if (StrUtil.isNotBlank(userRegistVo.getUserName()))
            userPojo.setUserName(userRegistVo.getUserName());
        if (StrUtil.isNotBlank(userRegistVo.getPhoneNumber()))
            userPojo.setPhoneNumber(userRegistVo.getPhoneNumber());
        if (StrUtil.isNotBlank(userRegistVo.getJobNumber()))
            userPojo.setJobNumber(userRegistVo.getJobNumber());
        if (userRegistVo.getSex()!=null)
            userPojo.setSex(userRegistVo.getSex());
        if (StrUtil.isNotBlank(userRegistVo.getUserPassword())){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String passwordEncoder = bCryptPasswordEncoder.encode(userRegistVo.getUserPassword());
            userPojo.setUserPassword(passwordEncoder);
        }
        if (userRegistVo.getUserType()!=null)
            userPojo.setUserType(userRegistVo.getUserType());
        userMapper.update(userPojo,new LambdaUpdateWrapper<UserPojo>().eq(UserPojo::getId,userPojo.getId()));
        return R.ok();
    }

    @Override
    public R getUserAdmin() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        return R.ok(userMapper.searchAllById(userId));
    }

    @Override
    public R findPassword(UserRegistVo userRegistVo) {
        UserPojo userPojo = userMapper.selectOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getPhoneNumber,userRegistVo.getPhoneNumber()));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passwordEncoder = bCryptPasswordEncoder.encode(userRegistVo.getUserPassword());
        userPojo.setUserPassword(passwordEncoder);
        return R.ok(userMapper.update(userPojo,new LambdaUpdateWrapper<UserPojo>().eq(UserPojo::getPhoneNumber,userRegistVo.getPhoneNumber())));
    }

    @Override
    public R uploadImage(MultipartFile file) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        String url = "http://my.xiangmu.com/tibao/";
        String imageUrl  =  saveHeadImg.uploadImage(file);
        this.update(new LambdaUpdateWrapper<UserPojo>().eq(UserPojo::getId,userId).set(UserPojo::getUserImg,imageUrl));
        return R.ok(url+imageUrl);
    }

}
