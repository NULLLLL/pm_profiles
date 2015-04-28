package com.profiles.web.account;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import util.DateUtil;

import com.profiles.service.account.MYUsernamePasswordToken;

@Component
public class LoginRecordFilter extends FormAuthenticationFilter {

	private static final Logger LOG = LoggerFactory.getLogger(LoginRecordFilter.class);

	public LoginRecordFilter() {
	}

	@Override
	protected MYUsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		MYUsernamePasswordToken token = new MYUsernamePasswordToken(username, password != null ? password.toCharArray() : null, rememberMe, host);
		return token;
	}

	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		MYUsernamePasswordToken token = createToken(request, response);
		try {
			Subject subject = getSubject(request, response);
			subject.login(token);// 正常验证.进入shiro
			subject.isPermitted("login");
			/*String projectRealPath = request.getRealPath("/");*/
			LOG.info("用户:" + token.getUsername() + " 登录成功, 时间: " + DateUtil.getNowTimestamp());
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			LOG.info("用户:" + token.getUsername() + "  登录失败, 时间: " + DateUtil.getNowTimestamp() + "----" + e);
			return onLoginFailure(token, e, request, response);
		}
	}

	@Override
	protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
		request.setAttribute(getFailureKeyAttribute(), ae);
	}

}
