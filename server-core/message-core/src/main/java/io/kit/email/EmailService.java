package io.kit.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String sendEmail;
    @Value("${mail.cacheMin:10}")
    private int cacheMin;

    @Autowired
    private JavaMailSender mailSender;

    public void sendHtmlEmail(SendParam param) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(param.getEmail());
        helper.setSubject(param.getSubject());
        helper.setText(getHtml(param.getEmail(), param.getCode()), true); // 第二个参数 `true` 表示发送 HTML 格式
        helper.setFrom(sendEmail);
        mailSender.send(message);
    }

    public String getHtml(String email, String code) {
        return replace(replace(replace(HTML, "email", email), "code", code), "cacheMin", String.valueOf(cacheMin));
    }

    public String replace(String content, String key, String value) {
        key = String.format("${%s}", key);
        while (content.contains(key)) {
            content = content.replace(key, value);
        }
        return content;
    }

    public static class SendParam {
        String email;
        String code;
        String subject;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }
    }


    public static final String HTML = "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"padding-bottom: 20px; max-width: 516px; min-width: 220px;\">\n" +
            "    <tbody>\n" +
            "    <tr>\n" +
            "        <td width=\"8\" style=\"width: 8px;\"></td>\n" +
            "        <td>\n" +
            "            <div style=\"border-style: solid; border-width: thin; border-color:#dadce0; border-radius: 8px; padding: 40px 20px;\"\n" +
            "                 align=\"center\" class=\"mdv2rw\"><img\n" +
            "                    src=\"http://1.12.74.15/favicon.ico\" width=\"74\"\n" +
            "                    height=\"24\" aria-hidden=\"true\" style=\"margin-bottom: 16px;\">\n" +
            "                <div style=\"\">\n" +
            "                    <div style=\"font-size: 24px;\">请使用此邮箱验证码应用到您的登陆操作</div>\n" +
            "                </div>\n" +
            "                <div style=\"font-family: Roboto-Regular,Helvetica,Arial,sans-serif; font-size: 14px; color: rgba(0,0,0,0.87); line-height: 20px;padding-top: 20px; text-align: left;\">\n" +
            "                    我们 收到了将 <a style=\"font-weight: bold;\">${email}</a> 用作邮箱账号登陆的请求。<br><br>请使用此验证码完成用户登陆：<br>\n" +
            "                    <div style=\"text-align: center; font-size: 36px; margin-top: 20px; line-height: 44px;\">${code}</div>\n" +
            "                    <br>此验证码将在 ${cacheMin} 分钟后失效。<br><br>如果您不认识 <a style=\"font-weight: bold;\">${email}</a>，可以放心地忽略这封电子邮件。\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div style=\"text-align: left;\">\n" +
            "                <div style=\"font-family: Roboto-Regular,Helvetica,Arial,sans-serif;color: rgba(0,0,0,0.54); font-size: 11px; line-height: 18px; padding-top: 12px; text-align: center;\">\n" +
            "                    <div>我们向您发送这封电子邮件，目的是让您了解关于您的 用户 账号和服务的重大变化，即您可以开始使用您的邮箱进行账号登陆。</div>\n" +
            "                    <div style=\"direction: ltr;\">© 2024 知之 LLC, <a class=\"afal\"\n" +
            "                                                                       style=\"font-family: Roboto-Regular,Helvetica,Arial,sans-serif;color: rgba(0,0,0,0.54); font-size: 11px; line-height: 18px; padding-top: 12px; text-align: center;\">2024\n" +
            "                        CN</a></div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </td>\n" +
            "        <td width=\"8\" style=\"width: 8px;\"></td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>";
}
