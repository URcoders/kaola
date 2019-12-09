package com.kaola.manager;

import com.kaola.manager.util.DateUtil;
import com.kaola.manager.util.DigestUtil;
import org.junit.jupiter.api.Test;

/**
 * @author linxu
 * @date 2019/11/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class UtilTest {
    @Test
    public void testDigest(){
        System.out.println(DigestUtil.digest("123456",2));
    }
    @Test
    public void testDate(){
        //System.out.println(DateUtil.getY_M_D());
        for (String d:DateUtil.getY_M_DList()
             ) {
            System.out.println(d);
        }
    }
}
