package interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

    public class fixApplyInterceptor extends AbstractInterceptor {
        public String intercept(ActionInvocation invocation) throws Exception {
            System.out.println("fixApplyInterceptor executed!");
            ActionContext ctx = invocation.getInvocationContext();
            Map session = ctx.getSession();

            //获取用户请求页面的参数
            Map formMap = invocation.getInvocationContext().getParameters();
//        Set set = formMap.keySet();
//        for (Object key:set) {
//            System.out.print("key:"+key.toString()+"   ");
//            System.out.println(formMap.get(key));
//        }
            //获得输入来判空
            String code = formMap.get("fixRecord.applyUID").toString();
            String seqid = formMap.get("fixRecord.seqID").toString();
            String des = formMap.get("fixRecord.description").toString();
            String dealUID = formMap.get("fixRecord.dealUID").toString();
            String Img = formMap.get("fixRecord.Img").toString();


            //如果有空则返回fixRecordNotnull
            if (code.equals("")||seqid.equals("")||des.equals("")||dealUID.equals("")||Img.equals("")) {
                return "fixRecordNotnull";
            }
            return invocation.invoke();
        }
    }

