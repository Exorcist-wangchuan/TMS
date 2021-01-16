package redirect;

import org.apache.struts2.result.ServletRedirectResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Redircet extends ServletRedirectResult {
    private String msg;

    @Override
    protected void sendRedirect(HttpServletResponse response,
                                String finalLocation) throws IOException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("<script>alert('"+msg+"');location.href='primaryHome.jsp'</script>");
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
