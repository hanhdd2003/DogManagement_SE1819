package data.history;

public class History {
    private String idCus;
    private String idDog;
    private String idEmp; // thêm số tiền giao dịch
    private double price;    
    private String currentTime;

    public History() {
    }

    
    public History(String idCus, String idDog, String idEmp, double price, String currentTime) {
        this.idCus = idCus;
        this.idDog = idDog;
        this.idEmp = idEmp;
        this.price = price;
        this.currentTime = currentTime;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIdCus() {
        return idCus;
    }

    public void setIdCus(String idCus) {
        this.idCus = idCus;
    }

    public String getIdDog() {
        return idDog;
    }

    public void setIdDog(String idDog) {
        this.idDog = idDog;
    }

    public String getIdEmp() {
        return idEmp;
    }



    public void setIdEmp(String idEmp) {
        this.idEmp = idEmp;
    }

    public void display() {
        System.out.printf(" %10s %12s %8s %12s %15s\n", price, idCus, idDog, idEmp, currentTime);
    }

    @Override
    public String toString() {
        return this.price +" , " + this.idCus + " , " + this.idDog + " , " + this.idEmp +" , " + this.currentTime;
    }

}
