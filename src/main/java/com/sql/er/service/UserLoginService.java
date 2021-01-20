package com.sql.er.service;

import com.sql.er.dao.mapper.UserMapper;
import com.sql.er.dao.model.User;
import com.sql.er.dto.UserTokenDto;
import com.sql.er.dto.VerificationCodeDto;
import com.sql.er.utils.BCrypt;
import com.sql.er.utils.RedisUtil;
import com.sql.er.utils.Result;
import com.sql.er.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class UserLoginService {
    private static Logger logger = LoggerFactory.getLogger(UserLoginService.class);

    // token过期时间 7 天
    private static final int TOKEN_EXP = 7 * 24 * 3600;

    @Resource
    UserMapper userMapper;

    @Resource
    RedisUtil redisUtil;

    public Result<String> login(String phone, String verificationCode) {
        // TODO verificationCode
        User user = userMapper.selectByPhone(phone);
        if (null == user) {
            return Result.fail(Result.UN_REGISTER, "用户未注册");
        }
        return Result.createSuccess(this.buildToken(user));
    }

    public Result<String> passwordLogin(String phone, String password) {
        User user = userMapper.selectByPhone(phone);
        if (null == user) {
            return Result.fail(Result.UN_REGISTER, "用户未注册");
        }
        if (!BCrypt.checkpw(password, user.getPassword())) {
            return Result.fail(Result.PASSWORD_ERROR, "密码错误");
        }
        return Result.createSuccess(this.buildToken(user));
    }

    /**
     * 构造token
     *
     * @param user
     * @return
     */
    private String buildToken(User user) {
        StringBuffer tokenAppend = new StringBuffer("" + user.getId());
        tokenAppend.append(System.currentTimeMillis()).append(new Double(Math.random() * 900000000).intValue() + 10000000);
        String token = StringUtil.MD5(tokenAppend.toString());
        String key = RedisUtil.KEY_TOKEN + token;
        redisUtil.set(key, user.getId() + "|" + "abc", TOKEN_EXP);
        return token;
    }

    /**
     * token 移除
     *
     * @param token
     * @return
     */
    public Result removeToken(String token) {
        if (StringUtil.isNull(token)) {
            return Result.fail("please enter token");
        }
        redisUtil.del(RedisUtil.KEY_TOKEN + token);
        return Result.createSuccess(token);
    }

    /**
     * 解析token对应的用户用户信息
     *
     * @param token
     * @return
     */
    public Result<UserTokenDto> decodeToken(String token) {
        if (StringUtil.isNull(token)) {
            return Result.fail("please enter token");
        }
        String key = RedisUtil.KEY_TOKEN + token;
        String value = redisUtil.getString(key);
        if (StringUtil.isNotNull(value)) {
            String[] tokenDecode = value.split(RedisUtil.KEY_TOKEN_SPLIT);
            UserTokenDto tokenDto = new UserTokenDto();
            switch (tokenDecode.length) {
                case 2:
                    tokenDto.setUserId(StringUtil.isNull(tokenDecode[0]) ? null : new Long(tokenDecode[0]));
                    tokenDto.setTodo(tokenDecode[tokenDecode.length - 1]);
                    tokenDto.setToken(token);
                    break;
                default:
                    tokenDto = null;
                    break;
            }
            return Result.createSuccess(tokenDto);
        }
        return Result.fail(Result.TOKEN_EXPIRE, "token expire");
    }

    /**
     * 发送验证码
     * @param codeDto
     * @return
     */
    public Result sendCode(VerificationCodeDto codeDto) {
        Result result = this.baseCheck(codeDto);
        if (!Result.isSuccess(result)){
            return result;
        }
        int times = 0;
        String ipKey = RedisUtil.KEY_PRE_IP + codeDto.getIp();
        String phoneKey = RedisUtil.KEY_PRE_PHONE + codeDto.getPhone();
        String codeKey = RedisUtil.KEY_PRE_CODE + codeDto.getPhone();
        long defaultTimeOutMin = 1l;
        if (null != redisUtil.get(phoneKey)) {
            result.fail("请在" + defaultTimeOutMin + "分钟后重新发送");
            return result;
        }
        redisUtil.set(phoneKey, "" + System.currentTimeMillis(), defaultTimeOutMin * 60);

        String cacheTimes = redisUtil.getString(ipKey);
        if (StringUtil.isNotNull(cacheTimes)) {
            times = new Integer(cacheTimes);
        }
        if (times > 3) {
            result.fail("发送失败，您的设备发送短信过多");
            return result;
        }

        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        try {
//            this.sendVerificationCode(code.toString(), templateCode, codeDto.getPhone());
            // TODO send code
            redisUtil.set(codeKey, code.toString(), defaultTimeOutMin * 60);
            redisUtil.set(ipKey, ++times + "", 600L);
        } catch (Exception e) {
            logger.error("send msg error", e);
            result.fail("发送失败");
        }

        return result;
    }

    /**
     * 验证码校验
     *
     * @param codeDto
     * @return
     */
    public Result checkCode(VerificationCodeDto codeDto, boolean isNeedDelete) {
        Result result = new Result();
        String codeKey = RedisUtil.KEY_PRE_CODE + codeDto.getPhone();
        String code = redisUtil.getString(codeKey);
        if (StringUtil.isNotNull(code)) {
            if (code.equals(codeDto.getCode())) {
                if (isNeedDelete) {
                    redisUtil.del(codeKey);
                }
            } else {
                result.fail("校验失败");
            }
        } else {
            result.fail("验证码失效，请重试");
        }
        return result;
    }


    /**
     * 入参校验
     *
     * @param dto
     * @return
     */
    private Result baseCheck(VerificationCodeDto dto) {
        Result response = new Result();
        if (StringUtil.isNull(dto.getPhone())) {
            response.fail("手机号不能为空");
            return response;
        }
        if (!StringUtil.isPhone(dto.getPhone())) {
            response.fail("手机号格式错误");
            return response;
        }
        if (StringUtil.isNull(dto.getIp())) {
            response.fail("ip不能为空");
            return response;
        }
        if (!StringUtil.ipCheck(dto.getIp())) {
            response.fail("ip格式错误");
            return response;
        }
        return response;
    }
}
