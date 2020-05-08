package votingaid.domain;

/**
 * A class representing additional info of a candidate. Shown in CandidateView.
 * @author mlkul
 */
public class CandidateInfo {
    final int id;
    final String electoralDistrict;
    final String language;
    final String profession;
    final String education;
    final String reasoning;

    
    public CandidateInfo(int id, String eDistrict, String lang, String prof, 
            String edu, String reason) {
        this.id = id;
        this.electoralDistrict = eDistrict;
        this.language = lang;
        this.profession = prof;
        this.education = edu;
        this.reasoning = reason;
    }
    
    /**
     * Construct a CandidateInfo that leaves the candidate's infopage "empty"
     * if there's no data available in the database.
     * @param id 
     */
    public CandidateInfo(int id) {
        this.id = id;
        this.electoralDistrict = "-";
        this.language = "-";
        this.profession = "-";
        this.education = "-";
        this.reasoning = "-";
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getElectoralDistrict() {
        return this.electoralDistrict;
    }
    
    public String getLanguage() {
        return this.language;
    }
    
    public String getProfession() {
        return this.profession;
    }
    
    public String getEducation() {
        return this.education;
    }
    
    public String getReasoning() {
        return this.reasoning;
    }
    
    
}
