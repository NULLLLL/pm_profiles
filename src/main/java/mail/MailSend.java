package mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailSend {

	public static void main(String[] args) {
		String server = "smtp.exmail.qq.com";
		int port = 465;
		boolean isSSl = true;
		String from_add = "wangchengyang@chinark.com";
		String userName = "wangchengyang@chinark.com";
		String userPassword = "";
		MailSend mailSend = new MailSend(server, port, isSSl, from_add, userName, userPassword);
		String subject = "majianqiao";
		String message = "sb";
		String chaosong = "";
		mailSend.sendMail("majianqiao@chinark.com", chaosong, subject, message);
	}

	public MailSend(String server, int port, boolean isSSl, String from_add, String userName, String userPassword) {
		this.server = server;
		this.auth = new MailAuthenticator(userName, userPassword);
		this.from_add = from_add;
		this.props = new Properties();
		this.props.put("mail.smtp.host", server);
		this.props.put("mail.smtp.socketFactory.port", port);
		this.props.put("mail.smtp.auth", "true");
		if (isSSl)
			this.props.put("mail.smtp.starttls.enable", isSSl);
	}

	private static final Logger log = LoggerFactory.getLogger(MailSend.class);

	private Properties props;//保存参数

	private String server;//发送邮件服务器

	private String from_add;//发送邮件地址

	private Authenticator auth = null;//认证信息

	public boolean sendMail(String toAddr, String chaosong, String subject, String message) {
		try {
			Session session = Session.getDefaultInstance(props, auth);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from_add));
			InternetAddress[] tos = InternetAddress.parse(toAddr);
			msg.setRecipients(Message.RecipientType.TO, tos);
			if (chaosong != null && !chaosong.trim().equals("")) {
				InternetAddress[] cc = InternetAddress.parse(chaosong);
				msg.setRecipients(Message.RecipientType.CC, cc);
			}
			msg.setSubject(subject);
			msg.setText(message);
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getFrom_add() {
		return from_add;
	}

	public void setFrom_add(String from_add) {
		this.from_add = from_add;
	}

	public Authenticator getAuth() {
		return auth;
	}

	public void setAuth(Authenticator auth) {
		this.auth = auth;
	}

}
