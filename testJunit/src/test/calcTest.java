package test;
import source.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class calcTest {

    //Generete Test with Junit v4.1

    public calcTest() {
    }

    @Test
    public void calcTest_1() {
        System.out.println("Prueba | caso 1");
        int a = 60;
        int b = 40;
        String signo = "+";
        calculo instance = new calculo();
        int expResult = 100;
        int result = instance.comprobarCalculo(a, signo, b);
        assertEquals(expResult, result);
    }

    @Test
    public void calcTest_2() {
        System.out.println("Prueba | caso 2");
        int a = 50;
        int b = 230;
        String signo = "-";
        calculo instance = new calculo();
        int expResult = -180;
        int result = instance.comprobarCalculo(a, signo, b);
        assertEquals(expResult, result);
    }

    @Test
    public void calcTest_3() {
        System.out.println("Prueba | caso 3");
        int a = 100;
        int b = 45;
        String signo = "";
        calculo instance = new calculo();
        int expResult = 55;
        int result = instance.comprobarCalculo(a, signo, b);
        assertEquals(expResult, result);
    }

    @Test
    public void calcTest_4() {
        System.out.println("Prueba | caso 4");
        int a = 0;
        int b = 0;
        String signo = "+";
        calculo instance = new calculo();
        int expResult = 0;
        int result = instance.comprobarCalculo(a, signo, b);
        assertEquals(expResult, result);
    }

}
