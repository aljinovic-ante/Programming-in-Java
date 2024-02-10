

public class Color {
    private int red,green,blue,value;
    public int getRed(){
        return red;
    }
    public int getGreen(){
        return green;
    }
    public int getBlue(){
        return blue;
    }
    public int getRGB(){
        return value;
    }

    public static Color decode(String str)
    {
        var color=str.split("x")[1];
        var rHex=color.substring(0,2);
        var gHex= color.substring(2,4);
        var bHex= color.substring(4,6);
        var rDec= Long.parseLong(rHex,16);
        var gDec= Long.parseLong(gHex,16);
        var bDec= Long.parseLong(bHex,16);
        return new Color((int)rDec,(int)gDec,(int)bDec);
    }
    Color(int red,int green,int blue)
    {
        this.red=red;
        this.green=green;
        this.blue=blue;
        value=((red<<16)|(green<<8)|(blue));
    }

    public static float[]  RGBtoHSB(int red,int green,int blue,float[] hsb){
        //System.out.println(red+" "+green+" "+blue);
            float h=0,s=0,b=0;
            float redFloat=(float)red/255;
            float greenFloat=(float)green/255;
            float blueFloat=(float)blue/255;

            //b
            if ((redFloat > greenFloat)&&(redFloat > blueFloat)){
                b=redFloat;
            } else if ((greenFloat > redFloat)&&(greenFloat > blueFloat)) {
                b=greenFloat;
            }
            else{
                b=blueFloat;
            }

            //s
            float min=0;
            if (b==0){
                s=0;
            }
            else{
                if ((redFloat < greenFloat)&&(redFloat < blueFloat)){
                    min=redFloat;
                } else if ((greenFloat < redFloat)&&(greenFloat < blueFloat)) {
                    min=greenFloat;
                }
                else{
                    min=blueFloat;
                }
                s=(b-min)/b;
            }

            //h
        //System.out.println(b+"  "+min+" ttttt");
            /*if(s==0){
                h=0;
            }
            else{
                if (b == redFloat) {
                    h = (greenFloat - blueFloat) / (b - min);
                } else if (b == greenFloat) {
                    h = (float) 2.0*((blueFloat - redFloat) / (b - min));
                } else if (b == blueFloat) {
                    h = (float) 4.0*((redFloat - greenFloat) / (b - min));
                }
            }*/

       if (s == 0)
            h = 0;
        else {
            float redc = ((float) (b - redFloat)) / ((float) (b - min));
            float greenc = ((float) (b - greenFloat)) / ((float) (b - min));
            float bluec = ((float) (b - b)) / ((float) (b - min));
            if (redFloat == b)
                h = bluec - greenc;
            else if (greenFloat == b)
                h = 2.0f + redc - bluec;
            else
                h = 4.0f + greenc - redc;
            h = h / 6.0f;
            if (h < 0)
                h = h + 1.0f;
        }
        
            hsb[0]=h;
            hsb[1]=s;
            hsb[2]=b;

            return hsb;

    }

    public static int[] RGBtoCYMK(int red,int green, int blue,int[] cymkCode)
    {

        float r = red / 255.0f;
        float g = green / 255.0f;
        float b = blue / 255.0f;

        float k = 1 - Math.max(Math.max(r, g), b);
        float c = (1 - r - k) / (1 - k);
        float m = (1 - g - k) / (1 - k);
        float y = (1 - b - k) / (1 - k);

        cymkCode[0] = (int) (c * 100);
        cymkCode[1] = (int) (m * 100);
        cymkCode[2] = (int) (y * 100);
        cymkCode[3] = (int) (k * 100);

        return cymkCode;
    }

    public static float[] RGBtoHSL(int red,int green,int blue,float[] hslCode)
    {
        float r = red / 255.0f;
        float g = green / 255.0f;
        float b = blue / 255.0f;

        float max = Math.max(r, Math.max(g, b));
        float min = Math.min(r, Math.min(g, b));

        float l = (max + min) / 2;

        float h = 0;
        float s = 0;

        if (max != min) {
            float d = max - min;

            if(l>0.5f)
            {
                s=d/(2.0f-max-min);
            }
            else{
                s=d/(max+min);
            }

            if (max == r) {
                h = (g - b) / (max-min);
            } else if (max == g) {
                h = 2.0f+ (b-r)/(max-min);
            } else {
                h = 4.0f + (r-g)/(max-min);
            }

            h =h*60;
        }
        hslCode[0]=h;
        hslCode[1]=s*100;
        hslCode[2]=l*100;
        return hslCode;
    }

    public static void main(String[] args) {
        String hexColor = "0x1FF0FF";

        Color c = Color.decode(hexColor);

        float[] hsbCode = new float[3];

        Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsbCode);
        System.out.println("Boja u HEX formatu: 0x" +
                Integer.toHexString(c.getRGB() & 0x00FFFFFF));
        System.out.println("Boja u RGB formatu: " + c.getRed() + ", " +
                c.getGreen() + ", " + c.getBlue());
        System.out.println("Boja u HSB formatu: " + hsbCode[0] * 360 + "°, " +
                hsbCode[1] * 100 + "%, " + hsbCode[2] * 100 + "%");
        int[] cymkCode=new int[4];
        Color.RGBtoCYMK(c.getRed(),c.getGreen(),c.getBlue(),cymkCode);
        System.out.println("Boja u CYMK formatu: "+cymkCode[0]+", " +cymkCode[1]+", "+cymkCode[2]+", "+cymkCode[3]);
        float[] hslCode=new float[3];
        Color.RGBtoHSL(c.getRed(),c.getGreen(),c.getBlue(),hslCode);
        System.out.println("Boja u HSL formatu: " + hslCode[0] + "°, " + hslCode[1]  + "%, " + hslCode[2]  + "%");
    }
}
