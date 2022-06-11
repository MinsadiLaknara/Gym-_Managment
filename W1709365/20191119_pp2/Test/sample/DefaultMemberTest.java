package sample;

import com.sun.corba.se.impl.interceptors.PICurrent;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultMemberTest {

    @Test
    public  void  getFirst_Name() throws Exception{
        DefaultMember minsi = new DefaultMember("Minsi");
        assertEquals("Minsi",minsi.getFirst_Name());
    }
    @Test
    public void getLast_Name() throws Exception{
        DefaultMember nana = new DefaultMember("John");
        assertEquals("Nana",nana.getLast_Name());
    }
    @Test
    public void Age() throws Exception{
        Over60Member test = new Over60Member();
        int input = Integer.parseInt(("40"));
        assertEquals(40,input);

    }

}