public class Processor {
    private String method;
    private String[] params;

    public Processor(String method, String[] params) {
        this.method = method;
        this.params = params;
    }

    public String process(String result) {
        switch(this.method){
            case "replace":
                result = replace(result, this.params[0], this.params[1]);
                break;
            case "prefix":
                result = prefix(result, this.params[0]);
                break;
            case "sufix":
                result = sufix(result, this.params[0]);
                break;
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

}
