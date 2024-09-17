package com.kit.framework.web.service.login.auth.provider;

import com.kit.framework.web.service.UserDetailsServiceImpl;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class CoreAuthenticationManagerDelegator implements AuthenticationManager {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private AuthenticationManagerBuilder delegateBuilder;

    private ProviderManager delegate;

    private final Object delegateMonitor = new Object();

    private Set<String> beanNames;

    CoreAuthenticationManagerDelegator(AuthenticationManagerBuilder delegateBuilder, ApplicationContext context) {
        Assert.notNull(delegateBuilder, "delegateBuilder cannot be null");
        Field parentAuthMgrField = ReflectionUtils.findField(AuthenticationManagerBuilder.class,
                "parentAuthenticationManager");
        ReflectionUtils.makeAccessible(parentAuthMgrField);
        this.beanNames = getAuthenticationManagerBeanNames(context);
        validateBeanCycle(ReflectionUtils.getField(parentAuthMgrField, delegateBuilder), this.beanNames);
        this.delegateBuilder = delegateBuilder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (this.delegate != null) {
            return this.delegate.authenticate(authentication);
        }
        initAuthenticationManager();
        return this.delegate.authenticate(authentication);
    }

    private void initAuthenticationManager() {
        if (null == this.delegate) {
            synchronized (this) {
                if (null == this.delegate) {
                    this.delegate = (ProviderManager) this.delegateBuilder.getObject();
                    this.delegate.getProviders().add(new UserPasswordAuthenticationProvider(userDetailsService));
                    this.delegate.getProviders().add(new EmailAuthenticationProvider(userDetailsService));
                    this.delegateBuilder = null;
                }
            }
        }
    }

    private static Set<String> getAuthenticationManagerBeanNames(ApplicationContext applicationContext) {
        String[] beanNamesForType = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(applicationContext,
                AuthenticationManager.class);
        return new HashSet<>(Arrays.asList(beanNamesForType));
    }

    private static void validateBeanCycle(Object auth, Set<String> beanNames) {
        if (auth == null || beanNames.isEmpty() || !(auth instanceof Advised)) {
            return;
        }
        TargetSource targetSource = ((Advised) auth).getTargetSource();
        if (!(targetSource instanceof LazyInitTargetSource)) {
            return;
        }
        LazyInitTargetSource lits = (LazyInitTargetSource) targetSource;
        if (beanNames.contains(lits.getTargetBeanName())) {
            throw new FatalBeanException(
                    "A dependency cycle was detected when trying to resolve the AuthenticationManager. "
                            + "Please ensure you have configured authentication.");
        }
    }

}
