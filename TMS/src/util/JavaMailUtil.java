package util;

import com.sun.mail.util.MailSSLSocketFactory;
import po.PeriodCheck;

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
    public int sendEmail(String receive, String receive_name, PeriodCheck record){
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
            InternetAddress to = new InternetAddress(receive);
            msg.setRecipient(Message.RecipientType.TO, to);
            //设置邮件标题
            msg.setSubject("TMS:维护提醒");
            //设置主题内容
            String content = generateContext(receive_name,record);
            msg.setContent(content, "text/html;charset=UTF-8");
            //发送邮件
            Transport.send(msg);

        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    //生成邮件内容
    public String generateContext(String receive_name, PeriodCheck record){
        int line =1;
        String context="";
        context = context + receive_name+",你好<br>";
        context = context + "以下为需要维护的工夹具清单，请及时维护！<br>";
        context = context + line+".<br>";
        context = context + "工夹具类别号:"+record.getCode_seqid().getCode()+"<br>";
        context = context+"工夹具序列号:"+record.getCode_seqid().getSeqID()+"<br>";

        return context;
    }

    //发送报废预警邮件
    public void sendScrapEmail(String receive){
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
            InternetAddress to = new InternetAddress(receive);
            msg.setRecipient(Message.RecipientType.TO, to);
            //设置邮件标题
            msg.setSubject("TMS:维护提醒");
            //设置主题内容
            String content = generateScrapContext(receive);
            msg.setContent(content, "text/html;charset=UTF-8");
            //发送邮件
            Transport.send(msg);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String generateScrapContext(String re){
        String context = "您好,"+ re+ "<br>";
        context = context+ "您借出的工夹具已临近期限!<br>";
        context = context + "请注意使用!<br>";
        return context;
    }

}
