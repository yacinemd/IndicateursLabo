package groupe7.indicateurs.web.projet.dto;

import java.util.List;

import groupe7.indicateurs.web.personnel.dto.PersonnelWebDTO;

public class ProjetWebDTO {
    private Long id;
    private String nom;
    private String type;
    private boolean partenariat;
    private List<PersonnelWebDTO> personnels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPartenariat() {
        return partenariat;
    }

    public void setPartenariat(boolean partenariat) {
        this.partenariat = partenariat;
    }

    public List<PersonnelWebDTO> getPersonnels() {
        return personnels;
    }

    public void setProjets(List<PersonnelWebDTO> personnels) {
        this.personnels = personnels;
    }

}
