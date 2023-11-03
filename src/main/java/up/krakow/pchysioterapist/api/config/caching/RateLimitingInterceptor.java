package up.krakow.pchysioterapist.api.config.caching;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

@Component
public class RateLimitingInterceptor implements HandlerInterceptor {
    @Autowired
    private CacheManager cacheManager;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (request.getMethod().equalsIgnoreCase(HttpMethod.POST.name())) {
            String ip = request.getRemoteAddr();
            Cache cache = cacheManager.getCache("rateLimitCache");

            if (cache != null && cache.get(ip) != null) {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.getWriter().write("You can only submit one opinion per day.");
                return false;
            } else {
                if (cache != null) {
                    cache.put(ip, true);
                }
            }
        }
        return true;
    }
}
