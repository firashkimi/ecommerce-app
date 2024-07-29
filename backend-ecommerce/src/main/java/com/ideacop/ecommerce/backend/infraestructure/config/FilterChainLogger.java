package com.ideacop.ecommerce.backend.infraestructure.config;


import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class FilterChainLogger implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("Request received at filter chain: {}", filterChain);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
