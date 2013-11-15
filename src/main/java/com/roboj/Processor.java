public class Processor {
    private String method;
    private String[] params;

    public Processor(String method, String[] params) {
        this.method = method;
        this.params = params;
    }

    public String process(String result) {
        if(this.method.equals("replace")) {
            result = replace(result, this.params[0], this.params[1]);
        } else if(this.method.equals("prefix")) {
            result = prefix(result, this.params[0]);
        } else if(this.method.equals("sufix")) {
            result = sufix(result, this.params[0]);
        }

        return result;
    }

    public String replace(String result, String a, String b){
        return result.replace(a, b);
    }

    public String prefix(String result, String a){
        return a + result;
    }

    public String sufix(String result, String a){
        return result + a;
    }

    public String toString() {
        String text = this.method + " - ";
        for(int i = 0; i < this.params.length; i++) {
            text += this.params[i] + ",";
        }
        return text;
    }

}
