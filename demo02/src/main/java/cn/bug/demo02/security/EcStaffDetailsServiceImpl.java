package cn.bug.demo02.security;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.BaseException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.ecommerce.constant.StaffStatusConstant;
import com.ruoyi.ecommerce.domain.EcStaff;
import com.ruoyi.ecommerce.service.IEcStaffService;
import com.ruoyi.framework.security.LoginUser;

/**
 * 用户验证处理
 */
@Service
public class EcStaffDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(EcStaffDetailsServiceImpl.class);

    @Autowired
    private IEcStaffService ecStaffService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) {

        QueryWrapper<EcStaff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", username);
        EcStaff user = ecStaffService.getOne(queryWrapper);

        if (StringUtils.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new BaseException(MessageUtils.message("user.not.exists"));
        } else if (Constants.DELETED.equals(user.getDeleted())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new BaseException(MessageUtils.message("user.password.delete"));
        }


        return createLoginUser(user);
    }

    /**
     * 查询用户权限
     *
     * @param user
     * @return
     */
    public UserDetails createLoginUser(EcStaff user) {
        return new LoginUser(user, permissionService.getMenuPermission(user));

    }
}