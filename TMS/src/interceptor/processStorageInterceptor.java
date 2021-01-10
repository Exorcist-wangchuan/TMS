package interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.StrutsStatics;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.Map;

public class processStorageInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext actionContext = invocation.getInvocationContext();
        ServletContext servletContext = (ServletContext) actionContext.get(StrutsStatics.SERVLET_CONTEXT);
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        //获取用户页面请求的参数
        Map map = invocation.getInvocationContext().getParameters();
        Object checkList= map.get("checkList.checkList");
        if (checkList.toString().equals("Empty{name='checkList.checkList'}")){
            return "empty";
        }
        return invocation.invoke();
    }
}
