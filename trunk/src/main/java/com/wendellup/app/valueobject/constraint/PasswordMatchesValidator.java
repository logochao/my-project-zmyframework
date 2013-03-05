/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-2-5 下午5:39:15
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-2-5   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.app.valueobject.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

public class PasswordMatchesValidator implements 
		ConstraintValidator<PasswordMatches, Object> {
	private String field;
	private String verifyField;
	
	@Override
	public void initialize(PasswordMatches matchers) {
		this.field = matchers.field();
		this.verifyField = matchers.verifyField();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		try {
			String fieldValue = BeanUtils.getProperty(value, field);
			String verfiyFieldValue = BeanUtils.getProperty(value, verifyField);
			boolean valid = (fieldValue == null) && (verfiyFieldValue == null);
			if(valid){
				return true;
			}
			
			boolean match = (fieldValue != null) 
				&& fieldValue.equals(verfiyFieldValue);
			if(!match){
				String messageTemplate = context
						.getDefaultConstraintMessageTemplate();
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(messageTemplate)
						.addNode(verifyField).addConstraintViolation();
			}
			return match;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
