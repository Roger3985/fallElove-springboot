package com.roger.member.entity.uniqueAnnotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

@Constraint(validatedBy = ValidMemAccount.ValidMemAccountValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMemAccount {

    // 驗證失敗時顯示的錯誤處理
    String message() default "會員帳號: 只能是英文字母、數字";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default{};

    class ValidMemAccountValidator implements ConstraintValidator<ValidMemAccount, String> {

        // 會員帳號驗證的正則表達式
        private static final Pattern MEMBER_ACCOUNT_PATTERN = Pattern.compile("^[a-zA-Z0-9]{4,10}$");

        @Override
        public void initialize(ValidMemAccount constraintAnnotation) {
            // 初始化驗證器（如果需要，可以在這裡進行初始化）
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {

            // DONE 寫法完成
            // 如果值為 null，則視為有效
            if (value.trim().isEmpty()) {
                return true;
            }

            // 使用正則表達式檢查值是否符合會員帳號的格式
            return MEMBER_ACCOUNT_PATTERN.matcher(value).matches();
        }
    }
}
