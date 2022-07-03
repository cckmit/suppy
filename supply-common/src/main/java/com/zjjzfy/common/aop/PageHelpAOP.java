package com.zjjzfy.common.aop;

import com.github.pagehelper.PageHelper;

import com.zjjzfy.common.annotation.PagehelpService;
import com.zjjzfy.common.entity.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.ClassClassPath;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zyx
 * @date 2020/3/3 下午11:35
 */
@Component
@Aspect
@Slf4j
public class PageHelpAOP {


    //Service层切点
    @Pointcut("@annotation(com.zjjzfy.common.annotation.PagehelpService)")
    public void serviceAspect() {
    }


    //拦截自定义注解
    @Before("serviceAspect()&&@annotation(pagehelpService)")
    public void serviceImplAop(JoinPoint joinPoint, PagehelpService pagehelpService) throws Exception {
        PageBean pageBean =null;

        Object[] args = joinPoint.getArgs();
        //获取类名
        String clazzName = joinPoint.getTarget().getClass().getName();
        //获取方法名称
        String methodName = joinPoint.getSignature().getName();
        //通过反射获取参数列表
        Map<String,Object > nameAndArgs = this.getFieldsName(this.getClass(), clazzName, methodName,args);

        pageBean = new PageBean();

        pageBean.setPageNo(
                nameAndArgs.get(pagehelpService.pageNoName())==null?
                        pagehelpService.pageNo():(Integer) nameAndArgs.get(pagehelpService.pageNoName()));
        pageBean.setPageSize(
                nameAndArgs.get(pagehelpService.pageSizeName())==null?
                        pagehelpService.pageSize():(Integer)nameAndArgs.get(pagehelpService.pageSizeName()));

        PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize());
    }


    /**
     * 通过反射获取参数列表
     * @param cls
     * @param clazzName
     * @param methodName
     * @param args
     * @return
     * @throws Exception
     */
    private Map<String,Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws Exception {
        Map<String,Object > map=new HashMap<String,Object>();

        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            // exception
        }
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++){
            map.put( attr.variableName(i + pos),args[i]);
        }
        return map;
    }



}
