import static org.junit.jupiter.api.Assertions.*;


class ColorTest {

    @org.junit.jupiter.api.Test
    void decode() {
        var expectedValue=new Color(31,240,255);
        var actualValue=Color.decode("Ox1FF0FF");
        assertEquals(expectedValue.getRed(),actualValue.getRed());
        assertEquals(expectedValue.getGreen(),actualValue.getGreen());
        assertEquals(expectedValue.getBlue(),actualValue.getBlue());
    }

    @org.junit.jupiter.api.Test
    void RGBtoHSB() {
        var c=new Color(31,240,255);
        var expectedValue=new float[3];
        var actualValue=new float[3];

        expectedValue[0]=0.5111607f;
        expectedValue[1]=0.8784314f;
        expectedValue[2]=1.0f;
        Color.RGBtoHSB(c.getRed(),c.getGreen(),c.getBlue(),actualValue);

        assertEquals(expectedValue[0],actualValue[0]);
        assertEquals(expectedValue[1],actualValue[1]);
        assertEquals(expectedValue[2],actualValue[2]);

    }

   @org.junit.jupiter.api.Test
    void RGBtoCYMK() {
       var c=new Color(31,240,255);
       var expectedValue=new int[4];
       var actualValue=new int[4];

       expectedValue[0]=87;
       expectedValue[1]=5;
       expectedValue[2]=0;
       expectedValue[3]=0;
       Color.RGBtoCYMK(c.getRed(),c.getGreen(),c.getBlue(),actualValue);

       assertEquals(expectedValue[0],actualValue[0]);
       assertEquals(expectedValue[1],actualValue[1]);
       assertEquals(expectedValue[2],actualValue[2]);
       assertEquals(expectedValue[3],actualValue[3]);
    }

    @org.junit.jupiter.api.Test
    void RGBtoHSL() {
        var c=new Color(31,240,255);
        var expectedValue=new float[3];
        var actualValue=new float[3];

        expectedValue[0]=184.01785f;
        expectedValue[1]=100.0f;
        expectedValue[2]=56.078434f;
        Color.RGBtoHSL(c.getRed(),c.getGreen(),c.getBlue(),actualValue);

        assertEquals(expectedValue[0],actualValue[0]);
        assertEquals(expectedValue[1],actualValue[1]);
        assertEquals(expectedValue[2],actualValue[2]);
    }

}