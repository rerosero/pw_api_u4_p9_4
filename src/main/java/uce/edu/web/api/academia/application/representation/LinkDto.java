package uce.edu.web.api.academia.application.representation;

public class LinkDto {
    public String rel;
    public String href;

    //constructor por defecto
    public LinkDto() {
    }
    public LinkDto(String rel, String href) {
        this.rel = rel;
        this.href = href;
    }

}
