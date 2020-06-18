/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

public class DadosGrafico {
    private String sessaoId;
    private double totalBilheteria;
    private double totalSite;
    
    public DadosGrafico(String sessaoId, double totalBilheteria, double totalSite)
    {
        this.sessaoId = sessaoId;
        this.totalBilheteria = totalBilheteria;
        this.totalSite = totalSite;
    }

    public String getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(String sessaoId) {
        this.sessaoId = sessaoId;
    }

    public double getTotalBilheteria() {
        return totalBilheteria;
    }

    public void setTotalBilheteria(double totalBilheteria) {
        this.totalBilheteria = totalBilheteria;
    }

    public double getTotalSite() {
        return totalSite;
    }

    public void setTotalSite(double totalSite) {
        this.totalSite = totalSite;
    }

    
    
}
