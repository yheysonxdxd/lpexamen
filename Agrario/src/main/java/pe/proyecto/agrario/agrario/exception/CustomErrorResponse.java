package pe.proyecto.agrario.agrario.exception;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomErrorResponse {
    private int statusCode;

    private LocalDateTime datetime;
    private String message;
    private String details;
    public CustomErrorResponse(LocalDateTime datetime, String message, String details) {
        this.datetime = datetime;
        this.message = message;
        this.details = details;
    }

}