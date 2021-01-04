package util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMailUtil {
    private String username;
    private String password;
    private String smtpServer;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    //发送邮件
    public int sendEmail(){
        int res=0;
        try {
            Properties p = new Properties();
            p.setProperty("mail.smtp.host", smtpServer);
            //身份验证
            p.setProperty("mail.smtp.auth", "true");
            //用户名
            p.setProperty("mail.username", username);
            //授权码
            p.setProperty("mail.password", password);
            //协议
            p.setProperty("mail.transport.protocol", "smtp");
            //SSL加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            p.put("mail.smtp.ssl.enable", "true");
            p.put("mail.smtp.ssl.socketFactory", sf);
            //创建Session
            Session session = Session.getDefaultInstance(p, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            //打开调试模式
            session.setDebug(true);
            //声明邮件对象
            MimeMessage msg = new MimeMessage(session);
            //设置发件人
            InternetAddress from = new InternetAddress("TMS SERVICE"+"<"+p.getProperty("mail.username")+">");
            msg.setFrom(from);
            //设置收件人
            InternetAddress to = new InternetAddress("xuj1677827654@outlook.com");
            msg.setRecipient(Message.RecipientType.TO, to);
            //设置邮件标题
            msg.setSubject("TMS:测试");
            //设置主题内容
            msg.setContent("测试邮件", "text/html;charset=UTF-8");
            //发送邮件
            Transport.send(msg);

        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
