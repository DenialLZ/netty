package com.lize.netty_lecture;

/**
 * @author: lize
 * @Date: 2020/8/14 15:13
 * @Description:
 */
public class Test {



//    protected <T> T doGetBean(final String name, @Nullable final Class<T> requiredType,
//                              @Nullable final Object[] args, boolean typeCheckOnly) throws BeansException {
//        //实例化依赖的bean之后可以实例化mbd本身了
//        //单例模式的创建
//        if (mbd.isSingleton()) {
//            sharedInstance = getSingleton(beanName, () -> {
//                try {
//                    /**
//                     *核心创建bean
//                     */
//                    return createBean(beanName, mbd, args);
//                }
//                catch (BeansException ex) {
//                    // Explicitly remove instance from singleton cache: It might have been put there
//                    // eagerly by the creation process, to allow for circular reference resolution.
//                    // Also remove any beans that received a temporary reference to the bean.
//                    destroySingleton(beanName);
//                    throw ex;
//                }
//            });
//            //真正的bean初始化处理
//            bean = getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
//        }
//    }



}
