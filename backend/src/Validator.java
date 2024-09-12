public class Validator {
    public static boolean isValid(String Xvalue, String Yvalue, String Rvalue) {
        try{
            float x = Float.parseFloat(Xvalue);
            float y = Float.parseFloat(Yvalue);
            float r = Float.parseFloat(Rvalue);
            return ((-5 < x && x < 3 && y > -5 && y < 5 && r > 1 && r < 4) &&
                    ((x >= -r && x <= 0 && y <= 0 && y >= -r / 2) ||
                            (x >= 0 && x <= r / 2 && y >= 0 && y <= r / 2 && Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2)) ||
                            (x >= 0 && x <= r && y <= 0 && y >= -r / 2 && y >= (x / 2) - r / 2)));
        }catch (NumberFormatException e){
            return false;
        }


    }
}
