package com.demo.jwt.filter;

import com.demo.jwt.interceptor.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author:yangyang
 * @date:2019/11/11
 * @descriptiom:
 */
@Component
@WebFilter(urlPatterns = "/*",filterName = "channelFilter")
public class ChannelFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(ChannelFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("================进入过滤器======================");
        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        ServletRequest requestWrapper = new RequestWrapper(httpServletRequest);

        filterChain.doFilter(requestWrapper, servletResponse);
    }
    @Override
    public void destroy() {

    }
}

