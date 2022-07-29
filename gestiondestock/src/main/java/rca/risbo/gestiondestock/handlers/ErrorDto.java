package rca.risbo.gestiondestock.handlers;

import lombok.*;
import rca.risbo.gestiondestock.exception.ErrorCodes;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private Integer httpCode;

    private ErrorCodes code;

    private String message;

    private List<String> errors = new ArrayList<>();
}
