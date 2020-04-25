package Entities;

public class Cinema {
    private int id;
    private String name;
    private String initials;
    private String adress;
    private String cnpj;
    private String active;

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id;}
    public String getName() { return this.name;}
    public void setName(String name) { this.name = name;}
    public String getInitials() { return this.initials;}
    public void setId(String initials) { this.initials = initials;}
    public String getAdress() { return this.adress;}
    public void setAdress(String adress) { this.adress = adress;}
    public String getCnpj() { return this.cnpj;}
    public void setCnpj(String cnpj) { this.cnpj = cnpj;}
    public String getActive() { return this.active;}
    public void setActive(String active) { this.active = active;}
}
