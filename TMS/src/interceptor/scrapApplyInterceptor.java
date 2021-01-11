package interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.StrutsStatics;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import po.Scrap;
import service.PurchaseRecordService;
import service.ScrapRecordService;

import javax.servlet.ServletContext;
import java.util.Map;
import java.util.Set;

public class scrapApplyInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        System.out.println("scrapApplyInterceptor executed!");
        ActionContext ctx = invocation.getInvocationContext();

        //获得purRecordService的bean对象
        ActionContext actionContext = invocation.getInvocationContext();
        ServletContext context = (ServletContext) actionContext.get(StrutsStatics.SERVLET_CONTEXT);
        ApplicationContext context1 = WebApplicationContextUtils.getWebApplicationContext(context);
        ScrapRecordService scrapRecordService = (ScrapRecordService) context1.getBean("scrapService");

        //获取用户请求页面的参数
        Map formMap = invocation.getInvocationContext().getParameters();
        Set set = formMap.keySet();
        for (Object key:set) {
            System.out.print("key:"+key.toString()+"   ");
            System.out.println(formMap.get(key));
        }
        //获得code和seqID调用purchaseRecordService来查重
        String code = formMap.get("scrap.code_seqid.Code").toString();
        System.out.println("code"+code);
        String seqid = formMap.get("scrap.code_seqid.SeqID").toString();
        String pk = code + "&" + seqid;
        System.out.println("pk:" + pk);
        Scrap scrap = scrapRecordService.getScrapRecordByCodeandId(pk);
        //如果存在则返回ScrapRecordExist
        if (scrap != null) {
            return "ScrapRecordExist";
        }
        return invocation.invoke();
    }
}
