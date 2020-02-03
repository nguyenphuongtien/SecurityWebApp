package org.o7planning.security.web.app.utils;
import java.util.List;
import java.util.Set;
 
import javax.servlet.http.HttpServletRequest;
 
import org.o7planning.security.web.app.config.SecurityConfig;
 
public class SecurityUtils {
 
    public static boolean isSecurityPage(HttpServletRequest request) {
    	String url = UrlPatternUtils.getUrlPattern(request);
    	
    	 Set<String> roles = SecurityConfig.getAllAppRoles();
    	 
         for (String role : roles) {
             List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
             if (urlPatterns != null && urlPatterns.contains(url)) {
                 return true;
             }
         }
         return false;
    }
    
    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);
 
        Set<String> allRoles = SecurityConfig.getAllAppRoles();
 
        for (String role : allRoles) {
            if (!request.isUserInRole(role)) {
                continue;
            }
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
}
