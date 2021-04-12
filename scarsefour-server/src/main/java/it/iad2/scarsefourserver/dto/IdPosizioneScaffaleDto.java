package it.iad2.scarsefourserver.dto;

public class IdPosizioneScaffaleDto {
    private Long id;

    public IdPosizioneScaffaleDto() {
    }

    public IdPosizioneScaffaleDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IdPosizioneScaffaleDto{" +
                "id=" + id +
                '}';
    }
}
