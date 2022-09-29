package com.my.core.assertions;

import com.my.core.error.ErrorCode;
import com.my.core.exception.ServerRunTimeException;
import com.my.core.util.EmptyUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class ServerAssert {

  /**
   * Assert expression.
   * @param  expression 表達式
   * @param errorCode 狀態碼
   * @throws ServerRunTimeException exception
   */
  public static void isTrue(boolean expression, ErrorCode errorCode) {
    if (!expression) {
      throw new ServerRunTimeException(errorCode);
    }
  }

  /**
   * Assert expression.
   * @param expression 表達式.
   * @param errorCode 狀態碼.
   * @param args args.
   * @throws ServerRunTimeException exception
   */
  public static void isTrue(boolean expression, ErrorCode errorCode, String... args) {
    if (!expression) {
      throw new ServerRunTimeException(errorCode, Arrays.asList(args));
    }
  }

    /**
     * Assert expression.
     * @param expression 表達式.
     * @param errorCode 狀態碼.
     * @param msgs msgs.
     * @throws ServerRunTimeException exception
     */
  public static void isTrue(boolean expression, ErrorCode errorCode, List<String> msgs) {
      if (!expression) {
          throw new ServerRunTimeException(errorCode, msgs);
      }
  }


  /**
   * BAD REQUEST expression.
   * @param expression 表達式.
   * @throws ServerRunTimeException exception
   */
  public static void isTrue(boolean expression) {
    isTrue(expression, ErrorCode.BAD_REQUEST);
  }

  /**
   * isNull expression.
   * @param object 物件.
   * @param errorCode 狀態碼.
   * @throws ServerRunTimeException exception
   */
  public static void isNull(Object object, ErrorCode errorCode) {
    if (object != null) {
      throw new ServerRunTimeException(errorCode);
    }
  }


  /**
   * isNull expression.
   * @param object 物件.
   * @throws ServerRunTimeException exception
   */
  public static void isNull(Object object) {
    isNull(object, ErrorCode.BAD_REQUEST);
  }


  /**
   * notNull expression.
   * @param object 物件.
   * @param errorCode 狀態碼.
   * @throws ServerRunTimeException exception.
   */
  public static void notNull(Object object, ErrorCode errorCode) {
    if (object == null) {
      throw new ServerRunTimeException(errorCode);
    }
  }

  /**
   * notNull expression.
   * @param object 物件.
   * @throws ServerRunTimeException exception.
   */
  public static void notNull(Object object) {
    notNull(object, ErrorCode.BAD_REQUEST);
  }


  /**
   * Assert is not null & is not empty.
   * @param str 文本.
   * @param errorCode 狀態碼.
   * @throws ServerRunTimeException exception.
   */
  public static void hasLength(String str, ErrorCode errorCode) {
    if (EmptyUtil.stringIsEmpty(str)) {
      throw new ServerRunTimeException(errorCode);
    }
  }


  /**
   * Assert String not null && not empty.
   * @param str 文本.
   * @throws ServerRunTimeException exception.
   */
  public static void hasLength(String str) {
    hasLength(str, ErrorCode.BAD_REQUEST);
  }



  /**
   * Assert array not null && not empty.
   * @param array 陣列.
   * @param errorCode 狀態碼.
   * @throws ServerRunTimeException exception.
   */
  public static void notEmpty(Object[] array, ErrorCode errorCode) {
    if (EmptyUtil.arrayIsEmpty(array)) {
      throw new ServerRunTimeException(errorCode);
    }
  }


  /**
   * Assert array not null && not empty.
   * @param array 陣列.
   * @throws ServerRunTimeException exception.
   */
  public static void notEmpty(Object[] array) {
    notEmpty(array, ErrorCode.BAD_REQUEST);
  }

  /**
   * collection not null && not empty.
   * @param collection 容器.
   * @param errorCode 狀態碼.
   * @throws ServerRunTimeException exception.
   */
  public static void notEmpty(Collection<?> collection, ErrorCode errorCode) {
    if (EmptyUtil.isEmpty(collection)) {
      throw new ServerRunTimeException(errorCode);
    }
  }


  /**
   * collection not null && not empty.
   * @param collection 容器.
   * @throws ServerRunTimeException exception.
   */
  public static void notEmpty(Collection<?> collection) {
    notEmpty(collection, ErrorCode.BAD_REQUEST);
  }


  /**
   * Assert map not null && not empty.
   * @param map map.
   * @param errorCode 狀態碼.
   * @throws ServerRunTimeException exception.
   */
  public static void notEmpty(Map<?, ?> map, ErrorCode errorCode) {
    if (EmptyUtil.mapIsEmpty(map)) {
      throw new ServerRunTimeException(errorCode);
    }
  }


  /**
   * Assert map not null && not empty.
   * @param map map.
   * @throws ServerRunTimeException exception.
   */
  public static void notEmpty(Map<?, ?> map) {
    notEmpty(map, ErrorCode.BAD_REQUEST);
  }


  /**
   * Assert obj isInstanceOf clazz.
   * @param clazz class.
   * @param obj 物件
   * @throws ServerRunTimeException exception.
   */
  public static void isInstanceOf(Class<?> clazz, Object obj) {
    isInstanceOf(clazz, obj, ErrorCode.BAD_REQUEST);
  }


  /**
   * Assert obj isInstanceOf clazz.
   * @param clazz class.
   * @param obj 物件.
   * @param errorCode 狀態碼.
   * @throws ServerRunTimeException exception.
   */
  public static void isInstanceOf(Class<?> clazz, Object obj, ErrorCode errorCode) {
    notNull(clazz, ErrorCode.BAD_REQUEST);
    if (!clazz.isInstance(obj)) {
      throw new ServerRunTimeException(errorCode);
    }
  }
}
