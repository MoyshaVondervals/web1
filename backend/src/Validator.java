public class Validator {
    public static boolean isValid(String json) {
        json = json.replace("}", "").replace("{","");
        String[] data = json.split(",");

        try{

            float x = Float.parseFloat(data[0].split(":")[1].replace("\"", ""));
            float y = Float.parseFloat(data[1].split(":")[1].replace("\"", ""));
            float r = Float.parseFloat(data[2].split(":")[1].replace("\"", ""));

            if ((-5 < x && x < 3 && -5 < y && y < 5 && 1 < r && r < 4) &&
                    ((x >= -r && x <= 0 && y <= 0 && y >= -r / 2) ||
                            (x >= 0 && x <= r / 2 && y >= 0 && y <= r / 2 && Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2)) ||
                            (x >= 0 && x <= r && y <= 0 && y >= -r / 2 && y >= (x / 2) - r / 2))){
                return true;
            }
            else return false;
        }catch (NumberFormatException e){
            return false;
        }


    }

}
