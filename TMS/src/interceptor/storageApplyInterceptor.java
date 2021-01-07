package interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.StrutsStatics;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.WebApplicationContextUtils;
import po.PurchaseRecord;
import service.PurchaseRecordService;

import javax.servlet.ServletContext;
import java.util.Map;
import java.util.Set;

public class storageApplyInterceptor extends AbstractInterceptor {


    public String intercept(ActionInvocation invocation) throws Exception {
        System.out.println("storageApplyInterceptor executed!");
        ActionContext ctx = invocation.getInvocationContext();
        Map session = ctx.getSession();

        //获得purRecordService的bean对象
        ActionContext actionContext = invocation.getInvocationContext();
        ServletContext context = (ServletContext) actionContext.get(StrutsStatics.SERVLET_CONTEXT);
        ApplicationContext context1 = WebApplicationContextUtils.getWebApplicationContext(context);
        PurchaseRecordService purchaseRecordService = (PurchaseRecordService) context1.getBean("purRecordService");

        //获取用户请求页面的参数
        Map formMap = invocation.getInvocationContext().getParameters();
//        Set set = formMap.keySet();
//        for (Object key:set) {
//            System.out.print("key:"+key.toString()+"   ");
//            System.out.println(formMap.get(key));
//        }
        //获得code和seqID调用purchaseRecordService来查重
        String code = formMap.get("purchaseRecord.code_seqid.code").toString();
        String seqid = formMap.get("purchaseRecord.code_seqid.seqID").toString();
        String pk = code + "&" + seqid;
        System.out.println("pk:" + pk);

        PurchaseRecord purchaseRecord = purchaseRecordService.getPurchaseRecordByCodeandId(pk);

        //如果存在则返回PurchaseRecordexist
        if (purchaseRecord != null) {
            return "PurchaseRecordexist";
        }
        return invocation.invoke();
    }
}

