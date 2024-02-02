package system;

import java.util.List;

public class OffreClass {
    public String Title;
    public String OffreLink;
    public String SiteName;
    public String PublicationDate;
    public String ApplyDate;
    public String OffreDescription;
    public String Region;
    public String City;
    public String Sectors;
    public String Occupation;
    public String ContractType;
    public String EducationLevel;
    public String Diploma;
    public String ExperienceLevel;
    public String SearchedProfile;
    public String PersonalityTraits;
    public String RecommandedCompetence;
    public String Salary;
    public String SocialAdvantages;
    public String RemoteWork;
    public int NumberOfPosts;
    public List<SkillClass> skills;
    public CompanyClass companys;
    public List<LangueClass> langues;

    public OffreClass(
     String Title,
     String OffreLink,  
     String SiteName,
     String PublicationDate,
     String ApplyDate,
     String OffreDescription,
     String Region,
     String City,
     String Sectors,
     String Occupation,
     String ContractType,
     String EducationLevel,
     String Diploma,
     String ExperienceLevel,
     String SearchedProfile,
     String PersonalityTraits,
     String RecommandedCompetence,
     String Salary,
     String SocialAdvantages,
     String RemoteWork,
     int NumberOfPosts,
     List<SkillClass> skills,
     CompanyClass companys,
     List<LangueClass> langues
     ) {
        this.Title = Title;
        this.OffreLink = OffreLink;
        this.SiteName = SiteName;
        this.PublicationDate = PublicationDate;
        this.ApplyDate = ApplyDate;
        this.OffreDescription = OffreDescription;
        this.Region = Region;
        this.City = City;
        this.Sectors = Sectors;
        this.Occupation = Occupation;
        this.ContractType = ContractType;
        this.EducationLevel = EducationLevel;
        this.Diploma = Diploma;
        this.ExperienceLevel = ExperienceLevel;
        this.SearchedProfile = SearchedProfile;
        this.PersonalityTraits = PersonalityTraits;
        this.RecommandedCompetence = RecommandedCompetence;
        this.Salary = Salary;
        this.SocialAdvantages = SocialAdvantages;
        this.RemoteWork = RemoteWork;
        this.NumberOfPosts = NumberOfPosts;
        this.skills = skills;
        this.companys = companys;
        this.langues = langues;
     }

}
