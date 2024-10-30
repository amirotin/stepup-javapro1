package ru.stepup.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import ru.stepup.dto.IntegrationErrorDto;
import ru.stepup.dto.IntegrationException;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError()||response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError()||response.getStatusCode().is5xxServerError()) {
            ObjectMapper objectMapper = new ObjectMapper();
            IntegrationErrorDto error = objectMapper.readValue(response.getBody(), IntegrationErrorDto.class);
            throw new IntegrationException(error.message(), null);
        }
    }
}