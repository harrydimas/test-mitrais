package com.mitrais.registration.configuration;

import java.util.Calendar;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import com.mitrais.registration.model.BaseModel;

@Aspect
@Configuration
public class BaseModelAspect {

	@Before("execution(* *..repository.*Repository.save(..))")
    public void before(JoinPoint joinPoint) {

		Object[] args = joinPoint.getArgs();
		Object object = (Object) args[0];

		if ("save".equals(joinPoint.getSignature().getName()) && object instanceof BaseModel) {
			Date date = Calendar.getInstance().getTime();
			if (((BaseModel) object).getCreatedBy() == null || ((BaseModel) object).getCreatedDate() == null) {
				((BaseModel) object).setCreatedBy("SYSTEM");
				((BaseModel) object).setCreatedDate(date);
			}
			((BaseModel) object).setUpdatedBy("SYSTEM");
			((BaseModel) object).setUpdatedDate(date);	
		}
		
    }
	
}
